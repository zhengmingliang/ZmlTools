package top.wys.utils.collection;

import top.wys.utils.valid.Preconditions;

import javax.annotation.Nullable;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Maps {
    public static final Map EMPTY_MAP = Collections.emptyMap();

    /**
     * String used to indent the verbose and debug Map prints.
     */
    private static final String INDENT_STRING = "    ";

    /**
     * <code>Maps</code> should not normally be instantiated.
     */
    private Maps() {
    }

    /**
     * Creates a <i>mutable</i>, empty {@code HashMap} instance.
     *
     * <p><b>Note:</b> if {@code K} is an {@code enum} type, use {@link #newEnumMap} instead.
     *
     * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
     * deprecated. Instead, use the {@code HashMap} constructor directly, taking advantage of the new
     * <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     *
     * @return a new, empty {@code HashMap}
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }


    /**
     * Creates a <i>mutable</i> {@code HashMap} instance with the same mappings as the specified map.
     *
     * <p><b>Note:</b> if {@code K} is an {@link Enum} type, use {@link #newEnumMap} instead.
     *
     * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
     * deprecated. Instead, use the {@code HashMap} constructor directly, taking advantage of the new
     * <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     *
     * @param map the mappings to be placed in the new map
     * @return a new {@code HashMap} initialized with the mappings from {@code map}
     */
    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }


    /**
     * Creates a {@code HashMap} instance, with a high enough "initial capacity" that it <i>should</i>
     * hold {@code expectedSize} elements without growth. This behavior cannot be broadly guaranteed,
     * but it is observed to be true for OpenJDK 1.7. It also can't be guaranteed that the method
     * isn't inadvertently <i>oversizing</i> the returned map.
     *
     * @param expectedSize the number of entries you expect to add to the returned map
     * @return a new, empty {@code HashMap} with enough capacity to hold {@code expectedSize} entries
     *     without resizing
     * @throws IllegalArgumentException if {@code expectedSize} is negative
     */
    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int expectedSize) {
        return new HashMap<>(expectedSize);
    }


    /**
     * Creates a <i>mutable</i>, empty, insertion-ordered {@code LinkedHashMap} instance.
     *
     * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
     * deprecated. Instead, use the {@code LinkedHashMap} constructor directly, taking advantage of
     * the new <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     *
     * @return a new, empty {@code LinkedHashMap}
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    /**
     * Creates a <i>mutable</i>, insertion-ordered {@code LinkedHashMap} instance with the same
     * mappings as the specified map.
     *
     * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
     * deprecated. Instead, use the {@code LinkedHashMap} constructor directly, taking advantage of
     * the new <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     *
     * @param map the mappings to be placed in the new map
     * @return a new, {@code LinkedHashMap} initialized with the mappings from {@code map}
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
        return new LinkedHashMap<>(map);
    }

    /**
     * Creates a {@code LinkedHashMap} instance, with a high enough "initial capacity" that it
     * <i>should</i> hold {@code expectedSize} elements without growth. This behavior cannot be
     * broadly guaranteed, but it is observed to be true for OpenJDK 1.7. It also can't be guaranteed
     * that the method isn't inadvertently <i>oversizing</i> the returned map.
     *
     * @param expectedSize the number of entries you expect to add to the returned map
     * @return a new, empty {@code LinkedHashMap} with enough capacity to hold {@code expectedSize}
     *     entries without resizing
     * @throws IllegalArgumentException if {@code expectedSize} is negative
     * @since 19.0
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int expectedSize) {
        return new LinkedHashMap<>(expectedSize);
    }

    /**
     * Creates a new empty {@link ConcurrentHashMap} instance.
     *
     * @since 3.0
     */
    public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
        return new ConcurrentHashMap<>();
    }

    /**
     * Creates a <i>mutable</i>, empty {@code TreeMap} instance using the natural ordering of its
     * elements.
     *
     * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
     * deprecated. Instead, use the {@code TreeMap} constructor directly, taking advantage of the new
     * <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     *
     * @return a new, empty {@code TreeMap}
     */
    public static <K extends Comparable, V> TreeMap<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    /**
     * Creates a <i>mutable</i> {@code TreeMap} instance with the same mappings as the specified map
     * and using the same ordering as the specified map.
     *
     * <p><b>Note:</b> if mutability is not required, use {@link
     *
     * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
     * deprecated. Instead, use the {@code TreeMap} constructor directly, taking advantage of the new
     * <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     *
     * @param map the sorted map whose mappings are to be placed in the new map and whose comparator
     *     is to be used to sort the new map
     * @return a new {@code TreeMap} initialized with the mappings from {@code map} and using the
     *     comparator of {@code map}
     */
    public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> map) {
        return new TreeMap<>(map);
    }

    /**
     * Creates a <i>mutable</i>, empty {@code TreeMap} instance using the given comparator.
     *
     * <p><b>Note:</b> if mutability is not required, use {@code
     * ImmutableSortedMap.orderedBy(comparator).build()} instead.
     *
     * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
     * deprecated. Instead, use the {@code TreeMap} constructor directly, taking advantage of the new
     * <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     *
     * @param comparator the comparator to sort the keys with
     * @return a new, empty {@code TreeMap}
     */
    public static <C, K extends C, V> TreeMap<K, V> newTreeMap(@Nullable Comparator<C> comparator) {
        // Ideally, the extra type parameter "C" shouldn't be necessary. It is a
        // work-around of a compiler type inference quirk that prevents the
        // following code from being compiled:
        // Comparator<Class<?>> comparator = null;
        // Map<Class<? extends Throwable>, String> map = newTreeMap(comparator);
        return new TreeMap<>(comparator);
    }


    /**
     * Creates an {@code EnumMap} instance.
     *
     * @param type the key type for this map
     * @return a new, empty {@code EnumMap}
     */
    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> type) {
        return new EnumMap<>(Preconditions.checkNotNull(type));
    }

    /**
     * Creates an {@code EnumMap} with the same mappings as the specified map.
     *
     * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
     * deprecated. Instead, use the {@code EnumMap} constructor directly, taking advantage of the new
     * <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     *
     * @param map the map from which to initialize this {@code EnumMap}
     * @return a new {@code EnumMap} initialized with the mappings from {@code map}
     * @throws IllegalArgumentException if {@code m} is not an {@code EnumMap} instance and contains
     *     no mappings
     */
    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Map<K, ? extends V> map) {
        return new EnumMap<>(map);
    }

    /**
     * Creates an {@code IdentityHashMap} instance.
     *
     * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
     * deprecated. Instead, use the {@code IdentityHashMap} constructor directly, taking advantage of
     * the new <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     *
     * @return a new, empty {@code IdentityHashMap}
     */
    public static <K, V> IdentityHashMap<K, V> newIdentityHashMap() {
        return new IdentityHashMap<>();
    }


    // Type safe getters
    //-------------------------------------------------------------------------
    /**
     * Gets from a Map in a null-safe manner.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map, <code>null</code> if null map input
     */
    public static Object getObject(final Map map, final Object key) {
        if (map != null) {
            return map.get(key);
        }
        return null;
    }

    /**
     * Gets a String from a Map in a null-safe manner.
     * <p>
     * The String is obtained via <code>toString</code>.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a String, <code>null</code> if null map input
     */
    public static String getString(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                return answer.toString();
            }
        }
        return null;
    }

    /**
     * Gets a Boolean from a Map in a null-safe manner.
     * <p>
     * If the value is a <code>Boolean</code> it is returned directly.
     * If the value is a <code>String</code> and it equals 'true' ignoring case
     * then <code>true</code> is returned, otherwise <code>false</code>.
     * If the value is a <code>Number</code> an integer zero value returns
     * <code>false</code> and non-zero returns <code>true</code>.
     * Otherwise, <code>null</code> is returned.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Boolean, <code>null</code> if null map input
     */
    public static Boolean getBoolean(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                if (answer instanceof Boolean) {
                    return (Boolean) answer;

                } else if (answer instanceof String) {
                    return new Boolean((String) answer);

                } else if (answer instanceof Number) {
                    Number n = (Number) answer;
                    return (n.intValue() != 0) ? Boolean.TRUE : Boolean.FALSE;
                }
            }
        }
        return null;
    }

    /**
     * Gets a Number from a Map in a null-safe manner.
     * <p>
     * If the value is a <code>Number</code> it is returned directly.
     * If the value is a <code>String</code> it is converted using
     * {@link NumberFormat#parse(String)} on the system default formatter
     * returning <code>null</code> if the conversion fails.
     * Otherwise, <code>null</code> is returned.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Number, <code>null</code> if null map input
     */
    public static Number getNumber(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                if (answer instanceof Number) {
                    return (Number) answer;

                } else if (answer instanceof String) {
                    try {
                        String text = (String) answer;
                        return NumberFormat.getInstance().parse(text);

                    } catch (ParseException e) {
                        // failure means null is returned
                    }
                }
            }
        }
        return null;
    }

    /**
     * Gets a Byte from a Map in a null-safe manner.
     * <p>
     * The Byte is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Byte, <code>null</code> if null map input
     */
    public static Byte getByte(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Byte) {
            return (Byte) answer;
        }
        return new Byte(answer.byteValue());
    }

    /**
     * Gets a Short from a Map in a null-safe manner.
     * <p>
     * The Short is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Short, <code>null</code> if null map input
     */
    public static Short getShort(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Short) {
            return (Short) answer;
        }
        return new Short(answer.shortValue());
    }

    /**
     * Gets a Integer from a Map in a null-safe manner.
     * <p>
     * The Integer is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Integer, <code>null</code> if null map input
     */
    public static Integer getInteger(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return new Integer(answer.intValue());
    }

    /**
     * Gets a Long from a Map in a null-safe manner.
     * <p>
     * The Long is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Long, <code>null</code> if null map input
     */
    public static Long getLong(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Long) {
            return (Long) answer;
        }
        return new Long(answer.longValue());
    }

    /**
     * Gets a Float from a Map in a null-safe manner.
     * <p>
     * The Float is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Float, <code>null</code> if null map input
     */
    public static Float getFloat(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Float) {
            return (Float) answer;
        }
        return new Float(answer.floatValue());
    }

    /**
     * Gets a Double from a Map in a null-safe manner.
     * <p>
     * The Double is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Double, <code>null</code> if null map input
     */
    public static Double getDouble(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Double) {
            return (Double) answer;
        }
        return new Double(answer.doubleValue());
    }

    /**
     * Gets a Map from a Map in a null-safe manner.
     * <p>
     * If the value returned from the specified map is not a Map then
     * <code>null</code> is returned.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Map, <code>null</code> if null map input
     */
    public static Map getMap(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null && answer instanceof Map) {
                return (Map) answer;
            }
        }
        return null;
    }

    // Type safe getters with default values
    //-------------------------------------------------------------------------
    /**
     *  Looks up the given key in the given map, converting null into the
     *  given default value.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null
     *  @return  the value in the map, or defaultValue if the original value
     *    is null or the map is null
     */
    public static Object getObject( Map map, Object key, Object defaultValue ) {
        if ( map != null ) {
            Object answer = map.get( key );
            if ( answer != null ) {
                return answer;
            }
        }
        return defaultValue;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  a string, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a string, or defaultValue if the
     *    original value is null, the map is null or the string conversion
     *    fails
     */
    public static String getString( Map map, Object key, String defaultValue ) {
        String answer = getString( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  a boolean, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a boolean, or defaultValue if the
     *    original value is null, the map is null or the boolean conversion
     *    fails
     */
    public static Boolean getBoolean( Map map, Object key, Boolean defaultValue ) {
        Boolean answer = getBoolean( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  a number, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a number, or defaultValue if the
     *    original value is null, the map is null or the number conversion
     *    fails
     */
    public static Number getNumber( Map map, Object key, Number defaultValue ) {
        Number answer = getNumber( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  a byte, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a number, or defaultValue if the
     *    original value is null, the map is null or the number conversion
     *    fails
     */
    public static Byte getByte( Map map, Object key, Byte defaultValue ) {
        Byte answer = getByte( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  a short, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a number, or defaultValue if the
     *    original value is null, the map is null or the number conversion
     *    fails
     */
    public static Short getShort( Map map, Object key, Short defaultValue ) {
        Short answer = getShort( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  an integer, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a number, or defaultValue if the
     *    original value is null, the map is null or the number conversion
     *    fails
     */
    public static Integer getInteger( Map map, Object key, Integer defaultValue ) {
        Integer answer = getInteger( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  a long, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a number, or defaultValue if the
     *    original value is null, the map is null or the number conversion
     *    fails
     */
    public static Long getLong( Map map, Object key, Long defaultValue ) {
        Long answer = getLong( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  a float, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a number, or defaultValue if the
     *    original value is null, the map is null or the number conversion
     *    fails
     */
    public static Float getFloat( Map map, Object key, Float defaultValue ) {
        Float answer = getFloat( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  a double, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a number, or defaultValue if the
     *    original value is null, the map is null or the number conversion
     *    fails
     */
    public static Double getDouble( Map map, Object key, Double defaultValue ) {
        Double answer = getDouble( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }

    /**
     *  Looks up the given key in the given map, converting the result into
     *  a map, using the default value if the the conversion fails.
     *
     *  @param map  the map whose value to look up
     *  @param key  the key of the value to look up in that map
     *  @param defaultValue  what to return if the value is null or if the
     *     conversion fails
     *  @return  the value in the map as a number, or defaultValue if the
     *    original value is null, the map is null or the map conversion
     *    fails
     */
    public static Map getMap( Map map, Object key, Map defaultValue ) {
        Map answer = getMap( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }


    // Type safe primitive getters
    //-------------------------------------------------------------------------
    /**
     * Gets a boolean from a Map in a null-safe manner.
     * <p>
     * If the value is a <code>Boolean</code> its value is returned.
     * If the value is a <code>String</code> and it equals 'true' ignoring case
     * then <code>true</code> is returned, otherwise <code>false</code>.
     * If the value is a <code>Number</code> an integer zero value returns
     * <code>false</code> and non-zero returns <code>true</code>.
     * Otherwise, <code>false</code> is returned.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a Boolean, <code>false</code> if null map input
     */
    public static boolean getBooleanValue(final Map map, final Object key) {
        Boolean booleanObject = getBoolean(map, key);
        if (booleanObject == null) {
            return false;
        }
        return booleanObject.booleanValue();
    }

    /**
     * Gets a byte from a Map in a null-safe manner.
     * <p>
     * The byte is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a byte, <code>0</code> if null map input
     */
    public static byte getByteValue(final Map map, final Object key) {
        Byte byteObject = getByte(map, key);
        if (byteObject == null) {
            return 0;
        }
        return byteObject.byteValue();
    }

    /**
     * Gets a short from a Map in a null-safe manner.
     * <p>
     * The short is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a short, <code>0</code> if null map input
     */
    public static short getShortValue(final Map map, final Object key) {
        Short shortObject = getShort(map, key);
        if (shortObject == null) {
            return 0;
        }
        return shortObject.shortValue();
    }

    /**
     * Gets an int from a Map in a null-safe manner.
     * <p>
     * The int is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as an int, <code>0</code> if null map input
     */
    public static int getIntValue(final Map map, final Object key) {
        Integer integerObject = getInteger(map, key);
        if (integerObject == null) {
            return 0;
        }
        return integerObject.intValue();
    }

    /**
     * Gets a long from a Map in a null-safe manner.
     * <p>
     * The long is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a long, <code>0L</code> if null map input
     */
    public static long getLongValue(final Map map, final Object key) {
        Long longObject = getLong(map, key);
        if (longObject == null) {
            return 0L;
        }
        return longObject.longValue();
    }

    /**
     * Gets a float from a Map in a null-safe manner.
     * <p>
     * The float is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a float, <code>0.0F</code> if null map input
     */
    public static float getFloatValue(final Map map, final Object key) {
        Float floatObject = getFloat(map, key);
        if (floatObject == null) {
            return 0f;
        }
        return floatObject.floatValue();
    }

    /**
     * Gets a double from a Map in a null-safe manner.
     * <p>
     * The double is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @return the value in the Map as a double, <code>0.0</code> if null map input
     */
    public static double getDoubleValue(final Map map, final Object key) {
        Double doubleObject = getDouble(map, key);
        if (doubleObject == null) {
            return 0d;
        }
        return doubleObject.doubleValue();
    }

    // Type safe primitive getters with default values
    //-------------------------------------------------------------------------
    /**
     * Gets a boolean from a Map in a null-safe manner,
     * using the default value if the the conversion fails.
     * <p>
     * If the value is a <code>Boolean</code> its value is returned.
     * If the value is a <code>String</code> and it equals 'true' ignoring case
     * then <code>true</code> is returned, otherwise <code>false</code>.
     * If the value is a <code>Number</code> an integer zero value returns
     * <code>false</code> and non-zero returns <code>true</code>.
     * Otherwise, <code>defaultValue</code> is returned.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @param defaultValue  return if the value is null or if the
     *     conversion fails
     * @return the value in the Map as a Boolean, <code>defaultValue</code> if null map input
     */
    public static boolean getBooleanValue(final Map map, final Object key, boolean defaultValue) {
        Boolean booleanObject = getBoolean(map, key);
        if (booleanObject == null) {
            return defaultValue;
        }
        return booleanObject.booleanValue();
    }

    /**
     * Gets a byte from a Map in a null-safe manner,
     * using the default value if the the conversion fails.
     * <p>
     * The byte is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @param defaultValue  return if the value is null or if the
     *     conversion fails
     * @return the value in the Map as a byte, <code>defaultValue</code> if null map input
     */
    public static byte getByteValue(final Map map, final Object key, byte defaultValue) {
        Byte byteObject = getByte(map, key);
        if (byteObject == null) {
            return defaultValue;
        }
        return byteObject.byteValue();
    }

    /**
     * Gets a short from a Map in a null-safe manner,
     * using the default value if the the conversion fails.
     * <p>
     * The short is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @param defaultValue  return if the value is null or if the
     *     conversion fails
     * @return the value in the Map as a short, <code>defaultValue</code> if null map input
     */
    public static short getShortValue(final Map map, final Object key, short defaultValue) {
        Short shortObject = getShort(map, key);
        if (shortObject == null) {
            return defaultValue;
        }
        return shortObject.shortValue();
    }

    /**
     * Gets an int from a Map in a null-safe manner,
     * using the default value if the the conversion fails.
     * <p>
     * The int is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @param defaultValue  return if the value is null or if the
     *     conversion fails
     * @return the value in the Map as an int, <code>defaultValue</code> if null map input
     */
    public static int getIntValue(final Map map, final Object key, int defaultValue) {
        Integer integerObject = getInteger(map, key);
        if (integerObject == null) {
            return defaultValue;
        }
        return integerObject.intValue();
    }

    /**
     * Gets a long from a Map in a null-safe manner,
     * using the default value if the the conversion fails.
     * <p>
     * The long is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @param defaultValue  return if the value is null or if the
     *     conversion fails
     * @return the value in the Map as a long, <code>defaultValue</code> if null map input
     */
    public static long getLongValue(final Map map, final Object key, long defaultValue) {
        Long longObject = getLong(map, key);
        if (longObject == null) {
            return defaultValue;
        }
        return longObject.longValue();
    }

    /**
     * Gets a float from a Map in a null-safe manner,
     * using the default value if the the conversion fails.
     * <p>
     * The float is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @param defaultValue  return if the value is null or if the
     *     conversion fails
     * @return the value in the Map as a float, <code>defaultValue</code> if null map input
     */
    public static float getFloatValue(final Map map, final Object key, float defaultValue) {
        Float floatObject = getFloat(map, key);
        if (floatObject == null) {
            return defaultValue;
        }
        return floatObject.floatValue();
    }

    /**
     * Gets a double from a Map in a null-safe manner,
     * using the default value if the the conversion fails.
     * <p>
     * The double is obtained from the results of {@link #getNumber(Map,Object)}.
     *
     * @param map  the map to use
     * @param key  the key to look up
     * @param defaultValue  return if the value is null or if the
     *     conversion fails
     * @return the value in the Map as a double, <code>defaultValue</code> if null map input
     */
    public static double getDoubleValue(final Map map, final Object key, double defaultValue) {
        Double doubleObject = getDouble(map, key);
        if (doubleObject == null) {
            return defaultValue;
        }
        return doubleObject.doubleValue();
    }

    // Conversion methods
    //-------------------------------------------------------------------------
    /**
     * Gets a new Properties object initialised with the values from a Map.
     * A null input will return an empty properties object.
     *
     * @param map  the map to convert to a Properties object, may not be null
     * @return the properties object
     */
    public static Properties toProperties(final Map map) {
        Properties answer = new Properties();
        if (map != null) {
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                answer.put(key, value);
            }
        }
        return answer;
    }

    /**
     * Creates a new HashMap using data copied from a ResourceBundle.
     *
     * @param resourceBundle  the resource bundle to convert, may not be null
     * @return the hashmap containing the data
     * @throws NullPointerException if the bundle is null
     */
    public static Map toMap(final ResourceBundle resourceBundle) {
        Enumeration enumeration = resourceBundle.getKeys();
        Map map = new HashMap();

        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            Object value = resourceBundle.getObject(key);
            map.put(key, value);
        }

        return map;
    }

    // Printing methods
    //-------------------------------------------------------------------------
    /**
     * Prints the given map with nice line breaks.
     * <p>
     * This method prints a nicely formatted String describing the Map.
     * Each map entry will be printed with key and value.
     * When the value is a Map, recursive behaviour occurs.
     * <p>
     * This method is NOT thread-safe in any special way. You must manually
     * synchronize on either this class or the stream as required.
     *
     * @param out  the stream to print to, must not be null
     * @param label  The label to be used, may be <code>null</code>.
     *  If <code>null</code>, the label is not output.
     *  It typically represents the name of the property in a bean or similar.
     * @param map  The map to print, may be <code>null</code>.
     *  If <code>null</code>, the text 'null' is output.
     * @throws NullPointerException if the stream is <code>null</code>
     */
    public static void verbosePrint(
            final PrintStream out,
            final Object label,
            final Map map) {

        verbosePrintInternal(out, label, map, new ArrayStack(), false);
    }

    /**
     * Prints the given map with nice line breaks.
     * <p>
     * This method prints a nicely formatted String describing the Map.
     * Each map entry will be printed with key, value and value classname.
     * When the value is a Map, recursive behaviour occurs.
     * <p>
     * This method is NOT thread-safe in any special way. You must manually
     * synchronize on either this class or the stream as required.
     *
     * @param out  the stream to print to, must not be null
     * @param label  The label to be used, may be <code>null</code>.
     *  If <code>null</code>, the label is not output.
     *  It typically represents the name of the property in a bean or similar.
     * @param map  The map to print, may be <code>null</code>.
     *  If <code>null</code>, the text 'null' is output.
     * @throws NullPointerException if the stream is <code>null</code>
     */
    public static void debugPrint(
            final PrintStream out,
            final Object label,
            final Map map) {

        verbosePrintInternal(out, label, map, new ArrayStack(), true);
    }

    // Implementation methods
    //-------------------------------------------------------------------------
    /**
     * Logs the given exception to <code>System.out</code>.
     * <p>
     * This method exists as Jakarta Collections does not depend on logging.
     *
     * @param ex  the exception to log
     */
    protected static void logInfo(final Exception ex) {
        System.out.println("INFO: Exception: " + ex);
    }

    /**
     * Implementation providing functionality for {@link #debugPrint} and for
     * {@link #verbosePrint}.  This prints the given map with nice line breaks.
     * If the debug flag is true, it additionally prints the type of the object
     * value.  If the contents of a map include the map itself, then the text
     * <em>(this Map)</em> is printed out.  If the contents include a
     * parent container of the map, the the text <em>(ancestor[i] Map)</em> is
     * printed, where i actually indicates the number of levels which must be
     * traversed in the sequential list of ancestors (e.g. father, grandfather,
     * great-grandfather, etc).
     *
     * @param out  the stream to print to
     * @param label  the label to be used, may be <code>null</code>.
     *  If <code>null</code>, the label is not output.
     *  It typically represents the name of the property in a bean or similar.
     * @param map  the map to print, may be <code>null</code>.
     *  If <code>null</code>, the text 'null' is output
     * @param lineage  a stack consisting of any maps in which the previous
     *  argument is contained. This is checked to avoid infinite recursion when
     *  printing the output
     * @param debug  flag indicating whether type names should be output.
     * @throws NullPointerException if the stream is <code>null</code>
     */
    private static void verbosePrintInternal(
            final PrintStream out,
            final Object label,
            final Map map,
            final ArrayStack lineage,
            final boolean debug) {

        printIndent(out, lineage.size());

        if (map == null) {
            if (label != null) {
                out.print(label);
                out.print(" = ");
            }
            out.println("null");
            return;
        }
        if (label != null) {
            out.print(label);
            out.println(" = ");
        }

        printIndent(out, lineage.size());
        out.println("{");

        lineage.push(map);

        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            Object childKey = entry.getKey();
            Object childValue = entry.getValue();
            if (childValue instanceof Map && !lineage.contains(childValue)) {
                verbosePrintInternal(
                        out,
                        (childKey == null ? "null" : childKey),
                        (Map) childValue,
                        lineage,
                        debug);
            } else {
                printIndent(out, lineage.size());
                out.print(childKey);
                out.print(" = ");

                final int lineageIndex = lineage.indexOf(childValue);
                if (lineageIndex == -1) {
                    out.print(childValue);
                } else if (lineage.size() - 1 == lineageIndex) {
                    out.print("(this Map)");
                } else {
                    out.print(
                            "(ancestor["
                                    + (lineage.size() - 1 - lineageIndex - 1)
                                    + "] Map)");
                }

                if (debug && childValue != null) {
                    out.print(' ');
                    out.println(childValue.getClass().getName());
                } else {
                    out.println();
                }
            }
        }

        lineage.pop();

        printIndent(out, lineage.size());
        out.println(debug ? "} " + map.getClass().getName() : "}");
    }

    /**
     * Writes indentation to the given stream.
     *
     * @param out  the stream to indent
     */
    private static void printIndent(final PrintStream out, final int indent) {
        for (int i = 0; i < indent; i++) {
            out.print(INDENT_STRING);
        }
    }

    // Misc
    //-----------------------------------------------------------------------
    /**
     * Inverts the supplied map returning a new HashMap such that the keys of
     * the input are swapped with the values.
     * <p>
     * This operation assumes that the inverse mapping is well defined.
     * If the input map had multiple entries with the same value mapped to
     * different keys, the returned map will map one of those keys to the
     * value, but the exact key which will be mapped is undefined.
     *
     * @param map  the map to invert, may not be null
     * @return a new HashMap containing the inverted data
     * @throws NullPointerException if the map is null
     */
    public static Map invertMap(Map map) {
        Map out = new HashMap(map.size());
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            out.put(entry.getValue(), entry.getKey());
        }
        return out;
    }

    //-----------------------------------------------------------------------
    /**
     * Protects against adding null values to a map.
     * <p>
     * This method checks the value being added to the map, and if it is null
     * it is replaced by an empty string.
     * <p>
     * This could be useful if the map does not accept null values, or for
     * receiving data from a source that may provide null or empty string
     * which should be held in the same way in the map.
     * <p>
     * Keys are not validated.
     *
     * @param map  the map to add to, may not be null
     * @param key  the key
     * @param value  the value, null converted to ""
     * @throws NullPointerException if the map is null
     */
    public static void safeAddToMap(Map map, Object key, Object value) throws NullPointerException {
        if (value == null) {
            map.put(key, "");
        } else {
            map.put(key, value);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Puts all the keys and values from the specified array into the map.
     * <p>
     * This method is an alternative to the {@link java.util.Map#putAll(java.util.Map)}
     * method and constructors. It allows you to build a map from an object array
     * of various possible styles.
     * <p>
     * If the first entry in the object array implements {@link java.util.Map.Entry}
     * then the key and value are added from that object.
     * If the first entry in the object array is an object array itself, then
     * it is assumed that index 0 in the sub-array is the key and index 1 is the value.
     * Otherwise, the array is treated as keys and values in alternate indices.
     * <p>
     * For example, to create a color map:
     * <pre>
     * Map colorMap = Maps.putAll(new HashMap(), new String[][] {
     *     {"RED", "#FF0000"},
     *     {"GREEN", "#00FF00"},
     *     {"BLUE", "#0000FF"}
     * });
     * </pre>
     * or:
     * <pre>
     * Map colorMap = Maps.putAll(new HashMap(), new String[] {
     *     "RED", "#FF0000",
     *     "GREEN", "#00FF00",
     *     "BLUE", "#0000FF"
     * });
     * </pre>
     * or:
     * <pre>
     * Map colorMap = Maps.putAll(new HashMap(), new Map.Entry[] {
     *     new DefaultMapEntry("RED", "#FF0000"),
     *     new DefaultMapEntry("GREEN", "#00FF00"),
     *     new DefaultMapEntry("BLUE", "#0000FF")
     * });
     * </pre>
     *
     * @param map  the map to populate, must not be null
     * @param array  an array to populate from, null ignored
     * @return the input map
     * @throws NullPointerException  if map is null
     * @throws IllegalArgumentException  if sub-array or entry matching used and an
     *  entry is invalid
     * @throws ClassCastException if the array contents is mixed
     * @since 1.4.1
     */
    public static Map putAll(Map map, Object[] array) {
        map.size();  // force NPE
        if (array == null || array.length == 0) {
            return map;
        }
        Object obj = array[0];
        if (obj instanceof Map.Entry) {
            for (int i = 0; i < array.length; i++) {
                Map.Entry entry = (Map.Entry) array[i];
                map.put(entry.getKey(), entry.getValue());
            }
        } else if (obj instanceof Object[]) {
            for (int i = 0; i < array.length; i++) {
                Object[] sub = (Object[]) array[i];
                if (sub == null || sub.length < 2) {
                    throw new IllegalArgumentException("Invalid array element: " + i);
                }
                map.put(sub[0], sub[1]);
            }
        } else {
            for (int i = 0; i < array.length - 1;) {
                map.put(array[i++], array[i++]);
            }
        }
        return map;
    }

    //-----------------------------------------------------------------------
    /**
     * Null-safe check if the specified map is empty.
     * <p>
     * Null returns true.
     *
     * @param map  the map to check, may be null
     * @return true if empty or null
     * @since 1.4.1
     */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    /**
     * Null-safe check if the specified map is not empty.
     * <p>
     * Null returns false.
     *
     * @param map  the map to check, may be null
     * @return true if non-null and non-empty
     * @since 1.4.1
     */
    public static boolean isNotEmpty(Map map) {
        return !Maps.isEmpty(map);
    }

}
