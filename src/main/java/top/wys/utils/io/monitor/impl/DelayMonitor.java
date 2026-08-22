package top.wys.utils.io.monitor.impl;

import top.wys.utils.Assert;
import top.wys.utils.io.monitor.Monitor;
import top.wys.utils.thread.ExecutorServiceUtil;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 延迟监听器。
 *
 * <p>只对 modify 事件做按文件路径的尾部延迟合并，只有文件在指定时间内不再修改时才触发一次回调。
 * create、delete 和 overflow 事件会立即转发。</p>
 *
 * @since 1.4.5
 */
public class DelayMonitor implements Monitor, Closeable {

    /** 当前文件对应的延迟任务。 */
    private final Map<Path, ScheduledFuture<?>> scheduledEvents = new ConcurrentHashMap<>();
    /** 用于保证取消旧任务和注册新任务的原子性。 */
    private final Object taskLock = new Object();
    /** 实际处理事件的观察者。 */
    private final Monitor watcher;
    /** 延迟时间，单位毫秒。 */
    private final long delay;
    /** 延迟任务执行器。 */
    private final ScheduledExecutorService executor;

    /**
     * @param watcher 实际处理事件的观察者，不能是 null 或另一个 DelayMonitor
     * @param delay   延迟时间，单位毫秒；小于 1 时不做延迟合并
     */
    public DelayMonitor(Monitor watcher, long delay) {
        Assert.notNull(watcher);
        if (watcher instanceof DelayMonitor) {
            throw new IllegalArgumentException("Monitor must not be a DelayMonitor");
        }
        this.watcher = watcher;
        this.delay = delay;
        this.executor = delay < 1 ? null : ExecutorServiceUtil.newSingleScheduledExecutorService();
    }

    @Override
    public void onModify(WatchEvent<?> event, Path currentPath) {
        if (delay < 1) {
            watcher.onModify(event, currentPath);
            return;
        }

        final Path eventPath = resolveEventPath(currentPath, event);
        if (eventPath == null) {
            watcher.onModify(event, currentPath);
            return;
        }
        synchronized (taskLock) {
            ScheduledFuture<?> previous = scheduledEvents.remove(eventPath);
            if (previous != null) {
                previous.cancel(false);
            }

            final ScheduledFuture<?>[] current = new ScheduledFuture<?>[1];
            current[0] = executor.schedule(() -> {
                synchronized (taskLock) {
                    if (scheduledEvents.get(eventPath) != current[0]) {
                        return;
                    }
                    scheduledEvents.remove(eventPath);
                }
                watcher.onModify(event, currentPath);
            }, delay, TimeUnit.MILLISECONDS);
            scheduledEvents.put(eventPath, current[0]);
        }
    }

    @Override
    public void onCreate(WatchEvent<?> event, Path currentPath) {
        watcher.onCreate(event, currentPath);
    }

    @Override
    public void onDelete(WatchEvent<?> event, Path currentPath) {
        watcher.onDelete(event, currentPath);
    }

    @Override
    public void onOverflow(WatchEvent<?> event, Path currentPath) {
        watcher.onOverflow(event, currentPath);
    }

    /**
     * 取消尚未触发的延迟任务并关闭执行器。
     */
    @Override
    public void close() throws IOException {
        if (executor == null) {
            return;
        }
        synchronized (taskLock) {
            for (ScheduledFuture<?> future : scheduledEvents.values()) {
                future.cancel(false);
            }
            scheduledEvents.clear();
        }
        executor.shutdownNow();
    }

    private static Path resolveEventPath(Path currentPath, WatchEvent<?> event) {
        if (currentPath == null || event == null || event.context() == null) {
            return currentPath;
        }
        return currentPath.resolve(event.context().toString()).toAbsolutePath().normalize();
    }
}
