/**
 * Created by 郑明亮 on 2023/8/16 11:13.
 */
package top.wys.utils.jdk;

import java.lang.reflect.Field;

import sun.misc.Unsafe;
import top.wys.utils.ReflectionUtils;
import top.wys.utils.TypeUtils;
import top.wys.utils.convert.ConvertUtils;
import top.wys.utils.valid.Preconditions;

/**
 * <p> 封装jdk的不安全类的使用</p>
 *
 * @author 郑明亮
 * @time 2023/8/16 11:13
 * @since 1.4.3
 */
public class UnsafeUtils {
    public static final Unsafe UNSAFE;
    static {
        Unsafe unsafe = null;
        try {
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            unsafe = (Unsafe) theUnsafeField.get(null);
        } catch (Throwable ignored) {
            // ignored
        }
        UNSAFE = unsafe;
    }

    public static Object getObject(Object o, long offset) {
        return UNSAFE.getObject(o, offset);
    }

    public static long getLong(Object o, long offset) {
        return UNSAFE.getLong(o, offset);
    }

    public static int getInt(Object o, long offset) {
        return UNSAFE.getInt(o, offset);
    }

    public static short getShort(Object o, long offset) {
        return UNSAFE.getShort(o, offset);
    }

    public static byte getByte(Object o, long offset) {
        return UNSAFE.getByte(o, offset);
    }

    public static float getFloat(Object o, long offset) {
        return UNSAFE.getFloat(o, offset);
    }

    public static double getDouble(Object o, long offset) {
        return UNSAFE.getDouble(o, offset);
    }

    public static boolean getBoolean(Object o, long offset) {
        return UNSAFE.getBoolean(o, offset);
    }

    public static char getChar(Object o, long offset) {
        return UNSAFE.getChar(o, offset);
    }

    public static void putObject(Object o, long offset, Object x) {
        UNSAFE.putObject(o, offset, x);
    }

    public static void putInt(Object o, long offset, int x) {
        UNSAFE.putInt(o, offset, x);
    }

    public static void putLong(Object o, long offset, long x) {
        UNSAFE.putLong(o, offset, x);
    }

    public static void putFloat(Object o, long offset, float x) {
        UNSAFE.putFloat(o, offset, x);
    }

    public static void putDouble(Object o, long offset, double x) {
        UNSAFE.putDouble(o, offset, x);
    }

    public static void putShort(Object o, long offset, short x) {
        UNSAFE.putShort(o, offset, x);
    }

    public static void putByte(Object o, long offset, byte x) {
        UNSAFE.putByte(o, offset, x);
    }

    public static void putChar(Object o, long offset, char x) {
        UNSAFE.putChar(o, offset, x);
    }

    public static void putBoolean(Object o, long offset, boolean x) {
        UNSAFE.putBoolean(o, offset, x);
    }

    /**
     * 直接在堆上分配一个cls类的实例,并返回这个实例的引用。
     *
     * 由于没有调用任何构造函数,分配出来的对象中的字段值都是默认值(数值类型是0,boolean是false,引用类型是null)。
     *
     * 并且完全忽略了cls类的访问权限,即使cls类没有public构造函数也可以分配实例。
     *
     * 这种完全绕过构造函数和访问控制的实例分配方式很危险,可能会破坏对象的状态,应该尽量避免使用或者很小心的使用
     * @param cls
     * @return
     * @throws InstantiationException
     */
    public static Object allocateInstance(Class<?> cls) throws InstantiationException {
        return UNSAFE.allocateInstance(cls);
    }

    public static long objectFieldOffset(Field field) {
        return UNSAFE.objectFieldOffset(field);
    }

    public static Object get(Object o,String fieldName){
        return get(o, ReflectionUtils.getField(o.getClass(),fieldName));
    }
    public static Object getNullable(Object o,String fieldName){
        return get(o, ReflectionUtils.getFieldNullable(o.getClass(),fieldName));
    }


    /**
     * 获取指定对象某个字段的值
     * @param o 对象
     * @param field 字段
     * @return
     */
    public static Object get(Object o,Field field){

        if(o == null || field == null){
            return null;
        }

        long offset = objectFieldOffset(field);

        if (field.getType() == boolean.class) {
            return getBoolean(o,offset);
        } else if (field.getType() == byte.class) {
            return getByte(o,offset);
        } else if (field.getType() == char.class) {
            return getChar(o,offset);
        } else if (field.getType() == short.class) {
            return getShort(o,offset);
        } else if (field.getType() == int.class) {
            return getInt(o,offset);
        } else if (field.getType() == long.class) {
            return getLong(o,offset);
        } else if (field.getType() == float.class) {
            return getFloat(o,offset);
        } else if (field.getType() == double.class) {
            return getDouble(o,offset);
        } else {
            return getObject(o,offset);
        }
    }

    public static void set(Object o,String fieldName,Object value){
        Field fieldNullable = ReflectionUtils.getFieldNullable(o.getClass(), fieldName);
        if(fieldNullable == null){
            return;
        }
        set(o, fieldNullable,value);
    }

    /**
     * 给指定对象的字段设置值（设置字段的值类型和字段类型必需一致）
     * @param o 设置值的对象
     * @param field 字段
     * @param value 设置的值（值和字段类型必需要一致）
     */
    public static void set(Object o,Field field,Object value){

        long offset = objectFieldOffset(field);

        if (value != null) {
            Class<?> type = field.getType();
            Class<?> targetType = value.getClass();
            if (type.isPrimitive() && !targetType.isPrimitive()) {
                type = TypeUtils.getBoxedType(type);
            }
            Preconditions.checkArgument(type == targetType, String.format("字段类型和设置的值类型不一致,value需要是%s类型",type.getName() ));
        } else {
            putObject(o,offset,null);
            return;
        }



        if (field.getType() == boolean.class) {
             putBoolean(o,offset, ConvertUtils.toBoolean(value));
        } else if (field.getType() == byte.class) {
            putByte(o,offset, (byte) value);
        } else if (field.getType() == char.class) {
            putChar(o,offset,(char)value);
        } else if (field.getType() == short.class) {
             putShort(o,offset,(short)value);
        } else if (field.getType() == int.class) {
            putInt(field,offset, (int) value);
        } else if (field.getType() == long.class) {
            putLong(o,offset, (long) value);
        } else if (field.getType() == float.class) {
            putFloat(o,offset, (float) value);
        } else if (field.getType() == double.class) {
            putDouble(o,offset, (double) value);
        } else {
            putObject(o,offset,value);
        }
    }
}
