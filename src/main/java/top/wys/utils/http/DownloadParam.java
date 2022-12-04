/**
 * Created by 郑明亮 on 2022/8/27 11:05.
 */
package top.wys.utils.http;

import java.net.URL;
import java.nio.file.Path;

/**
 * <p> 下载参数</p>
 *
 * @author 郑明亮
 * @version 1.0.0
 * @time 2022/8/27 11:05
 */
public class DownloadParam {
    private String fileName;
    private long fileSize;
    private long saveSize;
    private Status status = Status.WAITING;
    private Path localPath;
    private URL location;
    private String description;
    private URL refLocation;
    private long lastConnectTime;

    public enum Status {
        WAITING("等待"), RUNNING("运行中"), STOPPED("停止"), FINISHED("完成");
        private String value;

        Status(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
