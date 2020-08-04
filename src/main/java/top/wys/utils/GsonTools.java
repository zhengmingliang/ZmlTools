package top.wys.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import top.wys.utils.gson.MapTypeAdapter;

/**
 * @author 郑明亮
 * @version 1.0
 * @time：2016年2月2日 下午2:15:38
 */
public class GsonTools {

    @Setter
    @Getter
	private static Gson gson = new GsonBuilder().registerTypeAdapterFactory(MapTypeAdapter.FACTORY).create();;



    /**
     * 将对象转换为json字符串
     *
     * @param src
     * @return
     */
    public static String createJsonString(Object src) {
        return gson.toJson(src);
    }

    /**
     * 将json字符串转换为指定类型对象
     *
     * @param jsonString json字符串
     * @param type       要转换成的对象的类型
     * @return
     */
    public static <T> T getBeanFromJson(String jsonString, Class<T> type) {

        T t = null;
        try {
            t = gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将json字符串转换为指定类型对象
     *
     * @param jsonString json字符串
     * @param type       要转换成的对象的类型
     * @return
     */
    public static <T> T getBeanFromJson(String jsonString, TypeToken<T> type) {

        T t = null;
        try {
            t = gson.fromJson(jsonString, type.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将json字符串转换为指定对象集合
     *
     * @param jsonString json字符串
     * @param type       要转换成的对象集合类型
     * @return
     */
    public static <T> List<T> getList(String jsonString, Class<T> type) {
        return gson.fromJson(jsonString, new ListParameterizedType(type));

    }

    /**
     * 将json字符串转换为对象集合，该方法可避免在远程调用时引起的java对象擦除的问题
     *
     * @param jsonString json字符串
     * @param type       要转换成对象的数组 * @return
     */
    public static <T> List<T> getListFromJson(String jsonString, Class<T[]> type) {

        T[] list = null;
        try {
            list = gson.fromJson(jsonString, type);
            return Arrays.asList(list);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();

    }

    /**
     * 将json字符串转换为字符串集合
     *
     * @param jsonString
     * @return
     */
    public static List<String> getStrings(String jsonString) {
        List<String> list = gson.fromJson(jsonString, new TypeToken<List<String>>() {
        }.getType());
        return list;

    }

    /**
     * 将json字符串转换为map集合
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getMaps(String jsonString) {
        return gson.fromJson(jsonString,
				new TypeToken<List<Map<String, Object>>>() {
				}.getType());

    }

    /**
     * 将json转为Map对象
     * @param jsonString
     *
     * @return
     */
    public static Map<String, Object> getMapFromJson(String jsonString) {
        return gson.fromJson(jsonString,
                new com.google.common.reflect.TypeToken<Map<String, Object>>() {
                }.getType());

    }

    private static class ListParameterizedType implements ParameterizedType {
        private Type type;

        private ListParameterizedType(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
