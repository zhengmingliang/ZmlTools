package top.wys.utils.collection;

import java.lang.reflect.Array;
import java.util.Arrays;

import static java.util.Arrays.deepEquals;

/**
 * 数组工具类
 *
 * @since 1.4.2
 */
public class ArrayUtils {

    /**
     * An empty immutable {@code String} array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * 数组是否为空
     *
     * @param arr 数组
     * @return
     */
    public static boolean isEmpty(Object[] arr) {
        if (arr == null) {
            return true;
        }
        if (arr.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * <p>Checks if an array of primitive booleans is empty or {@code null}.
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 1.4.5
     */
    public static boolean isEmpty(final boolean[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive bytes is empty or {@code null}.
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 1.4.5
     */
    public static boolean isEmpty(final byte[] array) {
        return getLength(array) == 0;
    }

    // IndexOf search
    // ----------------------------------------------------------------------

    /**
     * <p>Checks if an array of primitive chars is empty or {@code null}.
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 1.4.5
     */
    public static boolean isEmpty(final char[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive doubles is empty or {@code null}.
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 1.4.5
     */
    public static boolean isEmpty(final double[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive floats is empty or {@code null}.
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 1.4.5
     */
    public static boolean isEmpty(final float[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive ints is empty or {@code null}.
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 1.4.5
     */
    public static boolean isEmpty(final int[] array) {
        return getLength(array) == 0;
    }



    /**
     * <p>Checks if an array of primitive longs is empty or {@code null}.
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 1.4.5
     */
    public static boolean isEmpty(final long[] array) {
        return getLength(array) == 0;
    }

    /**
     * 数组是否不为空
     *
     * @param arr 数组
     * @return
     */
    public static boolean isNotEmpty(Object[] arr) {
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
     * @param array the array to retrieve the length from, may be null
     * @return The length of the array, or {@code 0} if the array is {@code null}
     * @throws IllegalArgumentException if the object argument is not an array.
     * @since 1.4.3
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
        if (e1 instanceof Object[] && e2 instanceof Object[]) {
            eq = deepEquals((Object[]) e1, (Object[]) e2);
        } else if (e1 instanceof byte[] && e2 instanceof byte[]) {
            eq = Arrays.equals((byte[]) e1, (byte[]) e2);
        } else if (e1 instanceof short[] && e2 instanceof short[]) {
            eq = Arrays.equals((short[]) e1, (short[]) e2);
        } else if (e1 instanceof int[] && e2 instanceof int[]) {
            eq = Arrays.equals((int[]) e1, (int[]) e2);
        } else if (e1 instanceof long[] && e2 instanceof long[]) {
            eq = Arrays.equals((long[]) e1, (long[]) e2);
        } else if (e1 instanceof char[] && e2 instanceof char[]) {
            eq = Arrays.equals((char[]) e1, (char[]) e2);
        } else if (e1 instanceof float[] && e2 instanceof float[]) {
            eq = Arrays.equals((float[]) e1, (float[]) e2);
        } else if (e1 instanceof double[] && e2 instanceof double[]) {
            eq = Arrays.equals((double[]) e1, (double[]) e2);
        } else if (e1 instanceof boolean[] && e2 instanceof boolean[]) {
            eq = Arrays.equals((boolean[]) e1, (boolean[]) e2);
        } else {
            eq = e1.equals(e2);
        }
        return eq;
    }

    /**
     * 获取数组中的第一个元素
     *
     * @param array
     * @param <T>
     * @return
     * @since 1.4.3
     */
    public static <T> T getFirst(T[] array) {
        return getFirst(array, null);
    }


    /**
     * 获取数组中的第一个元素
     *
     * @param array        数组
     * @param defaultValue 数组为空时返回的默认值
     * @param <T>
     * @return
     * @since 1.4.3
     */
    public static <T> T getFirst(T[] array, T defaultValue) {
        if (array == null || array.length == 0) {
            return defaultValue;
        }
        return array[0];
    }



    /**
     * Gets the nTh element of an array or null if the index is out of bounds or the array is null.
     *
     * @param <T> The type of array elements.
     * @param array The array to index.
     * @param index The index
     * @return the nTh element of an array or null if the index is out of bounds or the array is null.
     * @since 3.11
     */
    public static <T> T get(final T[] array, final int index) {
        return get(array, index, null);
    }

    /**
     * Gets the nTh element of an array or a default value if the index is out of bounds.
     *
     * @param <T> The type of array elements.
     * @param array The array to index.
     * @param index The index
     * @param defaultValue The return value of the given index is out of bounds.
     * @return the nTh element of an array or a default value if the index is out of bounds.
     * @since 3.11
     */
    public static <T> T get(final T[] array, final int index, final T defaultValue) {
        return isArrayIndexValid(array, index) ? array[index] : defaultValue;
    }

    /**
     * Returns whether a given array can safely be accessed at the given index.
     *
     * <pre>
     * ArrayUtils.isArrayIndexValid(null, 0)       = false
     * ArrayUtils.isArrayIndexValid([], 0)         = false
     * ArrayUtils.isArrayIndexValid(["a"], 0)      = true
     * </pre>
     *
     * @param <T> the component type of the array
     * @param array the array to inspect, may be null
     * @param index the index of the array to be inspected
     * @return Whether the given index is safely-accessible in the given array
     * @since 3.8
     */
    public static <T> boolean isArrayIndexValid(final T[] array, final int index) {
        return index >= 0 && getLength(array) > index;
    }

    /**
     * Reverses the order of the given array.
     *
     * @param array  the array to reverse
     */
    public static void reverseArray(Object[] array) {
        int i = 0;
        int j = array.length - 1;
        Object tmp;

        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    public static void main(String[] args) {
        boolean[] arr = null;
        System.out.println(isEmpty(arr));
    }

}
