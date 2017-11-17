package top.wys.utils;
/**
 * Created by 郑明亮 on 2017/11/16 18:01.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author 郑明亮   email 1072307340@qq.com
 * @version 1.0
 * time 2017/11/16 18:01
 * <p>properties配置文件读取工具类 </p>
 * <p>性能上，只使用一次配置文件，比手动读取一次偶尔会多几毫秒，但在项目中读取配置文件往往很频繁，远比手动读取要省时很多</p>
 */
public final class PropertiesUtil {

    private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {
        throw new UnsupportedOperationException("you cannot instant me");
    }

    private static HashMap<String, Properties> propertiesMap = new HashMap<>();


    /**
     * 对加载的配置文件计数
     */
    private static int propertySize = 0;


    /**
     * 通过文件名加载classpath下的配置文件
     *
     * @param fileName 配置文件名称
     * @return
     */
    public static boolean loadFromClassPath(String fileName) {
        return loadFromClassPath(fileName, true);
    }

    /**
     * 通过文件名加载classpath下的配置文件
     *
     * @param fileName 配置文件名称
     * @param loadOnce 是否只加载一次，默认为true，当设置为false时，每次都会从配置文件中重新读取
     * @return
     */
    public static boolean loadFromClassPath(String fileName, boolean loadOnce) {
        boolean flag = false;
        Properties properties = propertiesMap.get(fileName);

        if (!loadOnce || properties == null) {
            properties = new Properties();
            try {
                properties.load(PropertiesUtil.class.getResourceAsStream("/" + fileName));
                flag = true;
                propertiesMap.put(fileName, properties);
                propertySize = propertiesMap.keySet().size();
            } catch (IOException e) {
                log.error("从classpath下未找到执行配置文件，开始通过绝对路径寻找配置文件", e);
                flag = loadFromPath(fileName);
            }
        } else {//仅加载一次，并且properties不为空
            flag = true;
        }

        return flag;
    }


    /**
     * <p>通过完整的文件路径加载配置文件</p>
     *
     * @param path
     * @return
     */
    public static boolean loadFromPath(String path) {
        return loadFromPath(path, true);
    }

    /**
     * <p>通过完整的文件路径加载配置文件</p>
     *
     * @param path
     * @param loadOnce 是否只加载一次，默认为true，当设置为false时，每次都会从配置文件中重新读取
     * @return
     */
    public static boolean loadFromPath(String path, boolean loadOnce) {
        boolean flag = false;
        Properties properties = propertiesMap.get(path);

        if (!loadOnce || properties == null) {
            properties = new Properties();
            try (FileInputStream fis = new FileInputStream(path)) {
                properties.load(fis);
                propertiesMap.put(path, properties);
                flag = true;
                propertySize = propertiesMap.size();
            } catch (IOException e) {
                log.error("通过绝对路径未找到配置文件", e);
            }
        } else {//首次加载
            flag = true;
        }
        return flag;
    }

    public static String getString(String key) {
        for (Properties properties : propertiesMap.values()) {
            String property = properties.getProperty(key);
            if (property != null) {
                return property;
            }
        }
        return null;
    }

    /**
     * 当获取的值为null时，会使用defaultValue的值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key, String defaultValue) {
        String value = getString(key);
        return value == null ? defaultValue : value;
    }

    public static Object get(String key) {
        for (Properties properties : propertiesMap.values()) {
            Object o = properties.get(key);
            if (o != null) {
                return o;
            }
        }
        return null;
    }

    /**
     * 当获取的值为null时，会使用defaultValue的值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String key, Object defaultValue) {
        for (Properties properties : propertiesMap.values()) {
            Object o = properties.get(key);
            if (o != null) {
                return o;
            }
        }
        return defaultValue;
    }

    public static Integer getInteger(String key) {
        String string = getString(key);
        if (string != null) {
            return Integer.valueOf(string);
        }
        return null;
    }

    /**
     * 当获取的值为null时，会使用defaultValue的值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static Integer getInteger(String key, int defaultValue) {
        Integer integer = getInteger(key);
        return integer == null ? defaultValue : integer;
    }


    public static Double getDouble(String key) {
        String string = getString(key);
        if (string != null) {
            return Double.valueOf(string);
        }
        return null;
    }


    /**
     * 当获取的值为null时，会使用defaultValue的值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static Double getDouble(String key, double defaultValue) {
        Double value = getDouble(key);
        return value == null ? defaultValue : value;
    }

    /**
     * @param key
     * @return
     */
    public static Boolean getBoolean(String key) {
        return Boolean.valueOf(getString(key));
    }


