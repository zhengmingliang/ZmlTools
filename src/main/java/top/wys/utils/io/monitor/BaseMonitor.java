package top.wys.utils.io.monitor;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 监视器的默认实现，默认不做任何事情 用户继承此类后实现需要监听的方法
 * @since 1.4.5
 */
public abstract class BaseMonitor implements Monitor{
    @Override
    public void onCreate(WatchEvent<?> event, Path currentPath) {

    }

    @Override
    public void onModify(WatchEvent<?> event, Path currentPath) {

    }

    @Override
    public void onDelete(WatchEvent<?> event, Path currentPath) {

    }

    @Override
    public void onOverflow(WatchEvent<?> event, Path currentPath) {

    }
}
