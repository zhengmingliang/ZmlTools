package top.wys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author 郑明亮
 * @time：2016年7月18日 上午9:53:16
 * @version 1.0
 */
public class FastJsonTools {

    private static final Logger log = LoggerFactory.getLogger(FastJsonTools.class);

	private FastJsonTools() {
		throw new UnsupportedOperationException("不能被实例化");
	}

	/**
	 *  转换成json格式的字符串
	 * @param object 要转换的对象
	 * @return json字符串
	 */
	public static String createJsonString(Object object) {
		String jsonString = JSON.toJSONString(object);
		return jsonString;
	}

	/**将json字符串转换为指定的bean对象
	 * @param jsonString 
	 * @param cls  要转换为对象的类型
	 * @return 指定对象
	 */
	public static <T> T createJsonBean(String jsonString, Class<T> cls) {
		T t = JSON.parseObject(jsonString, cls);
		return t;
	}

	/**将json字符串转换为指定的bean对象,可以是复杂的对象
	 * @param jsonString 
	 * @param type  要转换为对象的类型 eg:new TypeReference&lt;T&gt;(){}
	 * @return 指定对象
	 * @param <T>
	 */
	public static <T> T createJsonBean(String jsonString, TypeReference<T> type) {
		T t = JSON.parseObject(jsonString,type);
		return t;
	}

	/**将json字符串转换为List&lt;T&gt;
	 * @param jsonString json字符串
	 * @param cls 要转换成的对象类型
	 * @return 指定对象
	 */
	public static <T> List<T> createJsonToListBean(String jsonString,
			Class<T> cls) {
		List<T> list = null;
		list = JSON.parseArray(jsonString, cls);
		return list;
	}

	/**将json字符串转换为List&lt;Map&lt;String,Object&gt;&gt;
	 * @param jsonString json字符串
	 * @return  List&lt;Map&lt;String,Object&gt;&gt;
	 */
	public static List<Map<String, Object>> createJsonToListMap(
			String jsonString) {
		List<Map<String, Object>> list2 = JSON.parseObject(jsonString,
				new TypeReference<List<Map<String, Object>>>() {
				});
		return list2;
	}

	/**将json字符串转换为List&lt;String&gt;
	 * @param jsonString json字符串
	 * @return List&lt;String&gt;
	 */
	public static List<String> createJsonToListString(String jsonString) {
		List<String> list2 = JSON.parseObject(jsonString,
				new TypeReference<List<String>>() {
				});
		return list2;
	}

	/**将json字符串转换为Map&lt;String,Object&gt;
	 * @param jsonString json字符串
	 * @return Map&lt;String,Object&gt;
	 */
	public static Map<Object, Object> createJsonToMap(String jsonString) {
		Map<Object, Object> list2 = JSON.parseObject(jsonString,
				new TypeReference<Map<Object, Object>>() {
				});
		return list2;
	}

	
	/**
	 * @author 郑明亮
	 * @time 2017年7月4日 下午5:27:57
	 * @description <p>将json字符串转换为Map&lt;Object,Object&gt; <br>
	 * @modifyBy
	 * @modifyTime 
	 * @modifyDescription<p> <br>
	 * @param jsonString  json字符串
	 * @param ignoreSpace 是否忽略value中为空字符串的值
	 * @return Map&lt;Object,Object&gt;
	 */
	public static Map<Object, Object> createJsonToMap(String jsonString,boolean ignoreSpace) {
		Map<Object, Object> map = JSON.parseObject(jsonString,
													new TypeReference<Map<Object, Object>>() {});
		if (ignoreSpace) {//是否忽略空字符串
            log.debug("忽略value为空字符串的值");
			Iterator<Object> keys = map.keySet().iterator();
			while (keys.hasNext()) {
				Object key = keys.next();
				if (StringUtils.isEmpty(map.get(key))) {
					log.debug("移除的key值:{}",key);
					keys.remove();
				}
				
			}
		}
		
		return map;
	}
	/**
	 * @author 郑明亮
	 * @time 2017年7月4日 下午5:58:48
	 * @description <p> 将实体类转换为Map&lt;Object,Object&gt;<br>
	 * @modifyBy
	 * @modifyTime 
	 * @modifyDescription<p> <br>
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> createBeanToMap(Object obj) {
		String jsonString = createJsonString(obj);
		Map<String, Object> map = JSON.parseObject(jsonString,
				new TypeReference<Map<String, Object>>() {
				});
		
		return map;
	}
	
	/**
	 * @author 郑明亮
	 * @time 2017年7月4日 下午5:58:27
	 * @description <p>将实体类转换为Map&lt;Object,Object&gt; <br>
	 * @modifyBy
	 * @modifyTime 
	 * @modifyDescription<p> <br>
	 * @param obj  
	 * @param ignoreSpace
	 * @return
	 */
	public static Map<Object, Object> createBeanToMap(Object obj,boolean ignoreSpace) {
		String jsonString = createJsonString(obj);
		Map<Object, Object> map = JSON.parseObject(jsonString,
				new TypeReference<Map<Object, Object>>() {	});
		if (ignoreSpace) {//是否忽略空字符串
			log.debug("忽略value为空字符串的值");
			Iterator<Object> keys = map.keySet().iterator();
			while (keys.hasNext()) {
				Object key = keys.next();
				if (StringUtils.isEmpty(map.get(key))) {
					log.debug("移除的key值:{}",key);
					keys.remove();
				}
				
			}
		}
		return map;
	}
}
