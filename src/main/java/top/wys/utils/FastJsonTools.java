package top.wys.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
/**
 * @author 郑明亮
 * @Time：2016年7月18日 上午9:53:16
 * @version 1.0
 */
public class FastJsonTools {

	public FastJsonTools() {
		throw new UnsupportedOperationException("不能被实例化");
	}

	/**TODO 转换成json格式的字符串
	 * @param object 要转换的对象
	 * @return
	 */
	public static String createJsonString(Object object) {
		String jsonString = JSON.toJSONString(object);
		return jsonString;
	}

	/**将json字符串转换为指定的bean对象
	 * @param jsonString 
	 * @param cls  要转换为对象的类型
	 * @return
	 */
	public static <T> T createJsonBean(String jsonString, Class<T> cls) {
		T t = JSON.parseObject(jsonString, cls);
		return t;
	}

	/**将json字符串转换为List<T>
	 * @param jsonString
	 * @param cls
	 * @return
	 */
	public static <T> List<T> createJsonToListBean(String jsonString,
			Class<T> cls) {
		List<T> list = null;
		list = JSON.parseArray(jsonString, cls);
		return list;
	}

	/**将json字符串转换为List<Map<String,Object>>
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String, Object>> createJsonToListMap(
			String jsonString) {
		List<Map<String, Object>> list2 = JSON.parseObject(jsonString,
				new TypeReference<List<Map<String, Object>>>() {
				});
		return list2;
	}

	/**将json字符串转换为List<String>
	 * @param jsonString
	 * @return
	 */
	public static List<String> createJsonToListString(String jsonString) {
		List<String> list2 = JSON.parseObject(jsonString,
				new TypeReference<List<String>>() {
				});
		return list2;
	}

	/**将json字符串转换为Map<String,Object>
	 * @param jsonString
	 * @return
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
	 * @description <p>将json字符串转换为Map<Object,Object> </p>
	 * @modifyBy
	 * @modifyTime 
	 * @modifyDescription<p> </p>
	 * @param jsonString  json字符串
	 * @param ignoreSpace 是否忽略value中为空字符串的值
	 * @return
	 */
	public static Map<Object, Object> createJsonToMap(String jsonString,boolean ignoreSpace) {
		Map<Object, Object> map = JSON.parseObject(jsonString,
													new TypeReference<Map<Object, Object>>() {});
		if (ignoreSpace) {//是否忽略空字符串
			System.out.println("忽略value为空字符串的值");
			Iterator<Object> keys = map.keySet().iterator();
			while (keys.hasNext()) {
				Object key = keys.next();
				if (StringUtils.isEmpty(map.get(key))) {
					System.out.println("移除的key值:"+key);
					keys.remove();
				}
				
			}
		}
		
		return map;
	}
	/**
	 * @author 郑明亮
	 * @time 2017年7月4日 下午5:58:48
	 * @description <p> 将实体类转换为Map<Object,Object></p>
	 * @modifyBy
	 * @modifyTime 
	 * @modifyDescription<p> </p>
	 * @param obj
	 * @return
	 */
	public static Map<Object, Object> createBeanToMap(Object obj) {
		String jsonString = createJsonString(obj);
		Map<Object, Object> map = JSON.parseObject(jsonString,
				new TypeReference<Map<Object, Object>>() {
				});
		
		return map;
	}
	
	/**
	 * @author 郑明亮
	 * @time 2017年7月4日 下午5:58:27
	 * @description <p>将实体类转换为Map<Object,Object> </p>
	 * @modifyBy
	 * @modifyTime 
	 * @modifyDescription<p> </p>
	 * @param obj  
	 * @param ignoreSpace
	 * @return
	 */
	public static Map<Object, Object> createBeanToMap(Object obj,boolean ignoreSpace) {
		String jsonString = createJsonString(obj);
		Map<Object, Object> map = JSON.parseObject(jsonString,
				new TypeReference<Map<Object, Object>>() {	});
		if (ignoreSpace) {//是否忽略空字符串
			System.out.println("忽略value为空字符串的值");
			Iterator<Object> keys = map.keySet().iterator();
			while (keys.hasNext()) {
				Object key = keys.next();
				if (StringUtils.isEmpty(map.get(key))) {
					System.out.println("移除的key值:"+key);
					keys.remove();
				}
				
			}
		}
		return map;
	}
}
