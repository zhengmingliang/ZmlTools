/**
 * Created by 郑明亮 on 2023/8/13 16:02.
 */
package top.wys.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * <p> 类型处理工具类</p>
 *
 * @author 郑明亮
 * @time 2023/8/13 16:02
 * @since 1.4.3
 */
public class TypeUtils {
    private static final BiMap<Class<?>, Class<?>> primWrapMap = HashBiMap.create(9);

    static {
        primWrapMap.put(boolean.class, Boolean.class);
        primWrapMap.put(byte.class, Byte.class);
        primWrapMap.put(char.class, Character.class);
        primWrapMap.put(double.class, Double.class);
        primWrapMap.put(float.class, Float.class);
        primWrapMap.put(int.class, Integer.class);
        primWrapMap.put(long.class, Long.class);
        primWrapMap.put(short.class, Short.class);
        primWrapMap.put(void.class, Void.class);
    }

    /**
     * 是否是装包类型
     * @param clz
     * @return
     */

    public static boolean isBoxed(Class clz){
        return primWrapMap.containsValue(clz);
    }

    /**
     * 获取基本类型的装包类型,如果不是基本类型，则返回传入的类型
     * @param clz
     * @return
     */
    public static Class<?> getBoxedType(Class<?> clz){
        if(clz.isPrimitive()){
            return primWrapMap.get(clz);
        }
        return clz;
    }


    /**
     * 获取拆箱后的类型
     * @param clz
     * @return
     */
    public static Class<?> getUnBoxedType(Class<?> clz){
        if (!clz.isPrimitive()) {
            return clz;
        }
        Class<?> aClass = primWrapMap.inverse().get(clz);
        return aClass == null ? clz : aClass;
    }
}
