package top.wys.utils;
/**
 * Created by 郑明亮 on 2017/11/17 12:03.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
            log.error("输入流转字节异常",e);
        }

        return temp != null ?temp.toByteArray() : new byte[0];
    }

    /**
     * 将输入流转为字符串，默认编码为UTF-8
     * @param inputStream 输入流
     * @return
     */
    public static String is2String(InputStream inputStream){
        return new String(isToBytes(inputStream), Charset.forName("UTF-8"));
    }

    /**
     * 将输入流转为指定编码的字符串
     * @param inputStream 输入流
     * @param charset 指定编码，如果编码不存在，则可能会抛出异常
     * @return
     */
    public static String is2String(InputStream inputStream,String charset){
        return new String(isToBytes(inputStream), Charset.forName(charset));
    }

    /**
     * @param closeable
     */
    public static void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            log.error("流关闭异常",e);
        }
    }

    /**
     * 关闭多个流
     * @param closeable
     */
    public static void close(AutoCloseable... closeable) {
        try {
            if (closeable != null) {
                for (AutoCloseable item : closeable) {
                    close(item);
                }
            }

        } catch (Exception e) {
            log.error("流关闭异常",e);
        }
    }


    /**
     * @param flushable
     */
    public static void flush(Flushable flushable) {
        try {
            if (flushable != null) {
                flushable.flush();
            }
        } catch (IOException e) {
            log.error("刷盘异常",e);
        }
    }

    /**
     * 刷新多个
     * @param flushable
     */
    public static void flush(Flushable... flushable) {
        try {
            if (flushable != null) {
                for (Flushable item : flushable) {
                    item.flush();
                }

            }
        } catch (IOException e) {
            log.error("刷盘异常",e);
        }
    }

    public static InputStream getInputStream(File file){
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("文件不存在",e);
        }
        return null;

    }

    public static InputStream getInputStream(String path){
       return getInputStream(new File(path));
    }

    public static InputStream getInputStream(byte[] bytes){
       return new ByteArrayInputStream(bytes);
    }


    public static Reader getReader(File file){
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            log.error("文件不存在",e);
        }
        return null;

    }

    public static Reader getReader(InputStream inputStream){
        return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }

    public static Reader getReader(String path){
       return getReader(new File(path));
    }

    public static Reader getReader(String path,Charset charset){
       return new StringReader(FileUtils.readTxtFile(path,charset.name()));
    }


}
