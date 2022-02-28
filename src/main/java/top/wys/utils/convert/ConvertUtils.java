package top.wys.utils.convert;
/**
 * Created by 郑明亮 on 2021/12/3 23:55.
 */

import org.jetbrains.annotations.NotNull;
import top.wys.utils.DataUtils;
import top.wys.utils.DateUtils;
import top.wys.utils.NumberUtils;
import top.wys.utils.collection.Booleans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * <ol>
 *  2021/12/3 23:55 <br>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 */
public class ConvertUtils {

    private static final String NULL_STRING = "null";
    private static final String[] TRUE_FLAGS = {"ok", "y","yes","t","true","1"};

    /**
     * 转换为字符串类型的数据
     *
     * @param obj 要转换类型的对象
     * @return
     */
    public static String toString(Object obj) {
        return toString(obj, null);
    }

    /**
     * 转换为字符串
     *
     * @param obj          要转换为字符串的对象
     * @param defaultValue 当obj为null时，默认返回的值
     * @return
     */
    public static String toString(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        return obj.toString();
    }

    /**
     * 转换为非null的字符串，当为null或“null”时，默认转为 "";
     * @param obj
     * @return
     */
    public static String toNoneNullString(Object obj){

        return toNoneNullString(obj,"");
    }
    /**
     * 转换为非null的字符串，当为null或“null”时，默认转为 "";
     * @param str
     * @return
     */
    public static String toNoneNullString(String str){

        return toNoneNullString(str,"");
    }

    /**
     * 转换为非null字符串
     * @param obj
     * @param defaultValue 当被转换的字符串为null时，返回的默认值
     * @return
     */
    public static String toNoneNullString(Object obj,String defaultValue){
        if(obj == null){
            return defaultValue;
        }

        String strValue = obj.toString();
        if (NULL_STRING.equalsIgnoreCase(strValue)) {
            return defaultValue;
        }

        return strValue;
    }

    /**
     * 转换为非空字符串
     * @param obj
     * @param defaultValue 当被转换的字符串为空字符串时，返回的默认值
     * @return
     */
    public static String toNoneEmptyString(String obj,String defaultValue){
        if(obj == null){
            return defaultValue;
        }

        String strValue = obj;
        if (strValue.length() == 0 || NULL_STRING.equalsIgnoreCase(strValue)) {
            return defaultValue;
        }

        return strValue;
    }

    /**
     * 转换为非null对象实例
     * @param obj
     * @param defaultValue 当被转换的字符串为null时，返回的默认值
     * @return
     */
    public static <T> T  toNoneNullObject(Object obj,T defaultValue){
        if(obj == null){
            return defaultValue;
        }

        String strValue = obj.toString();
        if (NULL_STRING.equalsIgnoreCase(strValue)) {
            return defaultValue;
        }

        return defaultValue;
    }


    /**
     * 转换为非null字符串
     * @param str
     * @param defaultValue 当被转换的字符串为null时，返回的默认值
     * @return
     */
    public static String toNoneNullString(String str,String defaultValue){
        if(str == null){
            return defaultValue;
        }
        if(str.equalsIgnoreCase(NULL_STRING)){
            return defaultValue;
        }

        return str;
    }

    /**
     * 转为布尔类型
     *
     * @param obj
     * @return
     */
    public static boolean toBoolean(Object obj) {
        return toBoolean(obj, TRUE_FLAGS);
    }

