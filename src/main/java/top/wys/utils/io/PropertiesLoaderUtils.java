/**
 * Created by 郑明亮 on 2022/3/20 10:12.
 */
package top.wys.utils.io;

import top.wys.utils.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Properties;

/**
 * <p> 用于加载 java.util.Properties 的便捷实用方法，执行输入流的标准处理</p>
 *
 * @author 郑明亮
 * @since  2.0.0
 * @time 2022/3/20 10:12
 */
public class PropertiesLoaderUtils {

    private static final String XML_FILE_EXTENSION = ".xml";
    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private PropertiesLoaderUtils() {
        throw new UnsupportedOperationException("you can not instantiate me !");
    }

    /**
     * Load properties from the given EncodedResource,
     * potentially defining a specific encoding for the properties file.
     */
    public static Properties loadProperties(File file) throws IOException {

       return loadProperties(file);
    }  /**
     * Load properties from the given EncodedResource,
     * potentially defining a specific encoding for the properties file.
     */
    public static Properties loadProperties(String path) throws IOException {

       return loadProperties(path,DEFAULT_CHARSET);
    }

    public static Properties loadProperties(String path, Charset charset) throws IOException {
        Properties props = new Properties();
        fillProperties(props, new File(path),charset);
        return props;
    }

    /**
     * 填充属性
     *
     * @param props the Properties instance to load into
     * @param path  路径
     * @throws IOException in case of I/O errors
     */
    public static void fillProperties(Properties props, String path) throws IOException {

        fillProperties(props, new File(path),null);
    }

    /**
     * 填充属性
     *
     * @param props   the Properties instance to load into
     * @param file    文件
     * @param charset 字符集
     * @throws IOException in case of I/O errors
     */
    static void fillProperties(Properties props, File file,Charset charset)
            throws IOException {

        InputStream stream = null;
        Reader reader = null;
        try {

            String fileName = file.getName();
            if (fileName != null && fileName.endsWith(XML_FILE_EXTENSION)) {
                stream = new FileInputStream(file);
                props.loadFromXML(stream);
            }
            else if (charset != null) {
                reader = IOUtils.getReader(file);
                props.load(reader);

            }
            else {
                stream = new FileInputStream(file);
                props.load(stream);
            }
        }
        finally {
            if (stream != null) {
                stream.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * 加载配置项（utf-8 encoding）
     *
     * @param is    io流
     * @param isXml 是xml
     * @return the populated Properties instance
     * @throws IOException if loading failed
     * @see PropertiesLoaderUtils#fillProperties(java.util.Properties, java.io.InputStream, boolean)
     */
    public static Properties loadProperties(InputStream is,boolean isXml) throws IOException {
        Properties props = new Properties();
        fillProperties(props, is,isXml);
        return props;
    }

    /**
     * 填充属性
     * Fill the given properties from the given resource (in ISO-8859-1 encoding).
     *
     * @param props the Properties instance to fill
     * @param is    是
     * @param isXml 是xml
     * @throws IOException if loading failed
     */
    public static void fillProperties(Properties props, InputStream is,boolean isXml) throws IOException {
        try {

            if (isXml) {
                props.loadFromXML(is);
            }
            else {
               props.load(is);
            }
        }
        finally {
            is.close();
        }
    }

    /**
     * Load all properties from the specified class path resource
     * (in ISO-8859-1 encoding), using the default class loader.
     * <p>Merges properties if more than one resource of the same name
     * found in the class path.
     * @param resourceName the name of the class path resource
     * @return the populated Properties instance
     * @throws IOException if loading failed
     */
    public static Properties loadAllProperties(String resourceName) throws IOException {
        return loadAllProperties(resourceName, null);
    }

    /**
     * Load all properties from the specified class path resource
     * (in ISO-8859-1 encoding), using the given class loader.
     * <p>Merges properties if more than one resource of the same name
     * found in the class path.
     * @param resourceName the name of the class path resource
     * @param classLoader the ClassLoader to use for loading
     * (or {@code null} to use the default class loader)
     * @return the populated Properties instance
     * @throws IOException if loading failed
     */
    public static Properties loadAllProperties(String resourceName, ClassLoader classLoader) throws IOException {
        Assert.notNull(resourceName, "Resource name must not be null");
        ClassLoader classLoaderToUse = classLoader;
        if (classLoaderToUse == null) {
            classLoaderToUse = ClassUtils.getDefaultClassLoader();
        }
        Enumeration<URL> urls = (classLoaderToUse != null ? classLoaderToUse.getResources(resourceName) :
                ClassLoader.getSystemResources(resourceName));
        Properties props = new Properties();
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            URLConnection con = url.openConnection();
            ResourceUtils.useCachesIfNecessary(con);
            InputStream is = con.getInputStream();
            try {
                if (resourceName.endsWith(XML_FILE_EXTENSION)) {
                    props.loadFromXML(is);
                }
                else {
                    props.load(is);
                }
            }
            finally {
                is.close();
            }
        }
        return props;
    }
}
