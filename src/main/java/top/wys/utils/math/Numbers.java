/**
 * Created by 郑明亮 on 2022/2/7 0:27.
 */
package top.wys.utils.math;

import java.math.BigDecimal;

import top.wys.utils.NumberUtils;
import top.wys.utils.StringUtils;

/**
 * <p> 数字处理工具类</p>
 *
 * @author 郑明亮
 * @version 1.0.0
 * @time 2022/2/7 0:27
 */
public class Numbers {
    private static final Integer ZERO_INT =   Integer.valueOf(0);
    private static final Long ZERO_LONG =   Long.valueOf(0);

    public static Integer add(Integer num1,Integer... num2){
        if (num1 == null) {
            num1 = ZERO_INT;
        }
        if(num2 == null){
            return num1;
        }
        for (Integer num : num2) {
            num1 +=num;
        }
        return num1;
    }

    public static Long add(Long num1,Long... num2){
        if (num1 == null) {
            num1 = ZERO_LONG;
        }
        if(num2 == null){
            return num1;
        }
        for (Long num : num2) {
            num1 +=num;
        }
        return num1;
    }

    public static Integer subtract(Integer num1,Integer... num2){
        if (num1 == null) {
            num1 = ZERO_INT;
        }
        if(num2 == null){
            return num1;
        }
        for (Integer num : num2) {
            num1 -=num;
        }
        return num1;
    }

    public static Long subtract(Long num1,Long... num2){
        if (num1 == null) {
            num1 = ZERO_LONG;
        }
        if(num2 == null){
            return num1;
        }
        for (Long num : num2) {
            num1 -=num;
        }
        return num1;
    }

    public static BigDecimal add(BigDecimal num1, BigDecimal... num2){
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if(num2 == null){
            return num1;
        }
        for (BigDecimal num : num2) {
            if (num == null) {
                continue;
            }
            num1 = num1.add(num);
        }
        return num1;
    }


    public static Number subtract(BigDecimal num1, BigDecimal... num2){
        if (num1 == null) {
            num1 = Number.ZERO;
        }
        if(num2 == null){
            return (Number)num1;
        }
        for (BigDecimal num : num2) {
            if (num == null) {
                continue;
            }
            num1 = num1.subtract(num);
        }
        return Number.valueOf(num1);
    }


    /**
     * 转换为无符号数字
     * @param number
     * @return
     */
    public static String toUnsigned(String number){
        if(StringUtils.isNotEmpty(number)){
            char ch = number.charAt(0);
            if(ch == '+' || ch == '-'){
                return number.substring(1);
            }
        }
        return number;
    }

    /**
     * 转换为无符号数字
     * @param number
     * @return
     */
    public static String toUnsigned(java.lang.Number number){
        if(number != null){
           return toUnsigned(number.toString());
        }
        return "0";
    }

    /**
     * 从文本中找到数字并返回
     * @param text
     * @return
     */
    public static String getNumber(String text){
        return getNumber(text,false);
    }

    /**
     * 得到数字（整数、浮点数均可）
     *
     * @param text            文本
     * @param onlyFirstNumber 只获取第一个数字
     * @return {@link String}
     */
    private static String getNumber(String text,boolean onlyFirstNumber){
        if (text == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (NumberUtils.isNumber(ch)) {
                builder.append(ch);
            }else if(ch == '-'){
                // 处理负数
                if(i >= 0 && i <chars.length - 1){
                    if(NumberUtils.isNumber(chars[i + 1]) ){
                        builder.append(ch);
                    }
                }
            }else if(ch == '.'){
                // 处理小数
                if(i > 0 && i <chars.length - 1){
                    if(NumberUtils.isNumber(chars[i - 1]) && NumberUtils.isNumber(chars[i + 1]) ){
                        builder.append(ch);
                    }
                }
            }else {
                if (onlyFirstNumber) {
                    if(builder.length() > 0 && ch != ','){
                        break;
                    }
                }
            }
        }

        return builder.toString();
    }
    /**
     * 从文本中找到第一个数字（整数或浮点数）并返回
     * @param text
     * @return
     */
    public static String getFirstNumber(String text){

        return getNumber(text,true);
    }

}