    /**
     * 当获取的值为null时，会使用defaultValue的值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static Boolean getBoolean(String key, boolean defaultValue) {
        Boolean value = getBoolean(key);
        return value == null ? defaultValue : value;
    }


    /**
     * @param key
     * @return
     */
    public static Long getLong(String key) {
        String string = getString(key);
        if (string != null) {
            return Long.valueOf(string);
        }
        return null;
    }


    /**
     * 当获取的值为null时，会使用defaultValue的值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static Long getLong(String key, long defaultValue) {
        Long value = getLong(key);
        return value == null ? defaultValue : value;
    }

    public static boolean removeKeys(String fileName) {
        Properties remove = propertiesMap.remove(fileName);
        return remove != null;
    }

    public static boolean update(String key, String value, String comment, boolean updateToFile) throws FileNotFoundException {
        boolean flag = false;
        for (Map.Entry<String, Properties> propertiesEntry : propertiesMap.entrySet()) {
            Properties properties = propertiesEntry.getValue();
            if (properties.containsKey(key)) {
                flag = properties.setProperty(key, value) != null;
                if (updateToFile) {
                    updateToFile(key, value, comment, propertiesEntry);
                }
                break;
            }
        }

        return flag;
    }

    /**
     * 更新参数到配置文件（保留原有配置文件的注释信息）
     *
     * @param key
     * @param value
     * @param comment
     * @param propertiesEntry
     * @throws FileNotFoundException
     */
    private static void updateToFile(String key, String value, String comment, Map.Entry<String, Properties> propertiesEntry) throws FileNotFoundException {
        Properties properties;
        properties = new SafeProperties();//采用自定义Properties，读取配置文件时，记录注释等信息
        String fileName = propertiesEntry.getKey();
        String path = PropertiesUtil.class.getResource(File.separator + fileName).getPath();

        File file = new File(path);
        if (!file.exists()) {
            file = new File(fileName);
            if (!file.exists()) {
                log.error("配置文件不存在");
                throw new FileNotFoundException("配置文件不存在");
            }
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            properties.load(fis);
            properties.setProperty(key, value);
            FileOutputStream fos = new FileOutputStream(file);
            properties.store(fos, comment);
        } catch (IOException e) {
            log.error("更新配置文件发生异常", e);
        }
    }

    /**
     * <li>获取所有的已加载配置文件的key值</li>
     * <li>在配置文件较少时，使用该方法更省时，当加载配置文件较多时，采用{@link PropertiesUtil#getKeys(String)}方法会更快</li>
     *
     * @return
     */
    public static Set<String> getAllKeys() {
        Set<String> stringPropertyNames = new HashSet<>();
        for (Properties properties : propertiesMap.values()) {
            if (propertySize == 1) {
                return properties.stringPropertyNames();
            } else {
                stringPropertyNames.addAll(properties.stringPropertyNames());
            }

        }
        return stringPropertyNames;
    }

    /**
     * <li>通过配置文件名称获取该配置文件的所有key</li>
     * <li>前提是，已使用PropertiesUtil.loadXXX方法加载过该配置文件</li>
     * <li>在配置文件较多时时，使用该方法更省时，当加载配置文件较少时采用{@link PropertiesUtil#getAllKeys()}方法会更快</li>
     *
     * @param fileName 文件名称
     * @return
     */
    public static Set<String> getKeys(String fileName) {
        Properties properties = propertiesMap.get(fileName);
        if (properties != null) {
            return properties.stringPropertyNames();
        }
        return Collections.emptySet();
    }

}
