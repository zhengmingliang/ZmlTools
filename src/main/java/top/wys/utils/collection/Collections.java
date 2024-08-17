/**
 * Created by 郑明亮 on 2022/1/24 18:51.
 */
package top.wys.utils.collection;

import com.google.common.collect.Lists;

import javax.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p> 集合工具类</p>
 *
 * @author 郑明亮
 * @time 2022/1/23 21:51
 * @since 1.3.3
 */
public class Collections {
    private Collections() {
        // generator
    }

    /**
     * 从集合中移除一个对象
     *
     * @param collection 集合
     * @param data       要移除的对象
     * @param <T>
     */
    public static <T> void removeOne(Collection<T> collection, T data) {
        if (collection == null || data == null) {
            return;
        }
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (Objects.equals(next, data)) {
                iterator.remove();
                break;
            }
        }
    }


    /**
     * 删除集合中一个元素
     *
     * @param collections 含有数据的集合
     * @param predicate   要删除的集合中对象的条件（谓词）
     * @since 1.4.2
     */
    public static <T> void removeOne(Collection<T> collections, Predicate<T> predicate) {
        if (collections == null) {
            return;
        }
        Iterator<T> iterator = collections.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (predicate.test(next)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * 从集合中移除所有的对象
     *
     * @param collection 集合
     * @param data       要移除的所有的对象
     * @param <T>
     */
    public static <T> void removeAll(Collection<T> collection, T data) {
        if (collection == null || data == null) {
            return;
        }
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (Objects.equals(next, data)) {
                iterator.remove();
            }
        }
    }

    /**
     * 删除集合中所有元素
     *
     * @param collections 含有数据的集合
     * @param predicate   要删除的集合中对象的条件（谓词）
     * @since 1.4.2
     */
    public static <T> void removeAll(Collection<T> collections, Predicate<T> predicate) {
        if (collections == null) {
            return;
        }
        Iterator<T> iterator = collections.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (predicate.test(next)) {
                iterator.remove();
            }
        }
    }


    /**
     * 判定是否为空集合
     *
     * @param collection 集合
     * @return boolean 如果集合为空，则返回<code>true</code>，否则返回<code>false</code>
     */
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 判定是否不为空集合
     *
     * @param collection 集合
     * @return boolean 如果集合不为空，则返回<code>true</code>，否则返回<code>false</code>
     */
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判定是否为空map
     *
     * @param map map集合
     * @return boolean 如果map为空，则返回<code>true</code>，否则返回<code>false</code>
     */
    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判定是否不为空map
     *
     * @param map map集合
     * @return boolean 如果map不为空，则返回<code>true</code>，否则返回<code>false</code>
     */
    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }


    /**
     * 获取集合第一个元素
     *
     * @param collection 集合
     * @param <T>
     * @return
     * @since 1.4.3
     */
    public static <T> T getFirst(Collection<T> collection) {
        return getFirst(collection, null);
    }

    /**
     * 获取集合第一个元素
     *
     * @param collection   集合
     * @param defaultValue 当{@code collection} 为空时，则返回该默认值
     * @param <T>
     * @return
     * @since 1.4.3
     */
    public static <T> T getFirst(Collection<T> collection, T defaultValue) {
        if (isEmpty(collection)) {
            return defaultValue;
        }
        if (collection instanceof SortedSet) {
            return ((SortedSet<T>) collection).last();
        }
        Iterator<T> iterator = collection.iterator();
        T first = defaultValue;
        if (iterator.hasNext()) {
            first = iterator.next();
        }
        return first;
    }


    /**
     * 获取集合最后一个元素
     *
     * @param collection 集合
     * @param <T>
     * @return
     * @since 1.4.5
     */
    public static <T> T getLast(Collection<T> collection) {
        return getLast(collection, null);
    }

    /**
     * 获取集合最后一个元素
     *
     * @param collection   集合
     * @param defaultValue 当{@code collection} 为空时，则返回该默认值
     * @param <T>
     * @return
     * @since 1.4.5
     */
    public static <T> T getLast(Collection<T> collection, T defaultValue) {
        if (isEmpty(collection)) {
            return defaultValue;
        }
        if (collection instanceof SortedSet) {
            return ((SortedSet<T>) collection).last();
        }
        if (collection instanceof List) {
            return ((List<T>) collection).get(collection.size() - 1);
        }

        // Full iteration necessary...
        Iterator<T> it = collection.iterator();
        T last = null;
        while (it.hasNext()) {
            last = it.next();
        }
        return last;

    }

    /**
     * Finds the first element in the given collection which matches the given predicate.
     * <p>
     * If the input collection or predicate is null, or no element of the collection
     * matches the predicate, null is returned.
     *
     * @param collection  the collection to search, may be null
     * @param predicate  the predicate to use, may be null
     * @return the first element of the collection which matches the predicate or null if none could be found
     */
    public static <T> T findFirst(Collection<T> collection, Predicate predicate) {
        if (collection != null && predicate != null) {
            for (Iterator<T> iter = collection.iterator(); iter.hasNext();) {
                T item = iter.next();
                if (predicate.test(item)) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * Finds the first element in the given collection which matches the given predicate.
     * <p>
     * If the input collection or predicate is null, or no element of the collection
     * matches the predicate, null is returned.
     *
     * @param collection  the collection to search, may be null
     * @param predicate  the predicate to use, may be null
     * @return the first element of the collection which matches the predicate or null if none could be found
     */
    public static <T> List<T> findAll(Collection<T> collection, Predicate predicate) {
        if (collection != null && predicate != null) {
            List<T> list = Lists.newArrayListWithExpectedSize(collection.size());
            for (Iterator<T> iter = collection.iterator(); iter.hasNext();) {
                T item = iter.next();
                if (predicate.test(item)) {
                    list.add(item);
                }
            }
            return list;
        }
        return java.util.Collections.emptyList();
    }


    /**
     * Transforms all elements from the inputIterator with the given transformer
     * and adds them to the outputCollection.
     * <p>
     * If the input iterator or transformer is null, the result is an empty list.
     *
     * @param inputIterator  the iterator to get the input from, may be null
     * @param transformer  the transformer to use, may be null
     * @return the transformed result (new list)
     */
    public static <I, O> Collection<O> convert(Iterator<I> inputIterator, Function<I, O> transformer) {
        ArrayList answer = new ArrayList();
        convert(inputIterator, transformer, answer);
        return answer;
    }

    /**
     * Transforms all elements from inputCollection with the given transformer
     * and adds them to the outputCollection.
     * <p>
     * If the input collection or transformer is null, there is no change to the
     * output collection.
     *
     * @param inputCollection  the collection to get the input from, may be null
     * @param transformer  the transformer to use, may be null
     * @param outputCollection  the collection to output into, may not be null
     * @return the outputCollection with the transformed input added
     * @throws NullPointerException if the output collection is null
     */
    public static <I, O> Collection<O> convert(Collection<I> inputCollection, final Function<I, O> transformer,
                                               final Collection<O> outputCollection) {
        if (inputCollection != null) {
            return convert(inputCollection.iterator(), transformer, outputCollection);
        }
        return outputCollection;
    }


    /**
     * Transforms all elements from the inputIterator with the given transformer
     * and adds them to the outputCollection.
     * <p>
     * If the input iterator or transformer is null, there is no change to the
     * output collection.
     *
     * @param inputIterator  the iterator to get the input from, may be null
     * @param transformer  the transformer to use, may be null
     * @param outputCollection  the collection to output into, may not be null
     * @return the outputCollection with the transformed input added
     * @throws NullPointerException if the output collection is null
     */
    public static <I, O> Collection<O> convert(Iterator<I> inputIterator, final Function<I, O> transformer,
                                               final Collection<O> outputCollection) {
        if (inputIterator != null && transformer != null) {
            while (inputIterator.hasNext()) {
                I item = inputIterator.next();
                O value = transformer.apply(item);
                outputCollection.add(value);
            }
        }
        return outputCollection;
    }



    /**
     * Counts the number of elements in the input collection that match the predicate.
     * <p>
     * A <code>null</code> collection or predicate matches no elements.
     *
     * @param inputCollection  the collection to get the input from, may be null
     * @param predicate  the predicate to use, may be null
     * @return the number of matches for the predicate in the collection
     */
    public static int countMatches(Collection inputCollection, Predicate predicate) {
        int count = 0;
        if (inputCollection != null && predicate != null) {
            for (Iterator it = inputCollection.iterator(); it.hasNext();) {
                if (predicate.test(it.next())) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * Answers true if a predicate is true for at least one element of a collection.
     * <p>
     * A <code>null</code> collection or predicate returns false.
     *
     * @param collection the collection to get the input from, may be null
     * @param predicate the predicate to use, may be null
     * @return true if at least one element of the collection matches the predicate
     */
    public static boolean exists(Collection collection, Predicate predicate) {
        if (collection != null && predicate != null) {
            for (Iterator it = collection.iterator(); it.hasNext();) {
                if (predicate.test(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 获取集合大小
     * @param collection
     * @since 1.4.5
     * @return
     */
    public static int size(Collection collection) {
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    /**
     * Gets the size of the collection/iterator specified.
     * <p>
     * This method can handles objects as follows
     * <ul>
     * <li>Collection - the collection size
     * <li>Map - the map size
     * <li>Array - the array size
     * <li>Iterator - the number of elements remaining in the iterator
     * <li>Enumeration - the number of elements remaining in the enumeration
     * </ul>
     *
     * @param object  the object to get the size of
     * @return the size of the specified collection
     * @throws IllegalArgumentException thrown if object is not recognised or null
     * @since Commons Collections 3.1
     */
    public static int size(Object object) {
        int total = 0;
        if (object == null) {
            return total;
        }
        if (object instanceof Map) {
            total = ((Map) object).size();
        } else if (object instanceof Collection) {
            total = ((Collection) object).size();
        } else if (object instanceof Object[]) {
            total = ((Object[]) object).length;
        } else if (object instanceof Iterator) {
            Iterator it = (Iterator) object;
            while (it.hasNext()) {
                total++;
                it.next();
            }
        } else if (object instanceof Enumeration) {
            Enumeration it = (Enumeration) object;
            while (it.hasMoreElements()) {
                total++;
                it.nextElement();
            }
        } else {
            try {
                total = Array.getLength(object);
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
            }
        }
        return total;
    }



    /**
     * Check whether the given Iterator contains the given element.
     * @param iterator the Iterator to check
     * @param element the element to look for
     * @return {@code true} if found, {@code false} otherwise
     */
    public static boolean contains(@Nullable Iterator<?> iterator, Object element) {
        if (iterator != null) {
            while (iterator.hasNext()) {
                Object candidate = iterator.next();
                if (top.wys.utils.Objects.deepEquals(candidate, element)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Find a single value of the given type in the given Collection.
     * @param collection the Collection to search
     * @param type the type to look for
     * @return a value of the given type found if there is a clear match,
     * or {@code null} if none or more than one such value found
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T findValueOfType(Collection<?> collection, @Nullable Class<T> type) {
        if (isEmpty(collection)) {
            return null;
        }
        T value = null;
        for (Object element : collection) {
            if (type == null || type.isInstance(element)) {
                if (value != null) {
                    // More than one value found... no clear single value.
                    return null;
                }
                value = (T) element;
            }
        }
        return value;
    }



    /**
     * Find a single value of one of the given types in the given Collection:
     * searching the Collection for a value of the first type, then
     * searching for a value of the second type, etc.
     * @param collection the collection to search
     * @param types the types to look for, in prioritized order
     * @return a value of one of the given types found if there is a clear match,
     * or {@code null} if none or more than one such value found
     */
    @Nullable
    public static Object findValueOfType(Collection<?> collection, Class<?>[] types) {
        if (isEmpty(collection) || ArrayUtils.isEmpty(types)) {
            return null;
        }
        for (Class<?> type : types) {
            Object value = findValueOfType(collection, type);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

}
