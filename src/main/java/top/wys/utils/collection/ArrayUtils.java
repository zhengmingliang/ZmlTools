package top.wys.utils.collection;

import java.lang.reflect.Array;

/**
 * 数组工具类
 * @since 1.4.2
 */
public class ArrayUtils {

    /**
     * 数组是否为空
     * @param arr 数组
     * @return
     */
    public static boolean isEmpty(Object[] arr){
        if(arr == null){
            return true;
        }
        if (arr.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 数组是否不为空
     * @param arr 数组
     * @return
     */
    public static boolean isNotEmpty(Object[]  arr){
        return !isEmpty(arr);
    }

    //-----------------------------------------------------------------------
    /**
     * <p>Returns the length of the specified array.
     * This method can deal with {@code Object} arrays and with primitive arrays.
     *
     * <p>If the input array is {@code null}, {@code 0} is returned.
     *
     * <pre>
     * ArrayUtils.getLength(null)            = 0
     * ArrayUtils.getLength([])              = 0
     * ArrayUtils.getLength([null])          = 1
     * ArrayUtils.getLength([true, false])   = 2
     * ArrayUtils.getLength([1, 2, 3])       = 3
     * ArrayUtils.getLength(["a", "b", "c"]) = 3
     * </pre>
     *
     * @param array  the array to retrieve the length from, may be null
     * @return The length of the array, or {@code 0} if the array is {@code null}
     * @throws IllegalArgumentException if the object argument is not an array.
     * @since 2.1
     */
    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }
}
