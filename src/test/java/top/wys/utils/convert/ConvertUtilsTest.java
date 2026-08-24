package top.wys.utils.convert;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConvertUtils} 单元测试。
 */
public class ConvertUtilsTest {

    @Test
    public void toString_shouldConvertObjectAndUseDefaultValueForNull() {
        assertEquals("1", ConvertUtils.toString(1));
        assertEquals("0", ConvertUtils.toString((char) 48));
        assertEquals("1", ConvertUtils.toString((char) 49));
        assertEquals("1000", ConvertUtils.toString(1000L));
        assertNull(ConvertUtils.toString(null));
        assertEquals("", ConvertUtils.toString(null, ""));
    }

    @Test
    public void toNoneNullString_shouldReplaceNullLiteralAndNullWithDefaultValue() {
        assertEquals("value", ConvertUtils.toNoneNullString("value"));
        assertEquals("", ConvertUtils.toNoneNullString(null));
        assertEquals("", ConvertUtils.toNoneNullString("null"));
        assertEquals("default", ConvertUtils.toNoneNullString(null, "default"));
        assertEquals("default", ConvertUtils.toNoneNullString("NULL", "default"));
    }
    @Test
    public void toNoneEmptyString_shouldReplaceEmptyValuesWithDefaultValue() {
        assertEquals("value", ConvertUtils.toNoneEmptyString("value", "default"));
        assertEquals("default", ConvertUtils.toNoneEmptyString(null, "default"));
        assertEquals("default", ConvertUtils.toNoneEmptyString("", "default"));
        assertEquals("default", ConvertUtils.toNoneEmptyString("null", "default"));
    }

    @Test
    public void toNoneNullObject_shouldReturnDefaultValueForNull() {
        String defaultValue = "default";

        assertSame(defaultValue, ConvertUtils.toNoneNullObject(null, defaultValue));
    }

    @Test
    public void toBoolean_shouldConvertBooleanStringsAndCustomTrueValues() {
        assertTrue(ConvertUtils.toBoolean(true));
        assertTrue(ConvertUtils.toBoolean("true"));
        assertTrue(ConvertUtils.toBoolean("True"));
        assertTrue(ConvertUtils.toBoolean("yes"));
        assertTrue(ConvertUtils.toBoolean("1"));
        assertFalse(ConvertUtils.toBoolean(false));
        assertFalse(ConvertUtils.toBoolean("false"));
        assertFalse(ConvertUtils.toBoolean(null));
        assertTrue(ConvertUtils.toBoolean(1, 1));
        assertFalse(ConvertUtils.toBoolean(1, 2));
    }

    @Test
    public void getNumberFromBoolean_shouldReturnOneForTrueAndZeroForFalse() {
        assertEquals(1, ConvertUtils.getNumberFromBoolean(true).intValue());
        assertEquals(0, ConvertUtils.getNumberFromBoolean(false).intValue());
        assertEquals(1, ConvertUtils.getNumberFromBoolean("yes").intValue());
    }

    @Test
    public void toInteger_shouldConvertNumbersAndReturnDefaultForInvalidValue() {
        assertEquals(Integer.valueOf(1), ConvertUtils.toInteger(1));
        assertEquals(Integer.valueOf(2), ConvertUtils.toInteger(2.1221));
        assertEquals(Integer.valueOf(3), ConvertUtils.toInteger("3.921"));
        assertEquals(Integer.valueOf(0), ConvertUtils.toInteger(".921"));
        assertEquals(Integer.valueOf(3), ConvertUtils.toInteger("3.921L", 0));
        assertNull(ConvertUtils.toInteger(null));
        assertEquals(Integer.valueOf(7), ConvertUtils.toInteger("not a number", 7));
    }

    @Test
    public void toInt_shouldConvertUnsignedBytesAndObjects() {
        assertEquals(255, ConvertUtils.toInt((byte) -1));
        assertEquals(1, ConvertUtils.toInt(1));
        assertEquals(2, ConvertUtils.toInt(2.9));
        assertEquals(3, ConvertUtils.toInt("3.921"));
        assertEquals(1, ConvertUtils.toInt("yes"));
        assertEquals(9, ConvertUtils.toInt(null, 9));
        assertEquals(0x01020304, ConvertUtils.toInt(new byte[]{1, 2, 3, 4}));
    }

    @Test
    public void toDouble_shouldConvertNumbersAndReturnDefaultForInvalidValue() {
        assertEquals(Double.valueOf(2.1221D), ConvertUtils.toDouble(2.1221D));
        assertEquals(3.921D, ConvertUtils.toDoubleValue("3.921"), 0D);
        assertEquals(1D, ConvertUtils.toDoubleValue("yes"), 0D);
        assertEquals(0D, ConvertUtils.toDoubleValue(null), 0D);
        assertNull(ConvertUtils.toDouble(null));
        assertEquals(Double.valueOf(7D), ConvertUtils.toDouble("not a number", 7D));
    }

    @Test
    public void toLong_shouldConvertNumbersAndReturnDefaultForInvalidValue() {
        assertEquals(Long.valueOf(2L), ConvertUtils.toLong(2.9D));
        assertEquals(3L, ConvertUtils.toLongValue("3.921"));
        assertEquals(1L, ConvertUtils.toLongValue("true"));
        assertEquals(9L, ConvertUtils.toLongValue(null, 9L));
        assertNull(ConvertUtils.toLong(null));
        assertEquals(Long.valueOf(7L), ConvertUtils.toLong("not a number", 7L));
    }

