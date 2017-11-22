package top.wys.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 郑明亮
 * @Time：2016年2月2日 下午2:15:38
 * @version 1.0
 */
public class GsonTools {

	/**
	 * 将对象转换为json字符串
	 * @param src
	 * @return
	 */
	public static String createJsonString(Object src) {

		Gson gson = new Gson();
		String jsonString = gson.toJson(src);
		return jsonString;
	}

	/**
	 *  将json字符串转换为指定类型对象
	 * @param jsonString json字符串
	 * @param type 要转换成的对象的类型
	 * @return
	 */
	public static <T> T getBeanFromJson(String jsonString, Class<T> type) {

		T t = null;
		try {
			Gson gson = new Gson();
			t = gson.fromJson(jsonString, type);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将json字符串转换为指定对象集合
	 * @param jsonString json字符串
	 * @param type 要转换成的对象集合类型
	 * @return
	 */
	public static <T> List<T> getlist(String jsonString, Class<T> type) {
		List<T> list = new ArrayList<T>();
		Gson gson = new Gson();
		list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
		}.getType());
		return list;

	}

	/**
	 * 将json字符串转换为对象集合，该方法可避免在远程调用时引起的java对象擦除的问题
	 * @param jsonString json字符串
	 * @param type 要转换成对象的数组类型
	 * @return
	 */
	public static <T> List<T> StringTolist(String jsonString, Class<T[]> type) {
		
		T[] list = null;
		try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonString, type);
			return Arrays.asList(list);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 将json字符串转换为字符串集合
	 * @param jsonString
	 * @return
	 */
	public static List<String> getStrings(String jsonString) {
		List<String> list = new ArrayList<String>();
		Gson gson = new Gson();
		list = gson.fromJson(jsonString, new TypeToken<List<String>>() {
		}.getType());
		return list;

	}

	/** 将json字符串转换为map集合
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String, Object>> getMaps(String jsonString) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		list = gson.fromJson(jsonString,
				new TypeToken<List<Map<String, Object>>>() {
				}.getType());

		return list;

	}
}
