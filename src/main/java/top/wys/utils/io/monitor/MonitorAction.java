package top.wys.utils.io.monitor;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 监听事件处理函数接口
 *
 * @since 1.4.5
 */
@FunctionalInterface
public interface MonitorAction {
    /**
     * 事件处理，通过实现此方法处理各种事件。
     *
     * 事件可以调用 {@link WatchEvent#kind()} 获取，对应事件见 {@link WatchEventKinds}
     *
     * @param event       事件
     * @param currentPath 事件发生的当前Path路径
     */
    void handle(WatchEvent<?> event, Path currentPath);
}
