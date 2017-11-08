package top.wys.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 郑明亮
 * @Time：2016年12月20日 下午4:34:52 
 * @Description <p> 反射相关工具类</p>
 */
public class ReflectionUtils {
	private ReflectionUtils() {
		new UnsupportedOperationException(" you can not instantiate me");
	}

	


	/**
	 * @author 郑明亮
	 * @Time：2016年12月19日 下午5:40:50 TODO 获取传入类中声明的成员变量、常量的成员名称，并将首字母变为大写
	 * @param clz
	 * @return
	 */
	public static String[] getFields(Class<?> clz) {
		List<String> list = new ArrayList<String>();

		Field[] fields = clz.getDeclaredFields();
		String[] fieldsArray = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			// System.out.println(fields[i].getName());
			String fieldName = fields[i].getName();
			list.add(fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1));// 首字母大写
			fieldsArray[i] = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
		}
		System.out.println(list.toString());
		return fieldsArray;
	}

	/**
	 * @author 郑明亮
	 * @Time 2017年1月9日 下午7:47:27
	 * @Description <p>
	 *              获取传入类中声明的成员变量、常量的成员名称，并将首字母变为大写，特定成员除外
	 *              </p>
	 * @param clz
	 * @param exceptNames
	 *            不包含的声明的成员变量、常量的名称，必需与实体类中的成员名称大小写一致
	 * @return
	 */
	public static String[] getFields(Class<?> clz, String... exceptNames) {
		List<String> list = new ArrayList<String>();

		Field[] fields = clz.getDeclaredFields();
		String[] fieldsArray = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			// System.out.println(fields[i].getName());
			String fieldName = fields[i].getName();
			jump: for (int j = 0; j < exceptNames.length; j++) {
				if (exceptNames[j].equals(fieldName)) {
					continue jump;
				}
			}

			list.add(fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1));// 首字母大写
			fieldsArray[i] = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
		}
		System.out.println(list.toString());
		return fieldsArray;
	}

	/**
	 * @author 郑明亮
	 * @Time 2017年1月9日 下午7:36:21
	 * @Description <p>
	 *              获取特定个数的传入类中声明的成员变量、常量的成员名称，并将首字母变为大写
	 *              </p>
	 * @param clz
	 * @param maxSize
	 *            需要得到的filed的最大个数，maxSize<clz.getDeclaredFields().size()
	 * @return
	 */
	public static String[] getFields(Class<?> clz, int maxSize) {
		List<String> list = new ArrayList<String>();

		Field[] fields = clz.getDeclaredFields();
		String[] fieldsArray = new String[maxSize];
		for (int i = 0; i < maxSize; i++) {
			// System.out.println(fields[i].getName());
			String fieldName = fields[i].getName();
			list.add(fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1));// 首字母大写
			fieldsArray[i] = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
		}
		System.out.println(list.toString());
		return fieldsArray;
	}

	/**
	 * @author 郑明亮
	 * @Time 2017年1月9日 下午7:34:15
	 * @Description <p>
	 *              通过反射执行某特定实例中的特定方法
	 *              </p>
	 * @param t
	 *            要执行方法的实例
	 * @param methodName
	 *            方法名
	 * @param paramters
	 *            传入方法的参数，没有参数可不传
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Object methodInvoke(T t, String methodName,
			Class... paramters) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class clz = t.getClass();
		Method method = clz.getDeclaredMethod(methodName);
		return method.invoke(t);

	}
}
