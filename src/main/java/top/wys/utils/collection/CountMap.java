/**
 * Created by 郑明亮 on 2022/2/18 15:29.
 */
package top.wys.utils.collection;

import java.util.HashMap;

/**
 * <p> 计数map</p>
 *
 * @author 郑明亮
 * @version 1.0.0
 * @time 2022/2/19 15:29
 */
public class CountMap<K> extends HashMap<K, Integer> {
    public void increment(K key) {
        put(key, get(key) + 1);
    }

    public void increment(K key, int step) {
        put(key, get(key) + step);
    }

    @Override
    public Integer get(Object key) {
        Integer integer = super.get(key);
        return integer == null ? 0 : integer;
    }
}
