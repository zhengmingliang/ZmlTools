package top.wys.utils;


import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author 郑明亮
 * @time：2016年12月20日 下午4:34:52
 * @description <p> 反射相关工具类<br>
 */
public class ReflectionUtils {
	private ReflectionUtils() {
		throw new UnsupportedOperationException(" you can not instantiate me");
	}


	/**
	 * 获取传入类中声明的成员变量、常量的成员名称
	 * @param clz
	 * @param upperFirstLetter 是否将首字母转换为大写
	 * @return
	 */
	public static String[] getFieldsNames(Class<?> clz,boolean upperFirstLetter) {
		return getFieldsNames(clz,upperFirstLetter,false);
	}

	/**
	 * @author 郑明亮
	 * @time：2016年12月19日 下午5:40:50  获取传入类中声明的成员变量、常量的成员名称，并将首字母变为大写
	 * @param clz
	 * @param upperFirstLetter 是否首字母大写
	 * @param includeParent 是否获取直接父类中的成员
	 * @return
	 */
	public static String[] getFieldsNames(Class<?> clz,boolean upperFirstLetter,boolean includeParent) {
		List<String> list = new ArrayList<String>();
		List<Field> fieldList = Lists.newArrayList();
		fieldList.addAll(Arrays.asList(clz.getDeclaredFields()));
		if(includeParent){
			fieldList.addAll(Arrays.asList(clz.getSuperclass().getDeclaredFields()));
		}
		for (int i = 0; i < fieldList.size(); i++) {
			String fieldName = fieldList.get(i).getName();
			if (upperFirstLetter){
				fieldName= fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 首字母大写
			}
			list.add(fieldName);
		}
		return list.toArray(new String[list.size()]);
	}



	/**
	 * @author 郑明亮
	 * @time 2017年1月9日 下午7:47:27
	 * @description <p>
	 *              获取传入类中声明的成员变量、常量的成员名称，并将首字母变为大写，特定成员除外
	 *              <br>
	 * @param clz
	 * @param exceptNames   不包含的声明的成员变量、常量的名称，必需与实体类中的成员名称大小写一致
	 *
	 * @return
	 */
	public static String[] getFieldsNames(Class<?> clz, String... exceptNames) {
		List<String> list = new ArrayList<String>();

		Field[] fields = clz.getDeclaredFields();
		String[] fieldsArray = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			// System.out.println(fields[i].getName());
			String fieldName = fields[i].getName();
			 for (int j = 0; j < exceptNames.length; j++) {
				if (exceptNames[j].equals(fieldName)) {
					continue ;
				}
			}

			list.add(fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1));// 首字母大写
			fieldsArray[i] = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
		}
		return fieldsArray;
	}

	/**
	 * @author 郑明亮
	 * @time 2017年1月9日 下午7:36:21
	 * @description <p>
	 *              获取特定个数的传入类中声明的成员变量、常量的成员名称，并将首字母变为大写
	 *              <br>
	 * @param clz
	 * @param maxSize
	 *            需要得到的filed的最大个数，maxSize&lt;clz.getDeclaredFields().size()
	 * @return
	 */
	public static String[] getFields(Class<?> clz, int maxSize) {
		List<String> list = new ArrayList<String>();

		Field[] fields = clz.getDeclaredFields();
		String[] fieldsArray = new String[maxSize];
		for (int i = 0; i < maxSize; i++) {
			String fieldName = fields[i].getName();
			list.add(fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1));// 首字母大写
			fieldsArray[i] = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
		}
		return fieldsArray;
	}

	/**
	 * @author 郑明亮
	 * @time 2017年1月9日 下午7:34:15
	 * @description <p>
	 *              通过反射执行某特定实例中的特定方法
	 *              <br>
	 * @param t 要执行方法的实例
	 *
	 * @param methodName  方法名
	 *
	 * @param paramters  传入方法的参数，没有参数可不传
	 *
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Object methodInvoke(T t, String methodName,
			Class... paramters) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Class clz = t.getClass();
		Method method = clz.getMethod(methodName);
		return method.invoke(t);

	}

	/**
	 * <ol>
	 * <li>功能：获取包括父类所有的字段
	 * </ol>
	 * @author 沈建飞
	 * @param object
	 * @return
	 */
	public static Field[] getAllFields(Object object){
		Class clazz = object.getClass();
		List<Field> fieldList = new ArrayList<Field>();
		while (clazz != null){
			fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}
}
