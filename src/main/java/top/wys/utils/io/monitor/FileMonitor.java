package top.wys.utils.io.monitor;

import top.wys.utils.IOUtils;
import top.wys.utils.exception.MonitorException;
import top.wys.utils.io.monitor.impl.DelayMonitor;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchService;

/**
 * 路径监听器。
 *
 * <p>监听器可以监听目录或单个文件，支持创建、修改、删除和事件溢出通知。</p>
 * <p>监听单个文件时，实际监听的是文件所在目录，因此文件被删除后重新创建仍然可以收到事件。</p>
 *
 * @since 1.4.5
 */
public class FileMonitor extends MonitorServer {
    private static final long serialVersionUID = 1L;

    /** 事件丢失。 */
    public static final WatchEvent.Kind<?> OVERFLOW = WatchEventKinds.OVERFLOW.getValue();
    /** 修改事件。 */
    public static final WatchEvent.Kind<?> ENTRY_MODIFY = WatchEventKinds.MODIFY.getValue();
    /** 创建事件。 */
    public static final WatchEvent.Kind<?> ENTRY_CREATE = WatchEventKinds.CREATE.getValue();
    /** 删除事件。 */
    public static final WatchEvent.Kind<?> ENTRY_DELETE = WatchEventKinds.DELETE.getValue();
    /** 全部事件。 */
    public static final WatchEvent.Kind<?>[] EVENTS_ALL = WatchEventKinds.ALL;

    /** 实际监听的目录。 */
    private Path path;
    /** 目录递归监听最大深度，1 表示只监听当前目录。 */
    private int maxDepth;
    /** 单文件监听目标，目录监听时为空。 */
    private Path filePath;
    /** 事件观察者。 */
    private volatile Monitor watcher;
    /** 根路径是否已经注册到 WatchService。 */
    private volatile boolean registered;

    public static FileMonitor create(URI uri, WatchEvent.Kind<?>... events) {
        return create(uri, 0, events);
    }

    public static FileMonitor create(URI uri, int maxDepth, WatchEvent.Kind<?>... events) {
        return create(Paths.get(uri), maxDepth, events);
    }

    public static FileMonitor create(File file, WatchEvent.Kind<?>... events) {
        return create(file, 0, events);
    }

    public static FileMonitor create(File file, int maxDepth, WatchEvent.Kind<?>... events) {
        return create(file.toPath(), maxDepth, events);
    }

    public static FileMonitor create(String path, WatchEvent.Kind<?>... events) {
        return create(path, 0, events);
    }

    public static FileMonitor create(String path, int maxDepth, WatchEvent.Kind<?>... events) {
        return create(Paths.get(path), maxDepth, events);
    }

    public static FileMonitor create(Path path, WatchEvent.Kind<?>... events) {
        return create(path, 0, events);
    }

    public static FileMonitor create(Path path, int maxDepth, WatchEvent.Kind<?>... events) {
        return new FileMonitor(path, maxDepth, events);
    }

    /**
     * 创建监听所有事件的监听器。
     *
     * @param path    监听路径
     * @param watcher 事件观察者
     * @return 文件监听器
     */
    public static FileMonitor createAll(Path path, Monitor watcher) {
        return create(path, EVENTS_ALL).setWatcher(watcher);
    }

    public static FileMonitor createAll(String path, Monitor watcher) {
        return createAll(Paths.get(path), watcher);
    }

    /**
     * 创建监听所有事件并合并短时间内重复修改事件的监听器。
     *
     * @param path         监听路径
     * @param watcher      事件观察者
     * @param modifyDelay  修改事件静默延迟，单位毫秒
     * @return 文件监听器
     */
    public static FileMonitor createAll(Path path, Monitor watcher, long modifyDelay) {
        return createAll(path, new DelayMonitor(watcher, modifyDelay));
    }

    public static FileMonitor createAll(String path, Monitor watcher, long modifyDelay) {
        return createAll(Paths.get(path), watcher, modifyDelay);
    }

    public static FileMonitor createAll(File file, Monitor watcher) {
        return createAll(file.toPath(), watcher);
    }

    public static FileMonitor createAll(URI uri, Monitor watcher) {
        return createAll(Paths.get(uri), watcher);
    }

    public static FileMonitor createAll(URL url, Monitor watcher) {
        try {
            return createAll(Paths.get(url.toURI()), watcher);
        } catch (URISyntaxException exception) {
            throw new MonitorException(exception);
        }
    }

    public FileMonitor(File file, WatchEvent.Kind<?>... events) {
        this(file.toPath(), events);
    }

    public FileMonitor(String path, WatchEvent.Kind<?>... events) {
        this(Paths.get(path), events);
    }

    public FileMonitor(Path path, WatchEvent.Kind<?>... events) {
        this(path, 0, events);
    }

    /**
     * 构造路径监听器。
     *
     * @param path     文件或目录路径
     * @param maxDepth 目录递归深度，1 或更小表示只监听当前目录
     * @param events   监听事件，未指定时监听全部事件
     */
    public FileMonitor(Path path, int maxDepth, WatchEvent.Kind<?>... events) {
        if (path == null) {
            throw new IllegalArgumentException("Path must not be null");
        }
        this.path = path;
        this.maxDepth = maxDepth;
        this.events = events == null ? null : events.clone();
        init();
    }

