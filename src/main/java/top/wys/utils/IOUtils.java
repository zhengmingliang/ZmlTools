package top.wys.utils;
/**
 * Created by 郑明亮 on 2017/11/17 12:03.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

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
     * 将输入流转换为byte[]
     *
     * @param is 输入流
     * @return
     */
    public static byte[] isToBytes(InputStream is) {

        ByteArrayOutputStream temp = null;
        byte[] buffer = new byte[1024];
        int len = 0;
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
            temp = bos;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temp != null ?temp.toByteArray() : new byte[0];
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
