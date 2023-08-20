package top.wys.utils.collection;

import java.lang.reflect.Array;
import java.util.Arrays;

import static java.util.Arrays.deepEquals;

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




    public static boolean deepEquals0(Object e1, Object e2) {
        assert e1 != null;
        boolean eq;
        if (e1 instanceof Object[] && e2 instanceof Object[])
            eq = deepEquals ((Object[]) e1, (Object[]) e2);
        else if (e1 instanceof byte[] && e2 instanceof byte[])
            eq = Arrays.equals((byte[]) e1, (byte[]) e2);
        else if (e1 instanceof short[] && e2 instanceof short[])
            eq = Arrays.equals((short[]) e1, (short[]) e2);
        else if (e1 instanceof int[] && e2 instanceof int[])
            eq = Arrays.equals((int[]) e1, (int[]) e2);
        else if (e1 instanceof long[] && e2 instanceof long[])
            eq = Arrays.equals((long[]) e1, (long[]) e2);
        else if (e1 instanceof char[] && e2 instanceof char[])
            eq = Arrays.equals((char[]) e1, (char[]) e2);
        else if (e1 instanceof float[] && e2 instanceof float[])
            eq = Arrays.equals((float[]) e1, (float[]) e2);
        else if (e1 instanceof double[] && e2 instanceof double[])
            eq = Arrays.equals((double[]) e1, (double[]) e2);
        else if (e1 instanceof boolean[] && e2 instanceof boolean[])
            eq = Arrays.equals((boolean[]) e1, (boolean[]) e2);
        else
            eq = e1.equals(e2);
        return eq;
    }

    /**
     * 获取数组中的第一个元素
     * @param array
     * @since 1.4.3
     * @return
     * @param <T>
     */
    public static <T>  T getFirst(T [] array){
        return getFirst(array,null);
    }


    /**
     * 获取数组中的第一个元素
     * @param array 数组
     * @param defaultValue 数组为空时返回的默认值
     * @since 1.4.3
     * @return
     * @param <T>
     */
    public static <T>  T getFirst(T [] array,T defaultValue){
        if (array == null || array.length == 0) {
            return defaultValue;
        }
        return array[0];
    }
}
