package top.wys.utils;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Supplier;

public class Print {

    private static final Logger log = LoggerFactory.getLogger(Print.class);

//    正常normal
//    警告 warning

    /*  样式：

    0  空样式

    1  粗体

    4  下划线

    7  反色

    颜色1：

    30  黑色

    31  红色

    32  绿色

    33  黄色

    34  蓝色

    35  紫色

    36  浅蓝

    37  灰色

    背景颜色：

    40-47 和颜色顺序相同

    颜色2：

    90-97  比颜色1更鲜艳一些，我也不太清楚为什么又两种
    * */

    public static String wrapNormal(Object object) {
        return wrapGreen(object);
    }

    public static String wrapWarning(Object object) {
        return wrapYellow(object);
    }

    public static String wrapError(Object object) {
        return wrapRed(object);
    }


    /**
     * 黑色
     *
     * @param object
     * @return
     */
    public static String wrapBlack(Object object) {
        object = getObject(object);
        return "\033[30;1m" + object + "\033[0m";
    }

    /**
     * 红色
     *
     * @param object
     * @return
     */
    public static String wrapRed(Object object) {
        object = getObject(object);
        return "\033[31;1m" + object + "\033[0m";
    }

    /**
     * 绿色
     *
     * @param object
     * @return
     */
    public static String wrapGreen(Object object) {
        object = getObject(object);
        return "\033[32;1m" + object + "\033[0m";
    }

    /**
     * 黄色
     *
     * @param object
     * @return
     */
    public static String wrapYellow(Object object) {
        object = getObject(object);
        return "\033[33;1m" + object + "\033[0m";
    }

    /**
     * 蓝色
     *
     * @param object
     * @return
     */
    public static String wrapBlue(Object object) {
        object = getObject(object);
        return "\033[34;1m" + object + "\033[0m";
    }

    /**
     * 紫色
     *
     * @param object
     * @return
     */
    public static String wrapPurple(Object object) {
        object = getObject(object);
        return "\033[35;1m" + object + "\033[0m";
    }


    /**
     * 浅蓝
     *
     * @param object
     * @return
     */
    public static String wrapLightBlue(Object object) {
        object = getObject(object);
        return "\033[36;1m" + object + "\033[0m";
    }


    /**
     * 灰色
     *
     * @param object
     * @return
     */
    public static String wrapGrey(Object object) {
        object = getObject(object);
        return "\033[37;1m" + object + "\033[0m";
    }


    @Nullable
    private static Object getObject(Object object) {
        if (object != null) {
            if (object instanceof Supplier) {
                object = ((Supplier<?>) object).get();
            } else if (object instanceof Throwable) {
                object = ((Throwable) object).getMessage();
            } else if (object instanceof Optional) {
                object = ((Optional<?>) object).isPresent() ? ((Optional<?>) object).get() : "null";
            }
        }
        return object;
    }

    public static void normal(Object object) {
        String print = wrapNormal(object);
        System.out.println(print);
        log.info(print);
    }

    public static void warning(Object object) {
        String print = wrapWarning(object);
        System.out.println(print);
        log.warn(print);
    }

    public static void error(Object object) {
        String print = wrapError(object);
        System.out.println(print);
        log.warn(print);
    }

}
