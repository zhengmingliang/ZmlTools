/**
 * Created by 郑明亮 on 2022/8/27 11:57.
 */
package top.wys.utils.http;

/**
 * <p> 通知事件</p>
 *
 * @author 郑明亮
 * @since 1.4.1
 * @time 2022/8/27 11:57
 */
public interface ProcessNotifyEvent {
    /**
     * 通知
     *
     * @param process 进度或长度
     */
    void notifySpeed(long process);

    /**
     * 通知
     *
     * @param process 进度或长度
     * @param total   总长度
     */
    void notifyProcess(long process,long total);
}