    /**
     * 解析监听目标并初始化 WatchService。
     *
     * <p>已存在的普通文件按单文件监听处理；已存在的目录按目录监听处理。不存在的路径如果文件名带扩展名，
     * 按待创建文件处理，否则创建并监听该目录。</p>
     */
    @Override
    public void init() throws MonitorException {
        final Path requestedPath = path.toAbsolutePath().normalize();
        try {
            if (Files.isDirectory(requestedPath, LinkOption.NOFOLLOW_LINKS)) {
                path = requestedPath;
                filePath = null;
            } else if (Files.exists(requestedPath, LinkOption.NOFOLLOW_LINKS)
                    || looksLikeFile(requestedPath)) {
                filePath = requestedPath;
                path = requestedPath.getParent();
                Files.createDirectories(path);
            } else {
                Files.createDirectories(requestedPath);
                path = requestedPath;
                filePath = null;
            }
            super.init();
            registered = false;
        } catch (IOException exception) {
            throw new MonitorException("Failed to initialize file monitor: " + requestedPath, exception);
        }
    }

    /**
     * 设置事件观察者。
     *
     * @param watcher 观察者
     * @return 当前监听器
     */
    public FileMonitor setWatcher(Monitor watcher) {
        this.watcher = watcher;
        return this;
    }

    @Override
    public synchronized void start() {
        registerPathIfNecessary();
        super.start();
    }

    @Override
    public void run() {
        watch();
    }

    /**
     * 开始监听并阻塞当前线程。
     */
    public void watch() {
        watch(watcher);
    }

    /**
     * 开始监听并阻塞当前线程。
     *
     * @param watcher 观察者
     */
    public void watch(Monitor watcher) {
        if (watcher == null) {
            throw new MonitorException("Monitor must not be null");
        }
        if (isClosed) {
            throw new MonitorException("Watch Monitor is closed");
        }

        registerPathIfNecessary();
        while (!isClosed) {
            doTakeAndWatch(watcher);
        }
    }

    /**
     * 设置目录递归监听最大深度。
     *
     * @param maxDepth 1 或更小表示只监听当前目录
     * @return 当前监听器
     */
    public FileMonitor setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
        return this;
    }

    @Override
    public void close() {
        registered = false;
        super.close();
        Monitor currentWatcher = watcher;
        if (currentWatcher instanceof Closeable) {
            IOUtils.close((Closeable) currentWatcher);
        }
    }

    private void doTakeAndWatch(Monitor monitor) {
        super.watch((event, currentPath) -> {
            registerCreatedDirectory(event, currentPath);
            dispatch(monitor, event, currentPath);
        }, this::matchesTarget);
    }

    private synchronized void registerPathIfNecessary() {
        if (!registered) {
            if (isClosed) {
                throw new MonitorException("Watch Monitor is closed");
            }
            super.registerPath(path, filePath == null ? maxDepth : 1);
            registered = true;
        }
    }

    private boolean matchesTarget(WatchEvent<?> event) {
        if (filePath == null || event.kind() == OVERFLOW) {
            return true;
        }
        return event.context() instanceof Path && eventPath(null, event) != null
                && filePath.equals(eventPath(null, event));
    }

    private Path eventPath(Path currentPath, WatchEvent<?> event) {
        if (currentPath == null && path != null) {
            currentPath = path;
        }
        if (currentPath == null || !(event.context() instanceof Path)) {
            return null;
        }
        return currentPath.resolve((Path) event.context()).toAbsolutePath().normalize();
    }

    private void registerCreatedDirectory(WatchEvent<?> event, Path currentPath) {
        if (filePath != null || event.kind() != ENTRY_CREATE || maxDepth <= 1) {
            return;
        }
        Path createdPath = eventPath(currentPath, event);
        if (createdPath == null || !Files.isDirectory(createdPath, LinkOption.NOFOLLOW_LINKS)
                || !createdPath.startsWith(path)) {
            return;
        }
        int currentDepth = path.relativize(createdPath).getNameCount() + 1;
        if (currentDepth <= maxDepth) {
            super.registerPath(createdPath, maxDepth - currentDepth + 1);
        }
    }

    private static boolean looksLikeFile(Path path) {
        Path fileName = path.getFileName();
        return fileName != null && fileName.toString().contains(".")
                && !fileName.toString().endsWith(".d");
    }

    private static void dispatch(Monitor monitor, WatchEvent<?> event, Path currentPath) {
        WatchEvent.Kind<?> kind = event.kind();
        if (kind == ENTRY_CREATE) {
            monitor.onCreate(event, currentPath);
        } else if (kind == ENTRY_MODIFY) {
            monitor.onModify(event, currentPath);
        } else if (kind == ENTRY_DELETE) {
            monitor.onDelete(event, currentPath);
        } else if (kind == OVERFLOW) {
            monitor.onOverflow(event, currentPath);
        }
    }
}
