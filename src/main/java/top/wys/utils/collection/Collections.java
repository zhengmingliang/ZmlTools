/**
 * Created by 郑明亮 on 2022/1/24 18:51.
 */
package top.wys.utils.collection;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * <p> 集合工具类</p>
 *
 * @author 郑明亮
 * @since  1.3.3
 * @time 2022/1/23 21:51
 */
public class Collections {
    private Collections() {
        // generator
    }

    public static  <T> void removeOne(Collection<T> collection, T data){
        if (collection == null || data == null) {
            return;
        }
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (Objects.equals(next,data)) {
                iterator.remove();
                break;
            }
        }
    }
    public static  <T> void removeAll(Collection<T> collection,T data){
        if (collection == null || data == null) {
            return;
        }
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (Objects.equals(next,data)) {
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



}