    @Test
    public void toBigDecimal_shouldConvertNumbersAndReturnDefaultForInvalidValue() {
        assertEquals(new BigDecimal("12.50"), ConvertUtils.toBigDecimal("12.50"));
        assertEquals(new BigDecimal("12.50"), ConvertUtils.toBigDecimal("amount: 12.50"));
        assertEquals(BigDecimal.ZERO, ConvertUtils.toBigDecimal(""));
        assertEquals(BigDecimal.ONE, ConvertUtils.toBigDecimal("yes"));
        assertNull(ConvertUtils.toBigDecimal(null));
        assertEquals(new BigDecimal("7"), ConvertUtils.toBigDecimal("not a number", new BigDecimal("7")));
    }

    @Test
    public void toDate_shouldConvertDateCalendarAndJavaTimeValues() {
        Date original = new Date(1_650_000_000_123L);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1_650_000_000_123L);
        LocalDate localDate = LocalDate.of(2024, 2, 4);
        LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 4, 12, 12, 12);

        assertSame(original, ConvertUtils.toDate(original));
        assertEquals(calendar.getTime(), ConvertUtils.toDate(calendar));
        assertEquals(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                ConvertUtils.toDate(localDate));
        assertEquals(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()),
                ConvertUtils.toDate(localDateTime));
        assertNull(ConvertUtils.toDate(null));
        assertEquals(original, ConvertUtils.toDate(null, original));
    }

    @Test
    public void toDate_shouldConvertDateStringsAndTimestamps() {
        assertEquals(dateAt(2021, Calendar.NOVEMBER, 12, 0, 0, 0),
                ConvertUtils.toDate("2021-11-12"));
        assertEquals(dateAt(2021, Calendar.NOVEMBER, 12, 22, 45, 32),
                ConvertUtils.toDate("2021-11-12 22:45:32"));
        assertEquals(new Date(1_638_702_321_000L), ConvertUtils.toDate("1638702321"));
        assertEquals(new Date(1_638_702_321_200L), ConvertUtils.toDate("1638702321200"));
    }

    @Test
    public void toBinaryString_shouldReturnFixedWidthBinaryValues() {
        assertEquals("00000000", ConvertUtils.toBinaryString((byte) 0));
        assertEquals("00000101", ConvertUtils.toBinaryString((byte) 5));
        assertEquals("11111111", ConvertUtils.toBinaryString((byte) -1));
        assertEquals(32, ConvertUtils.toBinaryString(5).length());
        assertTrue(ConvertUtils.toBinaryString(5).endsWith("00000101"));
        assertEquals(64, ConvertUtils.toBinaryString(5L).length());
        assertTrue(ConvertUtils.toBinaryString(5L).endsWith("00000101"));
    }

    private static Date dateAt(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day, hour, minute, second);
        return calendar.getTime();
    }
    @Test
    public void toDate() {
        System.out.println("ConvertUtils.toDate(\"20211112\",null) = " + ConvertUtils.toDate("20211112", null));
        System.out.println("ConvertUtils.toDate(\"2021-11-12\",null) = " + ConvertUtils.toDate("2021-11-12", null));
        System.out.println("ConvertUtils.toDate(\"2021/11/12\",null) = " + ConvertUtils.toDate("2021/11/12", null));
        System.out.println(
                "ConvertUtils.toDate(\"2021年11月12日\",null) = " + ConvertUtils.toDate("2021年11月12日", null));
        System.out.println("ConvertUtils.toDate(\"2021년11월12일\",null) = " + ConvertUtils.toDate("2021년11월12일")); //
        System.out.println(
                "ConvertUtils.toDate(\"20211112214532\",null) = " + ConvertUtils.toDate("20211112214532", null));
        System.out.println(
                "ConvertUtils.toDate(\"20211112 22:45:32\",null) = " + ConvertUtils.toDate("20211112 22:45:32", null));
        System.out.println(
                "ConvertUtils.toDate(\"2021.11.12 22:45:32\",null) = " + ConvertUtils.toDate("2021.11.12 22:45:32",
                        null));
        System.out.println(
                "ConvertUtils.toDate(\"2021-12-21 23:10:33\") = " + ConvertUtils.toDate("2021-12-21 23:10:33"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4\") = " + ConvertUtils.toDate("2021/12/4"));
        System.out.println("ConvertUtils.toDate(\"2021/5/31\") = " + ConvertUtils.toDate("2021/5/31"));
        System.out.println("ConvertUtils.toDate(\"2021/5/3\") = " + ConvertUtils.toDate("2021/5/3"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4 1:25\") = " + ConvertUtils.toDate("2021/5/31 1:25"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4 1:2\") = " + ConvertUtils.toDate("2021/5/31 1:2"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4 12:25\") = " + ConvertUtils.toDate("2021/5/31 12:25"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4 12:5\") = " + ConvertUtils.toDate("2021/5/31 12:5"));
        System.out.println("ConvertUtils.toDate(\"2021-11-28T22:33:31+0800\") = " + ConvertUtils.toDate(
                "2021-11-28T22:33:31+0800"));

        System.out.println("ConvertUtils.toDate(\"202111122145\") = " + ConvertUtils.toDate("202111122145"));
        System.out.println("ConvertUtils.toDate(\"1638702321\") = " + ConvertUtils.toDate("1638702321"));
        System.out.println("ConvertUtils.toDate(\"1638702321200\") = " + ConvertUtils.toDate("1638702321200"));
        System.out.println("ConvertUtils.toDate(\"2022-03-01T19:26:28+08:00\") = " + ConvertUtils.toDate(
                "2022-03-01T19:26:28+08:00"));
        System.out.println(
                "ConvertUtils.toDate(\"2022-03-01T19:26:28\") = " + ConvertUtils.toDate("2022-03-01T19:26:28"));
    }
}
