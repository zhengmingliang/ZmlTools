package top.wys.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * @author 郑明亮
 * @Time：2016年2月2日 下午2:15:38
 * @version 1.0
 */
public class GsonTools {

	public static String createJsonString(Object src) {

		Gson gson = new Gson();
		String jsonString = gson.toJson(src);
		return jsonString;
	}

	public static <T> T getObject(String jsonString, Class<T> type) {

		T t = null;
		try {
			Gson gson = new Gson();
			t = gson.fromJson(jsonString, type);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	public static <T> List<T> getlist(String jsonString, Class<T> type) {
		List<T> list = new ArrayList<T>();
		Gson gson = new Gson();
		list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
		}.getType());
		return list;

	}
	public static <T> List<T> StringTolist(String jsonString, Class<T[]> type) {
		
		T[] list = null;
		try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonString, type);
			return Arrays.asList(list);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	public static List<String> getStrings(String jsonString) {
		List<String> list = new ArrayList<String>();
		Gson gson = new Gson();
		list = gson.fromJson(jsonString, new TypeToken<List<String>>() {
		}.getType());
		return list;

	}

	public static List<Map<String, Object>> getMaps(String jsonString) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		list = gson.fromJson(jsonString,
				new TypeToken<List<Map<String, Object>>>() {
				}.getType());

		return list;

	}

	/**
	 * 将输入流转换为byte[]
	 * 
	 * @param is
	 * @return
	 */
	public static byte[] IsToByte(InputStream is) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte buffer[] = new byte[1024];
		int len = 0;
		try {
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				bos.flush();
				bos.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return bos.toByteArray();
	}

}
