package top.wys.utils;
/**
 * Created by 郑明亮 on 2017/11/17 12:03.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author 郑明亮   @email 1072307340@qq.com
 * @version 1.0
 * @time 2017/11/17 12:03
 * @description <p> </p>
 */
public class IOUtils {

    private  static Logger log = LoggerFactory.getLogger(IOUtils.class);

    private IOUtils(){
        throw new UnsupportedOperationException("you can not instant me");
    }

    /**
     * @param closeable
     */
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            log.error("流关闭异常",e);
        }
    }

}