    /**
     * 转为布尔类型
     *
     * @param obj       要转换类型的对象
     * @param trueValues 该对象和obj一样时，则返回true
     * @return
     */
    public static boolean toBoolean(Object obj, Object... trueValues) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Boolean) {
            return Boolean.TRUE.equals(obj);
        }
        if (obj instanceof CharSequence) {
            boolean bool;
            try {
                bool = Boolean.parseBoolean(obj.toString());
            } catch (Exception e) {
                bool = DataUtils.orEqualsIgnoreCase(obj.toString(),trueValues);
            }
            return bool;
        }

        if (trueValues != null && trueValues.length > 0) {
            return DataUtils.orEqualsIgnoreCase(obj.toString(),trueValues);
        }
        return false;
    }


    public static Integer toInteger(Object obj) {
        return toInteger(obj, null);
    }


    public static int toInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        String strValue = obj.toString();
        if (strValue.trim().length() == 0) {
            throw new  NumberFormatException("当前字符不是数字");
        }
        if (obj instanceof CharSequence) {

            try {
                return new BigDecimal(strValue).intValue();
            } catch (NumberFormatException e) {
                strValue = getNumberString(strValue);
                if (strValue.length() > 0) {
                    return new BigDecimal(strValue).intValue();
                } else {
                    if (Booleans.isBoolean(strValue)) {
                        return getNumberFromBoolean(strValue).intValue();
                    }

                }

            }
        }

        return Integer.parseInt(strValue);
    }

    /**
     * 根据布尔类型获取数字， true 返回1，false返回0
     * @param strValue
     * @return 根据布尔类型获取数字， true 返回1，false返回0
     */
    public static Number getNumberFromBoolean(Object strValue) {
        boolean bool = toBoolean(strValue,TRUE_FLAGS);
        if(bool){
            return 1;
        } else {
            return 0;
        }
    }

    @NotNull
    private static String getNumberString(String strValue) {
        char lastChar = strValue.charAt(strValue.length() - 1);
        if(NumberUtils.isNotNumber(lastChar)){
            strValue = strValue.substring(0, strValue.length() - 1);
        }else if(strValue.charAt(0) == '.'){
            strValue = "0" + strValue;
        }
        return strValue;
    }

    /**
     * 转为Integer
     *
     * @param obj
     * @param defaultValue 默认值
     * @return
     */
    public static Integer toInteger(Object obj, Integer defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        try {
            return toInt(obj);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }

    public static Double toDouble(Object obj) {
        return toDouble(obj, null);
    }


    public static double toDoubleValue(Object obj) {
        if (obj == null) {
            return 0D;
        }
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        String strValue = obj.toString();
        if (obj instanceof CharSequence) {
            try {
                return new BigDecimal(strValue).doubleValue();
            } catch (NumberFormatException e) {
                strValue = getNumberString(strValue);
                return new BigDecimal(strValue).intValue();
            }
        }

        return Double.parseDouble(strValue);
    }

    /**
     * 转为Double
     *
     * @param obj
     * @param defaultValue 默认值
     * @return
     */
    public static Double toDouble(Object obj, Double defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        try {
            return toDoubleValue(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    public static Long toLong(Object obj) {
        return toLong(obj, null);
    }


    public static long toLongValue(Object obj) {
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }

        String strValue = obj.toString();
        if (obj instanceof CharSequence) {
            try {
                return new BigDecimal(strValue).longValue();
            } catch (NumberFormatException e) {
                strValue = getNumberString(strValue);
                if (strValue.length() > 0) {
                    return new BigDecimal(strValue).longValue();
                } else {
                    if (Booleans.isBoolean(strValue)) {
                        return getNumberFromBoolean(strValue).longValue();
                    }
                }
            }
        }

        long number = Long.parseLong(strValue);
        return number;
    }

    /**
     * 转为Long
     *
     * @param obj
     * @param defaultValue 默认值
     * @return
     */
    public static Long toLong(Object obj, Long defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        try {
            return toLongValue(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 转为BigDecimal
     *
     * @param obj
     * @return
     */
    public static BigDecimal toBigDecimal(Object obj) {
        return toBigDecimal(obj,null);
    }



    /**
     * 转为BigDecimal
     *
     * @param obj
     * @param defaultValue 默认值
     * @return
     */
    public static BigDecimal toBigDecimal(Object obj, BigDecimal defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        String strValue = obj.toString();
        try {
            if (strValue.length() == 0) {
                return BigDecimal.ZERO;
            }
            return new BigDecimal(strValue);
        } catch (Exception e) {
            String numberString = getNumberString(strValue);
            if (numberString.length() > 0) {
                return new BigDecimal(numberString);
            } else {
                if (Booleans.isBoolean(numberString)) {
                    return new BigDecimal(getNumberFromBoolean(numberString).intValue());
                }

            }

        }
        return defaultValue;
    }

    /**
     * 转换为Date类型
     * @param obj
     * @return
     */
    public static Date toDate(Object obj){
        return toDate(obj,null);
    }

    public static Date toDate(Object obj,Date defaultValue){
        if (obj == null) {
            return defaultValue;
        }

        String strValue = obj.toString();
        int strLength = strValue.length();
        Date date = new Date();
        if(obj instanceof Number){
            int length = strValue.length();
            if(length == 10){
                date.setTime(toLong(strValue + "000"));
            }else {
                date.setTime(((Number) obj).longValue());
            }
            return date;
        }
        if(obj instanceof Date){
            return (Date) obj;
        }

        if(obj instanceof java.sql.Date){
            date.setTime(((java.sql.Date) obj).getTime());
            return date;
        }

        char[] chars = strValue.toCharArray();
        // 判断是否是时间戳
        if (strLength == 10 && NumberUtils.isNumber(chars)) {
            date.setTime(toLong(strValue + "000"));
            return date;
        }else if(strLength == 13 && NumberUtils.isNumber(chars)){
            date.setTime(toLong(strValue));
            return date;
        }
        // 计算pattern和日期对应配置的偏移量
        // 如：yyyy-MM-dd 对于2021-5-2 偏移量为-2， 对于2021-5-12 偏移量为-1 ，对于2021-05-12 偏移量为0
        int offset = 0;
        StringBuilder pattern = new StringBuilder();
        // 2021-11-12   2021/11/12
        if (/*(chars[4] == chars[7] && NumberUtils.isNotNumber(chars[4])) ||*/ (NumberUtils.isNotNumber(chars[4]) && NumberUtils.isNotNumber(chars[7]))) {
            String split1 = toString(chars[4]);
            String split2 = toString(chars[7]);
            pattern.append("yyyy").append(split1).append("MM").append(split2).append("dd");
        // 2021/5/2
        }else if ((NumberUtils.isNotNumber(chars[4]) && NumberUtils.isNotNumber(chars[6]))) {
            String split1 = toString(chars[4]);
            String split2 = toString(chars[6]);
            pattern.append("yyyy").append(split1).append("MM").append(split2).append("dd");

            if(strLength > 8){
                // 如果第8个字符是数字则说明日期为两位数，否则为一位数
                if(NumberUtils.isNumber(chars[8])){
                    offset = -1;
                }else {
                    offset = -2;
                }
            }

        // 5/11/2021
        } else if((NumberUtils.isNotNumber(chars[1]) && NumberUtils.isNotNumber(chars[4]))){
            String dateSplit1 = toString(chars[1]);
            String dateSplit2 = toString(chars[4]);
            pattern.append("dd").append(dateSplit1).append("MM").append(dateSplit2).append("yyyy");
            offset = -1;
            // 12-5-2021
        } else if((NumberUtils.isNotNumber(chars[2]) && NumberUtils.isNotNumber(chars[4]))){
            String dateSplit1 = toString(chars[2]);
            String dateSplit2 = toString(chars[4]);
            pattern.append("dd").append(dateSplit1).append("MM").append(dateSplit2).append("yyyy");
            offset = -1;
        } else if(/*(chars[3] == chars[6]  && NumberUtils.isNotNumber(chars[3])) ||*/ (NumberUtils.isNotNumber(chars[3]) && NumberUtils.isNotNumber(chars[6]))){
            String dateSplit1 = toString(chars[3]);
            String dateSplit2 = toString(chars[6]);
            pattern.append("dd").append(dateSplit1).append("MM").append(dateSplit2).append("yyyy");
        } else {
            pattern.append("yyyyMMdd");
        }

        int patternLength = pattern.length();
        if(patternLength >= strValue.trim().length()){
            return DateUtils.getDateByGiven(strValue, pattern.toString());
        }
        int nextPosition = patternLength + offset;
        if (chars[nextPosition] == ' ') {
            pattern.append(" ");
        }else if(chars[nextPosition] == 'T'){
            pattern.append("'T'");
            offset -= 2;
        }else if(NumberUtils.isNotNumber(chars[nextPosition])){
            pattern.append(toString(chars[nextPosition]));
        }

        patternLength = pattern.length();
        if(patternLength >= strValue.trim().length()){
            return DateUtils.getDateByGiven(strValue, pattern.toString());
        }

        nextPosition = patternLength + offset;
        if (strLength >= nextPosition + 6) {

            if (chars[nextPosition + 2] == chars[nextPosition + 5] && (!NumberUtils.isNumber(chars[nextPosition + 2]))) {
               pattern.append("HH").append(toString(chars[nextPosition + 2])).append("mm").append(toString(chars[nextPosition + 5])).append("ss");
            }else {
                pattern.append("HHmmss");
            }
        }else if(strLength >=nextPosition + 3){
            // 5:42 或12:5 或者5:3
            if(NumberUtils.isNotNumber(chars[nextPosition + 1]) || NumberUtils.isNotNumber(chars[nextPosition + 2])){
                pattern.append("HH:mm");
            }else{
                pattern.append("HHmm");
            }

        } else {
            pattern.append("HHmmss");
        }

        patternLength = pattern.length();
        if(patternLength >= strLength){
            return DateUtils.getDateByGiven(strValue, pattern.toString());
        }
        if (chars[patternLength] == '.') {
            pattern.append(".SSS");
        }else if(chars[patternLength] == '+' || chars[patternLength] == '-'){
            pattern.append("Z");
        }

        patternLength = pattern.length();
        if(patternLength >= strLength){
            return DateUtils.getDateByGiven(strValue, pattern.toString());
        }

        if(chars[patternLength] == '+' || chars[patternLength] == '-'){
            pattern.append("Z");
        }
        return DateUtils.getDateByGiven(strValue, pattern.toString());
    }

}
