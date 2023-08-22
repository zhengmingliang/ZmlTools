/**
 * Created by 郑明亮 on 2023/8/16 11:57.
 */
package top.wys.utils.reflect;

import java.lang.reflect.Field;

import top.wys.utils.ReflectionUtils;
import top.wys.utils.jdk.UnsafeUtils;
import top.wys.utils.valid.Preconditions;

/**
 * <p> this is your description</p>
 *
 * @author 郑明亮
 * @time 2023/8/16 11:57
 * @since 1.0.0
 */
public class UnsafeFieldAccessor {
    private final Field field;
    private final long fieldOffset;

    /**
     * Search parent class if <code>cls</code> doesn't have a field named <code>fieldName</code>.
     *
     * @param cls class
     * @param fieldName field name
     */
    public UnsafeFieldAccessor(Class<?> cls, String fieldName) {
        this(ReflectionUtils.getField(cls, fieldName));
    }

    public UnsafeFieldAccessor(Field field) {
        Preconditions.checkNotNull(field);
        this.field = field;
        this.fieldOffset = UnsafeUtils.objectFieldOffset(field);
        Preconditions.checkArgument(fieldOffset != -1);
    }

    public Field getField() {
        return field;
    }

    public boolean getBoolean(Object obj) {
        return UnsafeUtils.getBoolean(obj, fieldOffset);
    }

    public void putBoolean(Object obj, boolean value) {
        UnsafeUtils.putBoolean(obj, fieldOffset, value);
    }

    public byte getByte(Object obj) {
        return UnsafeUtils.getByte(obj, fieldOffset);
    }

    public void putByte(Object obj, byte value) {
        UnsafeUtils.putByte(obj, fieldOffset, value);
    }

    public char getChar(Object obj) {
        return UnsafeUtils.getChar(obj, fieldOffset);
    }

    public void putChar(Object obj, char value) {
        UnsafeUtils.putChar(obj, fieldOffset, value);
    }

    public short getShort(Object obj) {
        return UnsafeUtils.getShort(obj, fieldOffset);
    }

    public void putShort(Object obj, short value) {
        UnsafeUtils.putShort(obj, fieldOffset, value);
    }

    public int getInt(Object obj) {
        return UnsafeUtils.getInt(obj, fieldOffset);
    }

    public void putInt(Object obj, int value) {
        UnsafeUtils.putInt(obj, fieldOffset, value);
    }

    public long getLong(Object obj) {
        return UnsafeUtils.getLong(obj, fieldOffset);
    }

    public void putLong(Object obj, long value) {
        UnsafeUtils.putLong(obj, fieldOffset, value);
    }

    public float getFloat(Object obj) {
        return UnsafeUtils.getFloat(obj, fieldOffset);
    }

    public void putFloat(Object obj, float value) {
        UnsafeUtils.putFloat(obj, fieldOffset, value);
    }

    public double getDouble(Object obj) {
        return UnsafeUtils.getDouble(obj, fieldOffset);
    }

    public void putDouble(Object obj, double value) {
        UnsafeUtils.putDouble(obj, fieldOffset, value);
    }

    public Object getObject(Object obj) {
        return UnsafeUtils.getObject(obj, fieldOffset);
    }

    public void putObject(Object obj, Object value) {
        UnsafeUtils.putObject(obj, fieldOffset, value);
    }
}
