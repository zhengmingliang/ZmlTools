/**
 * Created by 郑明亮 on 2022/1/24 18:51.
 */
package top.wys.utils.collection;

import java.util.Collection;
import java.util.Iterator;
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
}
