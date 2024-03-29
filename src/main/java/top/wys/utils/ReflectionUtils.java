package top.wys.utils;


import com.google.common.collect.Lists;

import org.apache.logging.log4j.core.util.Throwables;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nullable;

import top.wys.utils.collection.ArrayUtils;
import top.wys.utils.jdk.UnsafeUtils;
import top.wys.utils.reflect.FieldAccessor;
import top.wys.utils.valid.Preconditions;


/**
 * @author 郑明亮
 * @time：2016年12月20日 下午4:34:52
 * @description <p> 反射相关工具类<br>
 */
public class ReflectionUtils {


	private static final Method[] EMPTY_METHOD_ARRAY = new Method[0];

	/**
	 * Cache for {@link Class#getDeclaredFields()}, allowing for fast iteration.
	 */
	private static final Map<Class<?>, Field[]> declaredFieldsCache = new ConcurrentHashMap<>(256);
	/**
	 * 根据字段名缓存Field
	 */
	private static final Map<String, Field> declaredFieldCache = new ConcurrentHashMap<>(256);

	/**
	 * Cache for {@link Class#getDeclaredMethods()} plus equivalent default methods
	 * from Java 8 based interfaces, allowing for fast iteration.
	 */
	private static final Map<Class<?>, Method[]> declaredMethodsCache = new ConcurrentHashMap<>(256);
	/**
	 * 根据方法名缓存 Method
	 */
	private static final Map<String, Method> declaredMethodCache = new ConcurrentHashMap<>(256);


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

	public static Field getField(Class<?> cls, String fieldName) {
		String key = cls.getName() + "." + fieldName;
		Field cache = declaredFieldCache.get(key);
		if(cache != null){
			return cache;
		}
		Field field = getFieldNullable(cls, fieldName);
		if (field == null) {
			String msg = String.format("class %s doesn't have field %s", cls, fieldName);
			throw new IllegalArgumentException(msg);
		}
		declaredFieldCache.put(key,field);
		return field;
	}

