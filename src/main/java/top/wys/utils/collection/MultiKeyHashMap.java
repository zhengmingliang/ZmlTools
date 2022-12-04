/**
 * Created by 郑明亮 on 2022/3/13 09:27.
 */
package top.wys.utils.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 可以映射多个key到一个value的HashMap实现(获取值时可忽略key的大小写，优先获取匹配大小写的值)</p>
 *
 * @author 郑明亮
 * @since  1.3.11
 * @time 2022/3/13 09:27
 */
public class MultiKeyHashMap<K,V> extends HashMap<K,V> {

    Map<String,K> keyMap = new HashMap<String,K>();

    @Override
    public V get(Object key) {
        V v = super.get(key);
        if(v != null){
            return v;
        }
        K k = keyMap.get(String.valueOf(key).toLowerCase());
        if (k != null) {
            return super.get(k);
        }

        return null;
    }


    @Override
    public V getOrDefault(Object key, V defaultValue) {
        V v = get(key);
        if(v != null){
            return v;
        }
        return defaultValue;
    }

    /**
     * 通过多个key来获取值，返回不为空的key值
     * @param key1
     * @param key2
     * @return
     */
    public V get(Object key1,Object key2) {
        V v = get(key1);
        if (v != null) {
            return v;
        }
        return get(key2);
    }

    /**
     * 通过多个key来获取值，返回不为空的key值
     * @param key1 key1
     * @param key2 key2
     * @param keys 键
     * @return {@link V}
     */
    public V get(Object key1,Object key2,Object... keys) {
        V v = this.get(key1);
        if (v != null) {
            return v;
        }
        v = this.get(key2);
        if (v != null) {
            return v;
        }
        if (keys != null) {
            for (Object key : keys) {
                v = this.get(key);
                if (v != null) {
                    return v;
                }
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        keyMap.put(String.valueOf(key).toLowerCase(),key);
        return super.put(key, value);
    }

    public V put(K key, K key2, V value) {
        put(key, value);
        return put(key2,value);
    }


    public V putValue(V value,K key,K... keys) {

        V v = super.put(key, value);
        if (keys != null) {
            for (K k : keys) {
                super.put(k, value);
            }
        }
        return v;
    }


}
