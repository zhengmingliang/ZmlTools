/**
 * Created by 郑明亮 on 2022/3/20 11:56.
 */
package top.wys.utils;

import jdk.internal.util.Preconditions;
import jdk.internal.vm.annotation.ForceInline;
import top.wys.utils.collection.ArrayUtils;
import top.wys.utils.collection.Collections;
import top.wys.utils.convert.ConvertUtils;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.function.Supplier;

/**
 * <p> 对象工具类</p>
 *
 * @author 郑明亮
 * @time 2022/3/20 11:56
 * @since 2.0.0
 */
public class Objects {
    /**
     * 打开给定对象，该对象可能是被 {@link Optional} 包裹的对象
     *
     * @param obj 候选对象
     * @return 被 {@code Optional} 包裹的对象; 如果 {@code Optional} 为空, 或为null时，返回{@code null}
     * @since 5.0
     */
    @Nullable
    public static Object unwrapOptional(@Nullable Object obj) {
        if (obj instanceof Optional) {
            Optional<?> optional = (Optional<?>) obj;
            if (!optional.isPresent()) {
                return null;
            }
            Object result = optional.get();
            Assert.isTrue(!(result instanceof Optional), "Multi-level Optional usage not supported");
            return result;
        }
        return obj;
    }

    /**
     * 获取第一个对象值，当对象为空集合或空数组时，则返回null
     *
     * @param object
     * @return
     */
    public static Object getFirst(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Collection) {
            if (((Collection<?>) object).isEmpty()) {
                return null;
            }
            return ((Collection<?>) object).stream().findFirst();
        } else if (object instanceof Object[]) {
            Object[] arr = (Object[]) object;
            if (arr.length == 0) {
                return null;
            }
            return arr[0];
        } else {
            return object;
        }
    }

    /**
     * 获取第一个字符串
     *
     * @param object
     * @return
     */
    public static Object getFirstString(Object object) {
        return ConvertUtils.toString(getFirst(object));
    }

    /**
     * 获取第一个字符串
     *
     * @param object
     * @param defaultValue 当对象为空时，返回的默认字符串
     * @return
     */
    public static Object getFirstString(Object object, String defaultValue) {
        return ConvertUtils.toString(getFirst(object), defaultValue);
    }

    /**
     * 确定给定对象是否为空.
     * <p>此方法支持以下对象类型.
     * <ul>
     * <li>{@code Optional}: considered empty if {@link Optional#empty()}</li>
     * <li>{@code Array}: considered empty if its length is zero</li>
     * <li>{@link CharSequence}: considered empty if its length is zero</li>
     * <li>{@link Collection}: delegates to {@link Collection#isEmpty()}</li>
     * <li>{@link Map}: delegates to {@link Map#isEmpty()}</li>
     * </ul>
     * <p>If the given object is non-null and not one of the aforementioned
     * supported types, this method returns {@code false}.
     *
     * @param obj the object to check
     * @return {@code true} if the object is {@code null} or <em>empty</em>
     * @see Optional#isPresent()
     * @see Objects#isEmpty(Object[])
     * @see StringUtils#hasLength(CharSequence)
     * @see StringUtils#isEmpty(Object)
     * @see Collections#isEmpty(java.util.Collection)
     * @see Collections#isEmpty(java.util.Map)
     * @since 1.4.2
     */
    public static boolean isEmpty(@Nullable Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof Optional) {
            return !((Optional) obj).isPresent();
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        // else
        return false;
    }

    /**
     * 判断给定数组是否为空:
     * i.e. {@code null} or of zero length.
     *
     * @param array the array to check
     * @see #isEmpty(Object)
     */
    public static boolean isEmpty(@Nullable Object[] array) {
        return (array == null || array.length == 0);
    }


    /**
     * Returns {@code true} if the arguments are equal to each other
     * and {@code false} otherwise.
     * Consequently, if both arguments are {@code null}, {@code true}
     * is returned and if exactly one argument is {@code null}, {@code
     * false} is returned.  Otherwise, equality is determined by using
     * the {@link Object#equals equals} method of the first
     * argument.
     *
     * @param a an object
     * @param b an object to be compared with {@code a} for equality
     * @return {@code true} if the arguments are equal to each other
     * and {@code false} otherwise
     * @see Object#equals(Object)
     */
    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    /**
     * Returns {@code true} if the arguments are deeply equal to each other
     * and {@code false} otherwise.
     * <p>
     * Two {@code null} values are deeply equal.  If both arguments are
     * java.util.Arrays, the algorithm in {@link java.util.Arrays#deepEquals(Object[],
     * Object[]) java.util.Arrays.deepEquals} is used to determine equality.
     * Otherwise, equality is determined by using the {@link
     * Object#equals equals} method of the first argument.
     *
     * @param a an object
     * @param b an object to be compared with {@code a} for deep equality
     * @return {@code true} if the arguments are deeply equal to each other
     * and {@code false} otherwise
     * @see java.util.Arrays#deepEquals(Object[], Object[])
     * @see java.util.Objects#equals(Object, Object)
     */
    public static boolean deepEquals(Object a, Object b) {
        if (a == b)
            return true;
        else if (a == null || b == null)
            return false;
        else
            return ArrayUtils.deepEquals0(a, b);
    }

    /**
     * Returns the hash code of a non-{@code null} argument and 0 for
     * a {@code null} argument.
     *
     * @param o an object
     * @return the hash code of a non-{@code null} argument and 0 for
     * a {@code null} argument
     * @see Object#hashCode
     */
    public static int hashCode(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    /**
     * Generates a hash code for a sequence of input values. The hash
     * code is generated as if all the input values were placed into an
     * array, and that array were hashed by calling {@link
     * java.util.Arrays#hashCode(Object[])}.
     *
     * <p>This method is useful for implementing {@link
     * Object#hashCode()} on objects containing multiple fields. For
     * example, if an object that has three fields, {@code x}, {@code
     * y}, and {@code z}, one could write:
     *
     * <blockquote><pre>
     * &#064;Override public int hashCode() {
     *     return Objects.hash(x, y, z);
     * }
     * </pre></blockquote>
     *
     * <b>Warning: When a single object reference is supplied, the returned
     * value does not equal the hash code of that object reference.</b> This
     * value can be computed by calling {@link #hashCode(Object)}.
     *
     * @param values the values to be hashed
     * @return a hash value of the sequence of input values
     * @see java.util.Arrays#hashCode(Object[])
     * @see List#hashCode
     */
    public static int hash(Object... values) {
        return java.util.Arrays.hashCode(values);
    }

    /**
     * Returns the result of calling {@code toString} for a non-{@code
     * null} argument and {@code "null"} for a {@code null} argument.
     *
     * @param o an object
     * @return the result of calling {@code toString} for a non-{@code
     * null} argument and {@code "null"} for a {@code null} argument
     * @see Object#toString
     * @see String#valueOf(Object)
     */
    public static String toString(Object o) {
        return String.valueOf(o);
    }

    /**
     * Returns the result of calling {@code toString} on the first
     * argument if the first argument is not {@code null} and returns
     * the second argument otherwise.
     *
     * @param o           an object
     * @param nullDefault string to return if the first argument is
     *                    {@code null}
     * @return the result of calling {@code toString} on the first
     * argument if it is not {@code null} and the second argument
     * otherwise.
     * @see java.util.Objects#toString(Object)
     */
    public static String toString(Object o, String nullDefault) {
        return (o != null) ? o.toString() : nullDefault;
    }

    /**
     * Returns 0 if the arguments are identical and {@code
     * c.compare(a, b)} otherwise.
     * Consequently, if both arguments are {@code null} 0
     * is returned.
     *
     * <p>Note that if one of the arguments is {@code null}, a {@code
     * NullPointerException} may or may not be thrown depending on
     * what ordering policy, if any, the {@link Comparator Comparator}
     * chooses to have for {@code null} values.
     *
     * @param <T> the type of the objects being compared
     * @param a   an object
     * @param b   an object to be compared with {@code a}
     * @param c   the {@code Comparator} to compare the first two arguments
     * @return 0 if the arguments are identical and {@code
     * c.compare(a, b)} otherwise.
     * @see Comparable
     * @see Comparator
     */
    public static <T> int compare(T a, T b, Comparator<? super T> c) {
        return (a == b) ? 0 : c.compare(a, b);
    }

    /**
     * Checks that the specified object reference is not {@code null}. This
     * method is designed primarily for doing parameter validation in methods
     * and constructors, as demonstrated below:
     * <blockquote><pre>
     * public Foo(Bar bar) {
     *     this.bar = Objects.requireNonNull(bar);
     * }
     * </pre></blockquote>
     *
     * @param obj the object reference to check for nullity
     * @param <T> the type of the reference
     * @return {@code obj} if not {@code null}
     * @throws NullPointerException if {@code obj} is {@code null}
     */
    public static <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }

    /**
     * Checks that the specified object reference is not {@code null} and
     * throws a customized {@link NullPointerException} if it is. This method
     * is designed primarily for doing parameter validation in methods and
     * constructors with multiple parameters, as demonstrated below:
     * <blockquote><pre>
     * public Foo(Bar bar, Baz baz) {
     *     this.bar = Objects.requireNonNull(bar, "bar must not be null");
     *     this.baz = Objects.requireNonNull(baz, "baz must not be null");
     * }
     * </pre></blockquote>
     *
     * @param obj     the object reference to check for nullity
     * @param message detail message to be used in the event that a {@code
     *                NullPointerException} is thrown
     * @param <T>     the type of the reference
     * @return {@code obj} if not {@code null}
     * @throws NullPointerException if {@code obj} is {@code null}
     */
    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null)
            throw new NullPointerException(message);
        return obj;
    }

    /**
     * Returns {@code true} if the provided reference is {@code null} otherwise
     * returns {@code false}.
     *
     * @param obj a reference to be checked against {@code null}
     * @return {@code true} if the provided reference is {@code null} otherwise
     * {@code false}
     * @apiNote This method exists to be used as a
     * {@link java.util.function.Predicate}, {@code filter(Objects::isNull)}
     * @see java.util.function.Predicate
     * @since 1.8
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Returns {@code true} if the provided reference is non-{@code null}
     * otherwise returns {@code false}.
     *
     * @param obj a reference to be checked against {@code null}
     * @return {@code true} if the provided reference is non-{@code null}
     * otherwise {@code false}
     * @apiNote This method exists to be used as a
     * {@link java.util.function.Predicate}, {@code filter(Objects::nonNull)}
     * @see java.util.function.Predicate
     * @since 1.8
     */
    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    /**
     * Returns the first argument if it is non-{@code null} and
     * otherwise returns the non-{@code null} second argument.
     *
     * @param obj        an object
     * @param defaultObj a non-{@code null} object to return if the first argument
     *                   is {@code null}
     * @param <T>        the type of the reference
     * @return the first argument if it is non-{@code null} and
     * otherwise the second argument if it is non-{@code null}
     * @throws NullPointerException if both {@code obj} is null and
     *                              {@code defaultObj} is {@code null}
     * @since 9
     */
    public static <T> T requireNonNullElse(T obj, T defaultObj) {
        return (obj != null) ? obj : requireNonNull(defaultObj, "defaultObj");
    }

    /**
     * Returns the first argument if it is non-{@code null} and otherwise
     * returns the non-{@code null} value of {@code supplier.get()}.
     *
     * @param obj      an object
     * @param supplier of a non-{@code null} object to return if the first argument
     *                 is {@code null}
     * @param <T>      the type of the first argument and return type
     * @return the first argument if it is non-{@code null} and otherwise
     * the value from {@code supplier.get()} if it is non-{@code null}
     * @throws NullPointerException if both {@code obj} is null and
     *                              either the {@code supplier} is {@code null} or
     *                              the {@code supplier.get()} value is {@code null}
     * @since 9
     */
    public static <T> T requireNonNullElseGet(T obj, Supplier<? extends T> supplier) {
        return (obj != null) ? obj
                : requireNonNull(requireNonNull(supplier, "supplier").get(), "supplier.get()");
    }

    /**
     * Checks that the specified object reference is not {@code null} and
     * throws a customized {@link NullPointerException} if it is.
     *
     * <p>Unlike the method {@link #requireNonNull(Object, String)},
     * this method allows creation of the message to be deferred until
     * after the null check is made. While this may confer a
     * performance advantage in the non-null case, when deciding to
     * call this method care should be taken that the costs of
     * creating the message supplier are less than the cost of just
     * creating the string message directly.
     *
     * @param obj             the object reference to check for nullity
     * @param messageSupplier supplier of the detail message to be
     *                        used in the event that a {@code NullPointerException} is thrown
     * @param <T>             the type of the reference
     * @return {@code obj} if not {@code null}
     * @throws NullPointerException if {@code obj} is {@code null}
     * @since 1.8
     */
    public static <T> T requireNonNull(T obj, Supplier<String> messageSupplier) {
        if (obj == null)
            throw new NullPointerException(messageSupplier == null ?
                    null : messageSupplier.get());
        return obj;
    }

    /**
     * Checks if the {@code index} is within the bounds of the range from
     * {@code 0} (inclusive) to {@code length} (exclusive).
     *
     * <p>The {@code index} is defined to be out of bounds if any of the
     * following inequalities is true:
     * <ul>
     *  <li>{@code index < 0}</li>
     *  <li>{@code index >= length}</li>
     *  <li>{@code length < 0}, which is implied from the former inequalities</li>
     * </ul>
     *
     * @param index  the index
     * @param length the upper-bound (exclusive) of the range
     * @return {@code index} if it is within bounds of the range
     * @throws IndexOutOfBoundsException if the {@code index} is out of bounds
     * @since 9
     */
    @ForceInline
    public static int checkIndex(int index, int length) {
        return Preconditions.checkIndex(index, length, null);
    }

    /**
     * Checks if the sub-range from {@code fromIndex} (inclusive) to
     * {@code toIndex} (exclusive) is within the bounds of range from {@code 0}
     * (inclusive) to {@code length} (exclusive).
     *
     * <p>The sub-range is defined to be out of bounds if any of the following
     * inequalities is true:
     * <ul>
     *  <li>{@code fromIndex < 0}</li>
     *  <li>{@code fromIndex > toIndex}</li>
     *  <li>{@code toIndex > length}</li>
     *  <li>{@code length < 0}, which is implied from the former inequalities</li>
     * </ul>
     *
     * @param fromIndex the lower-bound (inclusive) of the sub-range
     * @param toIndex   the upper-bound (exclusive) of the sub-range
     * @param length    the upper-bound (exclusive) the range
     * @return {@code fromIndex} if the sub-range within bounds of the range
     * @throws IndexOutOfBoundsException if the sub-range is out of bounds
     * @since 9
     */
    public static int checkFromToIndex(int fromIndex, int toIndex, int length) {
        return Preconditions.checkFromToIndex(fromIndex, toIndex, length, null);
    }

    /**
     * Checks if the sub-range from {@code fromIndex} (inclusive) to
     * {@code fromIndex + size} (exclusive) is within the bounds of range from
     * {@code 0} (inclusive) to {@code length} (exclusive).
     *
     * <p>The sub-range is defined to be out of bounds if any of the following
     * inequalities is true:
     * <ul>
     *  <li>{@code fromIndex < 0}</li>
     *  <li>{@code size < 0}</li>
     *  <li>{@code fromIndex + size > length}, taking into account integer overflow</li>
     *  <li>{@code length < 0}, which is implied from the former inequalities</li>
     * </ul>
     *
     * @param fromIndex the lower-bound (inclusive) of the sub-interval
     * @param size      the size of the sub-range
     * @param length    the upper-bound (exclusive) of the range
     * @return {@code fromIndex} if the sub-range within bounds of the range
     * @throws IndexOutOfBoundsException if the sub-range is out of bounds
     * @since 9
     */
    public static int checkFromIndexSize(int fromIndex, int size, int length) {
        return Preconditions.checkFromIndexSize(fromIndex, size, length, null);
    }


    // Type safe getters
    //-------------------------------------------------------------------------


    /**
     * Gets a String from an Object in a null-safe manner.
     * <p>
     * The String is obtained via <code>toString</code>.
     *
     * @param object  the object to convert
     * @return the value of object as a String, <code>null</code> if null object input
     */
    public static String getString(final Object object) {
        if (object != null) {
            return object.toString();
        }
        return null;
    }

    /**
     * Gets a Boolean from an Object in a null-safe manner.
     * <p>
     * If the value is a <code>Boolean</code> it is returned directly.
     * If the value is a <code>String</code> and it equals 'true' ignoring case
     * then <code>true</code> is returned, otherwise <code>false</code>.
     * If the value is a <code>Number</code> an integer zero value returns
     * <code>false</code> and non-zero returns <code>true</code>.
     * Otherwise, <code>null</code> is returned.
     *
     * @param object  the object to convert
     * @return the value of object as a Boolean, <code>null</code> if null object input
     */
    public static Boolean getBoolean(final Object object) {
        if (object != null) {
            if (object instanceof Boolean) {
                return (Boolean) object;

            } else if (object instanceof String) {
                return new Boolean((String) object);

            } else if (object instanceof Number) {
                Number n = (Number) object;
                return (n.intValue() != 0) ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return null;
    }

    /**
     * Gets a Number from an Object in a null-safe manner.
     * <p>
     * If the value is a <code>Number</code> it is returned directly.
     * If the value is a <code>String</code> it is converted using
     * {@link NumberFormat#parse(String)} on the system default formatter
     * returning <code>null</code> if the conversion fails.
     * Otherwise, <code>null</code> is returned.
     *
     * @param object the object to convert
     * @return the object value in a Number, <code>null</code> if null object input
     */
    public static Number getNumber(final Object object) {
        if (object != null) {
            if (object instanceof Number) {
                return (Number) object;

            } else if (object instanceof String) {
                try {
                    String text = (String) object;
                    return NumberFormat.getInstance().parse(text);

                } catch (ParseException e) {
                    // failure means null is returned
                }
            }
        }
        return null;
    }

    /**
     * Gets a Byte from a Object in a null-safe manner.
     * <p>
     * The Byte is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object the object to convert
     * @return the value of object as a Byte, <code>null</code> if null object input
     */
    public static Byte getByte(final Object object) {
        Number answer = getNumber(object);
        if (answer == null) {
            return null;
        } else if (answer instanceof Byte) {
            return (Byte) answer;
        }
        return new Byte(answer.byteValue());
    }

    /**
     * Gets a Short from an object in a null-safe manner.
     * <p>
     * The Short is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object the object to convert
     * @return the value in the Object as a Short, <code>null</code> if null object input
     */
    public static Short getShort(final Object object) {
        Number answer = getNumber(object);
        if (answer == null) {
            return null;
        } else if (answer instanceof Short) {
            return (Short) answer;
        }
        return new Short(answer.shortValue());
    }

    /**
     * Gets a Integer from an Object in a null-safe manner.
     * <p>
     * The Integer is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object the object to convert
     * @return the value of object as a Integer, <code>null</code> if null object input
     */
    public static Integer getInteger(final Object object) {
        Number answer = getNumber(object);
        if (answer == null) {
            return null;
        } else if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return new Integer(answer.intValue());
    }

    /**
     * Gets a Long from an Object in a null-safe manner.
     * <p>
     * The Long is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object the object to convert
     * @return the value in the Object as a Long, <code>null</code> if null object input
     */
    public static Long getLong(final Object object) {
        Number answer = getNumber(object);
        if (answer == null) {
            return null;
        } else if (answer instanceof Long) {
            return (Long) answer;
        }
        return new Long(answer.longValue());
    }

    /**
     * Gets a Float from an Object in a null-safe manner.
     * <p>
     * The Float is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object the object to convert
     * @return the value of object as a Float, <code>null</code> if null object input
     */
    public static Float getFloat(final Object object) {
        Number answer = getNumber(object);
        if (answer == null) {
            return null;
        } else if (answer instanceof Float) {
            return (Float) answer;
        }
        return new Float(answer.floatValue());
    }

    /**
     * Gets a Double from an Object in a null-safe manner.
     * <p>
     * The Double is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object the object to convert
     * @return the value of object as a Double, <code>null</code> if null object input
     */
    public static Double getDouble(final Object object) {
        Number answer = getNumber(object);
        if (answer == null) {
            return null;
        } else if (answer instanceof Double) {
            return (Double) answer;
        }
        return new Double(answer.doubleValue());
    }

    /**
     * Gets a Map from an Object in a null-safe manner.
     * <p>
     * If the value returned from the specified map is not a Map then
     * <code>null</code> is returned.
     *
     * @param object  the object to convert
     * @return the value of object as a Map, <code>null</code> if null object input
     */
    public static Map getMap(final Object object) {
        if (object != null && object instanceof Map) {
            return (Map) object;
        }
        return null;
    }

    // Type safe getters with default values
    //-------------------------------------------------------------------------

    /**
     * converting null into the
     * given default value.
     *
     * @param object          the key of the value to convert
     * @param defaultValue what to return if the value is null
     * @return the value of the object, or defaultValue if the original value
     * is null or the object is null
     */
    public static Object getObject(Object object, Object defaultValue) {
        if (object != null) {
            return object;
        }
        return defaultValue;
    }

    /**
     * converting the object into
     * a string, using the default value if the value conversion fails.
     *
     * @param object          the key of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a string, or defaultValue if the
     * original value is null, the object is null or the string conversion
     * fails
     */
    public static String getString(Object object, String defaultValue) {
        String answer = getString(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     * converting the object into
     * a boolean, using the default value if the value conversion fails.
     *
     * @param object          the key of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a boolean, or defaultValue if the
     * original value is null, the Object is null or the boolean conversion
     * fails
     */
    public static Boolean getBoolean(Object object, Boolean defaultValue) {
        Boolean answer = getBoolean(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     * Looks up the given object ,converting the object into
     * a number, using the default value if the value conversion fails.
     *
     * @param object       the object of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a number, or defaultValue if the
     * original value is null, the object is null or the number conversion
     * fails
     */
    public static Number getNumber(Object object, Number defaultValue) {
        Number answer = getNumber(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     * converting the object into
     * a byte, using the default value if the value conversion fails.
     *
     * @param object          the key of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a number, or defaultValue if the
     * original value is null, the object is null or the number conversion
     * fails
     */
    public static Byte getByte(Object object, Byte defaultValue) {
        Byte answer = getByte(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     * converting the object into
     * a short, using the default value if the value conversion fails.
     *
     * @param object       the object of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a number, or defaultValue if the
     * original value is null, the object is null or the number conversion
     * fails
     */
    public static Short getShort(Object object, Short defaultValue) {
        Short answer = getShort(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     * converting the object into
     * an integer, using the default value if the value conversion fails.
     *
     * @param object       the object of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a number, or defaultValue if the
     * original value is null, the object is null or the number conversion
     * fails
     */
    public static Integer getInteger(Object object, Integer defaultValue) {
        Integer answer = getInteger(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     * converting the object into
     * a long, using the default value if the value conversion fails.
     *
     * @param object          the object of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a number, or defaultValue if the
     * original value is null, the object is null or the number conversion
     * fails
     */
    public static Long getLong(Object object, Long defaultValue) {
        Long answer = getLong(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     * converting the object into
     * a float, using the default value if the value conversion fails.
     *
     * @param object          the object of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a number, or defaultValue if the
     * original value is null, the object is null or the number conversion
     * fails
     */
    public static Float getFloat(Object object, Float defaultValue) {
        Float answer = getFloat(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     * converting the object into
     * a double, using the default value if the value conversion fails.
     *
     * @param object          the object of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a number, or defaultValue if the
     * original value is null, the object is null or the number conversion
     * fails
     */
    public static Double getDouble(Object object, Double defaultValue) {
        Double answer = getDouble(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     * converting the object into
     * a map, using the default value if the value conversion fails.
     *
     * @param object          the object of the value to convert
     * @param defaultValue what to return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a number, or defaultValue if the
     * original value is null, the object is null or the map conversion
     * fails
     */
    public static Map getMap(Object object, Map defaultValue) {
        Map answer = getMap(object);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }


    // Type safe primitive getters
    //-------------------------------------------------------------------------

    /**
     * Gets a boolean from an Object in a null-safe manner.
     * <p>
     * If the value is a <code>Boolean</code> its value is returned.
     * If the value is a <code>String</code> and it equals 'true' ignoring case
     * then <code>true</code> is returned, otherwise <code>false</code>.
     * If the value is a <code>Number</code> an integer zero value returns
     * <code>false</code> and non-zero returns <code>true</code>.
     * Otherwise, <code>false</code> is returned.
     *
     * @param object  the object to convert
     * @return the value of object as a Boolean, <code>false</code> if null object input
     */
    public static boolean getBooleanValue(final Object object) {
        Boolean booleanObject = getBoolean(object);
        if (booleanObject == null) {
            return false;
        }
        return booleanObject.booleanValue();
    }

    /**
     * Gets a byte from an Object in a null-safe manner.
     * <p>
     * The byte is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object  the object to convert
     * @return the value of object as a byte, <code>0</code> if null object input
     */
    public static byte getByteValue(final Object object) {
        Byte byteObject = getByte(object);
        if (byteObject == null) {
            return 0;
        }
        return byteObject.byteValue();
    }

    /**
     * Gets a short from an Object in a null-safe manner.
     * <p>
     * The short is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object  the object to convert
     * @return the value of object as a short, <code>0</code> if null object input
     */
    public static short getShortValue(final Object object) {
        Short shortObject = getShort(object);
        if (shortObject == null) {
            return 0;
        }
        return shortObject.shortValue();
    }

    /**
     * Gets an int from an Object in a null-safe manner.
     * <p>
     * The int is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object  the object to convert
     * @return the value of object as an int, <code>0</code> if null object input
     */
    public static int getIntValue(final Object object) {
        Integer integerObject = getInteger(object);
        if (integerObject == null) {
            return 0;
        }
        return integerObject.intValue();
    }

    /**
     * Gets a long from an Object in a null-safe manner.
     * <p>
     * The long is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object  the object to convert
     * @return the value of object as a long, <code>0L</code> if null object input
     */
    public static long getLongValue(final Object object) {
        Long longObject = getLong(object);
        if (longObject == null) {
            return 0L;
        }
        return longObject.longValue();
    }

    /**
     * Gets a float from an Object in a null-safe manner.
     * <p>
     * The float is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object  the object to convert
     * @return the value of object as a float, <code>0.0F</code> if null object input
     */
    public static float getFloatValue(final Object object) {
        Float floatObject = getFloat(object);
        if (floatObject == null) {
            return 0f;
        }
        return floatObject.floatValue();
    }

    /**
     * Gets a double from an Object in a null-safe manner.
     * <p>
     * The double is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object  the object to convert
     * @return the value of object as a double, <code>0.0</code> if null object input
     */
    public static double getDoubleValue(final Object object) {
        Double doubleObject = getDouble(object);
        if (doubleObject == null) {
            return 0d;
        }
        return doubleObject.doubleValue();
    }

    // Type safe primitive getters with default values
    //-------------------------------------------------------------------------

    /**
     * Gets a boolean from an Object in a null-safe manner,
     * using the default value if the value conversion fails.
     * <p>
     * If the value is a <code>Boolean</code> its value is returned.
     * If the value is a <code>String</code> and it equals 'true' ignoring case
     * then <code>true</code> is returned, otherwise <code>false</code>.
     * If the value is a <code>Number</code> an integer zero value returns
     * <code>false</code> and non-zero returns <code>true</code>.
     * Otherwise, <code>defaultValue</code> is returned.
     *
     * @param object          the object to convert
     * @param defaultValue return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a Boolean, <code>defaultValue</code> if null object input
     */
    public static boolean getBooleanValue(final Object object, boolean defaultValue) {
        Boolean booleanObject = getBoolean(object);
        if (booleanObject == null) {
            return defaultValue;
        }
        return booleanObject.booleanValue();
    }

    /**
     * Gets a byte from an Object in a null-safe manner,
     * using the default value if the value conversion fails.
     * <p>
     * The byte is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object          the object to convert
     * @param defaultValue return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a byte, <code>defaultValue</code> if null object input
     */
    public static byte getByteValue(final Object object, byte defaultValue) {
        Byte byteObject = getByte(object);
        if (byteObject == null) {
            return defaultValue;
        }
        return byteObject.byteValue();
    }

    /**
     * Gets a short from an Object in a null-safe manner,
     * using the default value if the value conversion fails.
     * <p>
     * The short is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object          the object to convert
     * @param defaultValue return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a short, <code>defaultValue</code> if null object input
     */
    public static short getShortValue(final Object object, short defaultValue) {
        Short shortObject = getShort(object);
        if (shortObject == null) {
            return defaultValue;
        }
        return shortObject.shortValue();
    }

    /**
     * Gets an int from an Object in a null-safe manner,
     * using the default value if the value conversion fails.
     * <p>
     * The int is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object          the object to convert
     * @param defaultValue return if the value is null or if the
     *                     conversion fails
     * @return the value of object as an int, <code>defaultValue</code> if null object input
     */
    public static int getIntValue(final Object object, int defaultValue) {
        Integer integerObject = getInteger(object);
        if (integerObject == null) {
            return defaultValue;
        }
        return integerObject.intValue();
    }

    /**
     * Gets a long from an Object in a null-safe manner,
     * using the default value if the value conversion fails.
     * <p>
     * The long is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object          the object to convert
     * @param defaultValue return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a long, <code>defaultValue</code> if null object input
     */
    public static long getLongValue(final Object object, long defaultValue) {
        Long longObject = getLong(object);
        if (longObject == null) {
            return defaultValue;
        }
        return longObject.longValue();
    }

    /**
     * Gets a float from an Object in a null-safe manner,
     * using the default value if the value conversion fails.
     * <p>
     * The float is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object          the object to convert
     * @param defaultValue return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a float, <code>defaultValue</code> if null object input
     */
    public static float getFloatValue(final Object object, float defaultValue) {
        Float floatObject = getFloat(object);
        if (floatObject == null) {
            return defaultValue;
        }
        return floatObject.floatValue();
    }

    /**
     * Gets a double from an Object in a null-safe manner,
     * using the default value if the value conversion fails.
     * <p>
     * The double is obtained from the results of {@link #getNumber(Object)}.
     *
     * @param object       对象
     * @param defaultValue return if the value is null or if the
     *                     conversion fails
     * @return the value of object as a double, <code>defaultValue</code> if null object input
     */
    public static double getDoubleValue(final Object object, double defaultValue) {
        Double doubleObject = getDouble(object);
        if (doubleObject == null) {
            return defaultValue;
        }
        return doubleObject.doubleValue();
    }

    // Conversion methods
    //-------------------------------------------------------------------------

}