	public static Field getFieldNullable(Class<?> cls, String fieldName) {
		Class<?> clazz = cls;
		do {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					return field;
				}
			}
			clazz = clazz.getSuperclass();
		} while (clazz != null);
		return null;
	}

	public static List<Field> getFields(Class<?> cls, boolean searchParent) {
		Preconditions.checkNotNull(cls);
		List<Field> fields = new ArrayList<>();
		if (searchParent) {
			Class<?> clazz = cls;
			do {
				Collections.addAll(fields, clazz.getDeclaredFields());
				clazz = clazz.getSuperclass();
			} while (clazz != null);
		} else {
			Collections.addAll(fields, cls.getDeclaredFields());
		}
		return fields;
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
			Class<?>... paramters) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return methodInvoke(t,methodName,null,paramters);

	}

	public static <T> Object methodInvoke(T t, String methodName,Object[] params,
										  Class<?>... paramters) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Class clz = t.getClass();
		Method method = clz.getMethod(methodName,paramters);
		if (params != null) {
			return method.invoke(t,params);
		}
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
		Field[] fields = declaredFieldsCache.get(clazz);
		if (fields != null) {
			return fields;
		}
		List<Field> fieldList = new ArrayList<Field>();
		while (clazz != null){
			fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		declaredFieldsCache.put(object.getClass(), fields);
		return fields;
	}

	/**
	 * Gets parameterized type.
	 *
	 * @param interfaceType interface type must not be null
	 * @param implementationClass implementation class of the interface must not be null
	 * @return parameterized type of the interface or null if it is mismatch
	 */
	public static ParameterizedType getParameterizedType(Class<?> interfaceType,
														 Class<?> implementationClass) {
		Assert.notNull(interfaceType, "Interface type must not be null");
		Assert.isTrue(interfaceType.isInterface(), "The give type must be an interface");

		if (implementationClass == null) {
			// If the super class is Object parent then return null
			return null;
		}

		// Get parameterized type
		ParameterizedType currentType =
				getParameterizedType(interfaceType, implementationClass.getGenericInterfaces());

		if (currentType != null) {
			// return the current type
			return currentType;
		}

		Class<?> superclass = implementationClass.getSuperclass();

		return getParameterizedType(interfaceType, superclass);
	}

	/**
	 * Gets parameterized type.
	 *
	 * @param superType super type must not be null (super class or super interface)
	 * @param genericTypes generic type array
	 * @return parameterized type of the interface or null if it is mismatch
	 */
	public static ParameterizedType getParameterizedType(Class<?> superType,
														 Type... genericTypes) {
		Assert.notNull(superType, "Interface or super type must not be null");

		ParameterizedType currentType = null;

		for (Type genericType : genericTypes) {
			if (genericType instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) genericType;
				if (parameterizedType.getRawType().getTypeName().equals(superType.getTypeName())) {
					currentType = parameterizedType;
					break;
				}
			}
		}

		return currentType;
	}

	/**
	 * 使给定的方法可访问，如果需要，显式地将其设置为可访问。setAccessible（true）方法仅在实际需要时调用，
	 * 以避免与JVM SecurityManager（如果处于活动状态）发生不必要的冲突
	 *
	 * @param field 字段
	 */
	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) ||
				!Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
				Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}


	/**
	 * 使构造方法可以被访问
	 * @param constructor
	 */
	public static void makeAccessible(Constructor constructor){
		if (!isAccessible(constructor) && !constructor.isAccessible()) {
			constructor.setAccessible(true);
		}
	}


	/**
	 * 判断该成员 {@link Member} 是否public 并且所在类也是public的
	 *
	 * @param <T> 要测试其可访问性的对象的类型
	 * @param member 要检查公共可访问性的成员(不能是{@code null})。
	 * @return 返回{@code true} 如果 {@code member} 是 public 的并且是在一个public 类中.
	 * @throws NullPointerException if {@code member} is {@code null}.
	 */
	public static <T extends AccessibleObject & Member> boolean isAccessible(final T member) {
		Objects.requireNonNull(member, "No member provided");
		return Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers());
	}


	/**
	 * 使给定的字段可访问，如果需要，显式地将其设置为可访问。setAccessible（true）方法仅在实际需要时调用，
	 * 以避免与JVM SecurityManager（如果处于活动状态）发生不必要的冲突
	 * @param method 方法
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) ||
				!Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	public static boolean isEqualsMethod(@Nullable Method method) {
		if (method == null || !method.getName().equals("equals")) {
			return false;
		}
		if (method.getParameterCount() != 1) {
			return false;
		}
		return method.getParameterTypes()[0] == Object.class;
	}

	/**
	 * Determine whether the given method is a "hashCode" method.
	 * @see java.lang.Object#hashCode()
	 */
	public static boolean isHashCodeMethod(@Nullable Method method) {
		return (method != null && method.getName().equals("hashCode") && method.getParameterCount() == 0);
	}

	/**
	 * Determine whether the given method is a "toString" method.
	 * @see java.lang.Object#toString()
	 */
	public static boolean isToStringMethod(@Nullable Method method) {
		return (method != null && method.getName().equals("toString") && method.getParameterCount() == 0);
	}

	/**
	 * Determine whether the given method is originally declared by {@link java.lang.Object}.
	 */
	public static boolean isObjectMethod(@Nullable Method method) {
		return (method != null && (method.getDeclaringClass() == Object.class ||
				isEqualsMethod(method) || isHashCodeMethod(method) || isToStringMethod(method)));
	}

	public static Method[] getDeclaredMethods(Class<?> clazz) {
		return getDeclaredMethods(clazz, true);
	}

	public static Method getDeclaredMethod(Class<?> clazz,String methodName,Class<?>... parameterTypes) throws NoSuchMethodException {
		String parameterTypeKey = "";
		if (ArrayUtils.isNotEmpty(parameterTypes)) {
			for (Class<?> parameterType : parameterTypes) {
				parameterTypeKey += parameterType.getSimpleName()+",";
			}
			parameterTypeKey = "(" + parameterTypeKey.substring(0,parameterTypeKey.length() - 1) + ")";
		}
		String key = clazz.getName() + methodName + parameterTypeKey;
		Method method = declaredMethodCache.get(key);
		if(method != null){
		   return method;
		}
		method = clazz.getMethod(methodName);
		declaredMethodCache.put(key,method);
		return method;
	}

	private static Method[] getDeclaredMethods(Class<?> clazz, boolean defensive) {
		Assert.notNull(clazz, "Class must not be null");
		Method[] result = declaredMethodsCache.get(clazz);
		if (result == null) {
			try {
				Method[] declaredMethods = clazz.getDeclaredMethods();
				List<Method> defaultMethods = findConcreteMethodsOnInterfaces(clazz);
				if (defaultMethods != null) {
					result = new Method[declaredMethods.length + defaultMethods.size()];
					System.arraycopy(declaredMethods, 0, result, 0, declaredMethods.length);
					int index = declaredMethods.length;
					for (Method defaultMethod : defaultMethods) {
						result[index] = defaultMethod;
						index++;
					}
				}
				else {
					result = declaredMethods;
				}
				declaredMethodsCache.put(clazz, (result.length == 0 ? EMPTY_METHOD_ARRAY : result));
			}
			catch (Throwable ex) {
				throw new IllegalStateException("Failed to introspect Class [" + clazz.getName() +
						"] from ClassLoader [" + clazz.getClassLoader() + "]", ex);
			}
		}
		return (result.length == 0 || !defensive) ? result : result.clone();
	}

	@Nullable
	private static List<Method> findConcreteMethodsOnInterfaces(Class<?> clazz) {
		List<Method> result = null;
		for (Class<?> ifc : clazz.getInterfaces()) {
			for (Method ifcMethod : ifc.getMethods()) {
				if (!Modifier.isAbstract(ifcMethod.getModifiers())) {
					if (result == null) {
						result = new ArrayList<>();
					}
					result.add(ifcMethod);
				}
			}
		}
		return result;
	}

	public static void clearCache() {
//		declaredMethodsCache.clear();
		declaredFieldsCache.clear();
	}

	public static PropertyDescriptor[] getBeanProperties(Class type) {
		return getPropertiesHelper(type, true, true);
	}

	public static PropertyDescriptor[] getBeanGetters(Class type) {
		return getPropertiesHelper(type, true, false);
	}

	public static PropertyDescriptor[] getBeanSetters(Class type) {
		return getPropertiesHelper(type, false, true);
	}

	private static PropertyDescriptor[] getPropertiesHelper(Class type, boolean read, boolean write) {
		try {
			BeanInfo info = Introspector.getBeanInfo(type, Object.class);
			PropertyDescriptor[] all = info.getPropertyDescriptors();
			if (read && write) {
				return all;
			}
			List properties = new ArrayList(all.length);
			for (int i = 0; i < all.length; i++) {
				PropertyDescriptor pd = all[i];
				if ((read && pd.getReadMethod() != null) ||
						(write && pd.getWriteMethod() != null)) {
					properties.add(pd);
				}
			}
			return (PropertyDescriptor[]) properties.toArray(new PropertyDescriptor[properties.size()]);
		}
		catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return emptyProperty;
	}

	private static final PropertyDescriptor[] emptyProperty = {};

	public static <T> T instantiate(final Class<T> clazz) {
		Objects.requireNonNull(clazz, "No class provided");
		final Constructor<T> constructor = getDefaultConstructor(clazz);
		try {
			return constructor.newInstance();
		} catch (final LinkageError | InstantiationException e) {
			// LOG4J2-1051
			// On platforms like Google App Engine and Android, some JRE classes are not supported: JMX, JNDI, etc.
			throw new IllegalArgumentException(e);
		} catch (final IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (final InvocationTargetException e) {
			Throwables.rethrow(e.getCause());
			throw new InternalError("Unreachable");
		}
	}

	/**
	 * 获取默认的无参构造方法.
	 *
	 * @param clazz 要为其查找构造函数的类
	 * @param <T>   构造函数创建的类型
	 * @return 给定类的默认构造函数
	 * @throws IllegalStateException 给定类的默认构造函数如果找不到则抛出该异常
	 */
	public static <T> Constructor<T> getDefaultConstructor(final Class<T> clazz) {
		Objects.requireNonNull(clazz, "No class provided");
		try {
			final Constructor<T> constructor = clazz.getDeclaredConstructor();
			makeAccessible(constructor);
			return constructor;
		} catch (final NoSuchMethodException ignored) {
			try {
				final Constructor<T> constructor = clazz.getConstructor();
				makeAccessible(constructor);
				return constructor;
			} catch (final NoSuchMethodException e) {
				throw new IllegalStateException("没有默认的无参构造方法",e);
			}
		}
	}


	public static Object getObjectFieldValue(Object obj, String fieldName) {
		Class<?> cls = obj.getClass();
		Preconditions.checkArgument(!cls.isPrimitive());
		while (cls != Object.class) {
			try {
				Field field = cls.getDeclaredField(fieldName);
				long fieldOffset = UnsafeUtils.objectFieldOffset(field);
				return UnsafeUtils.getObject(obj, fieldOffset);
				// CHECKSTYLE.OFF:EmptyCatchBlock
			} catch (NoSuchFieldException ignored) {
			}
			// CHECKSTYLE.ON:EmptyCatchBlock
			cls = cls.getSuperclass();
		}
		return null;
	}

	public static List<Object> getFieldValues(Collection<Field> fields, Object o) {
		List<Object> results = new ArrayList<>(fields.size());
		for (Field field : fields) {
			// UNSAFE.objectFieldOffset(field)无法处理基本数据类型字段.
			Object fieldValue = FieldAccessor.createAccessor(field).get(o);
			results.add(fieldValue);
		}
		return results;
	}
}
