package top.wys.utils.convert;

import org.junit.Test;

/**
 * Created by 郑明亮 on 2021/12/4 1:25.
 */
public class ConvertUtilsTest {

    @Test
    public void testToString() {
        System.out.println(ConvertUtils.toString(1));
        System.out.println(ConvertUtils.toString((char)48));
        System.out.println(ConvertUtils.toString((char)49));
        System.out.println(ConvertUtils.toString(null,""));
        System.out.println(ConvertUtils.toString(1000L));
    }


    @Test
    public void toBoolean() {
        System.out.println("ConvertUtils.toBoolean(true) = " + ConvertUtils.toBoolean(true));
        System.out.println("ConvertUtils.toBoolean(\"true\") = " + ConvertUtils.toBoolean("true"));
        System.out.println("ConvertUtils.toBoolean(\"True\") = " + ConvertUtils.toBoolean("True"));
        System.out.println("ConvertUtils.toBoolean(\"False\") = " + ConvertUtils.toBoolean("False"));
        System.out.println("ConvertUtils.toBoolean(\"false\") = " + ConvertUtils.toBoolean("false"));
        System.out.println("ConvertUtils.toBoolean(false) = " + ConvertUtils.toBoolean(false));
        System.out.println("ConvertUtils.toBoolean(null) = " + ConvertUtils.toBoolean(null));
        System.out.println("ConvertUtils.toBoolean(new Boolean(true)) = " + ConvertUtils.toBoolean(new Boolean(true)));
        System.out.println("ConvertUtils.toBoolean(1,1) = " + ConvertUtils.toBoolean(1, 1));
    }



    @Test
    public void toInteger() {
        System.out.println("ConvertUtils.toInteger(1) = " + ConvertUtils.toInteger(1));
        System.out.println("ConvertUtils.toInteger(1) = " + ConvertUtils.toInteger(1));
        System.out.println("ConvertUtils.toInteger(2.1221) = " + ConvertUtils.toInteger(2.1221));
        System.out.println("ConvertUtils.toInteger(3.921) = " + ConvertUtils.toInteger(3.921));
        System.out.println("ConvertUtils.toInteger(\".921\") = " + ConvertUtils.toInteger(".921"));
        System.out.println("ConvertUtils.toInteger(\"3.921\") = " + ConvertUtils.toInteger("3.921"));
        System.out.println("ConvertUtils.toInteger(\"3.921L\",0) = " + ConvertUtils.toInteger("3.921L", 0));
    }

    @Test
    public void toInt() {
    }

    @Test
    public void testToInteger() {
    }

    @Test
    public void toDouble() {
    }

    @Test
    public void toDoubleValue() {
    }

    @Test
    public void testToDouble() {
    }

    @Test
    public void toLong() {
    }

    @Test
    public void toLongValue() {
    }

    @Test
    public void testToLong() {
    }

    @Test
    public void toBigDecimal() {
    }

    @Test
    public void testToBigDecimal() {
    }

    @Test
    public void toDate() {
        System.out.println("ConvertUtils.toDate(\"20211112\",null) = " + ConvertUtils.toDate("20211112", null));
        System.out.println("ConvertUtils.toDate(\"2021-11-12\",null) = " + ConvertUtils.toDate("2021-11-12", null));
        System.out.println("ConvertUtils.toDate(\"2021/11/12\",null) = " + ConvertUtils.toDate("2021/11/12", null));
        System.out.println("ConvertUtils.toDate(\"2021年11月12日\",null) = " + ConvertUtils.toDate("2021年11月12日", null));
        System.out.println("ConvertUtils.toDate(\"2021년11월12일\",null) = " + ConvertUtils.toDate("2021년11월12일"));//
        System.out.println("ConvertUtils.toDate(\"20211112214532\",null) = " + ConvertUtils.toDate("20211112214532", null));
        System.out.println("ConvertUtils.toDate(\"20211112 22:45:32\",null) = " + ConvertUtils.toDate("20211112 22:45:32", null));
        System.out.println("ConvertUtils.toDate(\"2021.11.12 22:45:32\",null) = " + ConvertUtils.toDate("2021.11.12 22:45:32", null));
        System.out.println("ConvertUtils.toDate(\"2021-12-21 23:10:33\") = " + ConvertUtils.toDate("2021-12-21 23:10:33"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4\") = " + ConvertUtils.toDate("2021/12/4"));
        System.out.println("ConvertUtils.toDate(\"2021/5/31\") = " + ConvertUtils.toDate("2021/5/31"));
        System.out.println("ConvertUtils.toDate(\"2021/5/3\") = " + ConvertUtils.toDate("2021/5/3"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4 1:25\") = " + ConvertUtils.toDate("2021/5/31 1:25"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4 1:2\") = " + ConvertUtils.toDate("2021/5/31 1:2"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4 12:25\") = " + ConvertUtils.toDate("2021/5/31 12:25"));
        System.out.println("ConvertUtils.toDate(\"2021/12/4 12:5\") = " + ConvertUtils.toDate("2021/5/31 12:5"));
        System.out.println("ConvertUtils.toDate(\"2021-11-28T22:33:31+0800\") = " + ConvertUtils.toDate("2021-11-28T22:33:31+0800"));

        System.out.println("ConvertUtils.toDate(\"202111122145\") = " + ConvertUtils.toDate("202111122145"));
        System.out.println("ConvertUtils.toDate(\"1638702321\") = " + ConvertUtils.toDate("1638702321"));
        System.out.println("ConvertUtils.toDate(\"1638702321200\") = " + ConvertUtils.toDate("1638702321200"));
        System.out.println("ConvertUtils.toDate(\"2022-03-01T19:26:28+08:00\") = " + ConvertUtils.toDate("2022-03-01T19:26:28+08:00"));
        System.out.println("ConvertUtils.toDate(\"2022-03-01T19:26:28\") = " + ConvertUtils.toDate("2022-03-01T19:26:28"));
    }
}