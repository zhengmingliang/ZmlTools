/**
 * Created by 郑明亮 on 2022/2/28 19:25.
 */
package top.wys.utils.collection;

import top.wys.utils.DataUtils;

/**
 * <p> 布尔值处理工具类</p>
 *
 * @author 郑明亮
 * @since 1.3.9
 * @time 2022/2/28 19:25
 */
public class Booleans {

    private static final String[] TRUE_FLAGS = {"ok", "y","yes","t","true","1"};
    private static final String[] BOOLEAN_FLAGS = {"ok", "y","yes","t","true","false","f","no","n","1","0"};

    /**
     * 转为布尔类型
     * @param str
     * @return
     */
    public static boolean valueOf(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return DataUtils.orEqualsIgnoreCase(str, TRUE_FLAGS);
    }

    /**
     * 是否为布尔类型
     * @param str
     * @return
     */
    public static Boolean isBoolean(String str) {
        return DataUtils.orEqualsIgnoreCase(str, BOOLEAN_FLAGS);
    }


    /**
     * 是否为true
     * @param bool
     * @return
     */
    public static boolean isTrue(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue() ? true : false;
    }


    /**
     * 是否不是true
     * <pre>
     * Booleans.isNotTrue(Boolean.TRUE)  = false
     * Booleans.isNotTrue(Boolean.FALSE) = true
     * Booleans.isNotTrue(null)          = true
     * </pre>
     *
     * @param bool 布尔
     * @return boolean
     */
    public static boolean isNotTrue(Boolean bool) {
        return !isTrue(bool);
    }


    /**
     * 是否是false
     * <pre>
     *  Booleans.isFalse(Boolean.TRUE)  = false
     *  Booleans.isFalse(Boolean.FALSE) = true
     *  Booleans.isFalse(null)          = false
     * </pre>
     * @param bool
     * @return
     */
    public static boolean isFalse(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue() ? false : true;
    }


    /**
     * 是否不是false
     * <pre>
     *  Booleans.isNotFalse(Boolean.TRUE)  = true
     *  Booleans.isNotFalse(Boolean.FALSE) = false
     *  Booleans.isNotFalse(null)          = true
     * </pre>
     * @param bool
     * @return
     */
    public static boolean isNotFalse(Boolean bool) {
        return !isFalse(bool);
    }


    /**
     * 将布尔值转换为处理 null 的布尔值。
     * <pre>
     *  Booleans.toBooleanDefaultIfNull(Boolean.TRUE, false) = true
     *  Booleans.toBooleanDefaultIfNull(Boolean.FALSE, true) = false
     *  Booleans.toBooleanDefaultIfNull(null, true)          = true
     * </pre>
     * @param bool  布尔值
     * @param valueIfNull 如果是null则返回什么布尔值
     * @return
     */
    public static boolean toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull) {
        if (bool == null) {
            return valueIfNull;
        }
        return bool.booleanValue() ? true : false;
    }


}
