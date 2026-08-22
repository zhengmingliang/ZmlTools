package top.wys.utils.io.monitor;

import top.wys.utils.IOUtils;
import top.wys.utils.exception.MonitorException;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * 文件监听服务，此服务可以同时监听多个路径。
 *
 * @author loolly
 * @since 5.1.0
 */
public class MonitorServer extends Thread implements Closeable, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 监听服务。
     */
    private transient WatchService watchService;
    /**
     * 监听事件列表。
     */
    protected WatchEvent.Kind<?>[] events;
    /**
     * 监听选项，例如监听频率等。
     */
    private WatchEvent.Modifier[] modifiers;
    /**
     * 监听是否已经关闭。
     */
    protected volatile boolean isClosed = true;
    /**
     * WatchKey 和 Path 的对应表。
     */
    private final Map<WatchKey, Path> watchKeyPathMap = new ConcurrentHashMap<>();

    /**
     * 初始化监听服务。
     *
     * @throws IOException 创建 WatchService 失败时抛出
     */
    public synchronized void init() throws IOException {
        IOUtils.close(watchService);
        watchKeyPathMap.clear();
        watchService = FileSystems.getDefault().newWatchService();
        isClosed = false;
    }

    /**
     * 设置监听选项，例如监听频率等。
     *
     * @param modifiers 监听选项
     */
    public void setModifiers(WatchEvent.Modifier[] modifiers) {
        this.modifiers = modifiers == null ? null : modifiers.clone();
    }

    /**
     * 将指定路径加入监听。
     *
     * @param path     路径，必须是目录
     * @param maxDepth 递归下层目录的最大深度，1 表示只监听当前目录
     */
    public void registerPath(Path path, int maxDepth) {
        if (path == null) {
            throw new IllegalArgumentException("Path must not be null");
        }
        if (watchService == null || isClosed) {
            throw new MonitorException("Watch Monitor is not initialized");
        }

        final Path normalizedPath = path.toAbsolutePath().normalize();
        final WatchEvent.Kind<?>[] kinds = getWatchEventKinds();
        try {
            registerDirectory(normalizedPath, kinds);
            if (maxDepth > 1) {
                Files.walkFileTree(normalizedPath, EnumSet.noneOf(FileVisitOption.class), maxDepth,
                        new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attrs)
                                    throws IOException {
                                if (!normalizedPath.equals(directory)) {
                                    registerDirectory(directory, kinds);
                                }
                                return FileVisitResult.CONTINUE;
                            }

                            @Override
                            public FileVisitResult visitFileFailed(Path file, IOException exception)
                                    throws IOException {
                                if (exception instanceof AccessDeniedException) {
                                    return FileVisitResult.SKIP_SUBTREE;
                                }
                                throw exception;
                            }
                        });
            }
        } catch (AccessDeniedException ignored) {
            // 无权限目录跳过监听。
        } catch (IOException exception) {
            throw new MonitorException("Failed to register path: " + normalizedPath, exception);
        }
    }

    /**
     * 执行一次事件获取并处理。
     *
     * @param action      事件处理函数
     * @param watchFilter 事件过滤器，null 表示不过滤
     */
    public void watch(MonitorAction action, Predicate<WatchEvent<?>> watchFilter) {
        if (action == null) {
            throw new IllegalArgumentException("Monitor action must not be null");
        }
        if (isClosed) {
            return;
        }
        if (watchService == null) {
            throw new MonitorException("Watch Monitor is not initialized");
        }

        final WatchKey watchKey;
        try {
            watchKey = watchService.take();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            close();
            return;
        } catch (java.nio.file.ClosedWatchServiceException exception) {
            close();
            return;
        }

        final Path currentPath = watchKeyPathMap.get(watchKey);
        for (WatchEvent<?> event : watchKey.pollEvents()) {
            if (watchFilter == null || watchFilter.test(event)) {
                action.handle(event, currentPath);
            }
        }

        if (!watchKey.reset()) {
            watchKeyPathMap.remove(watchKey);
        }
    }

    /**
     * 执行一次事件获取并分发给观察者。
     *
     * @param monitor     观察者
     * @param watchFilter 事件过滤器，null 表示不过滤
     */
    public void watch(Monitor monitor, Predicate<WatchEvent<?>> watchFilter) {
        if (monitor == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        watch((event, currentPath) -> dispatch(monitor, event, currentPath), watchFilter);
    }

    /**
     * 关闭监听服务。
     */
    @Override
    public synchronized void close() {
        isClosed = true;
        IOUtils.close(watchService);
        watchService = null;
        watchKeyPathMap.clear();
    }

    private void registerDirectory(Path path, WatchEvent.Kind<?>[] kinds) throws IOException {
        final WatchKey key;
        if (modifiers == null || modifiers.length == 0) {
            key = path.register(watchService, kinds);
        } else {
            key = path.register(watchService, kinds, modifiers);
        }
        watchKeyPathMap.put(key, path);
    }

    private WatchEvent.Kind<?>[] getWatchEventKinds() {
        if (events == null || events.length == 0) {
            return WatchEventKinds.ALL;
        }
        return events.clone();
    }

    private static void dispatch(Monitor monitor, WatchEvent<?> event, Path currentPath) {
        WatchEvent.Kind<?> kind = event.kind();
        if (kind == WatchEventKinds.CREATE.getValue()) {
            monitor.onCreate(event, currentPath);
        } else if (kind == WatchEventKinds.MODIFY.getValue()) {
            monitor.onModify(event, currentPath);
        } else if (kind == WatchEventKinds.DELETE.getValue()) {
            monitor.onDelete(event, currentPath);
        } else if (kind == WatchEventKinds.OVERFLOW.getValue()) {
            monitor.onOverflow(event, currentPath);
        }
    }
}
