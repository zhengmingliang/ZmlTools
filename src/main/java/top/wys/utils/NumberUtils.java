package top.wys.utils;
/**
 * Created by 郑明亮 on 2021/12/4 16:49.
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <ol>
 *  2021/12/4 16:49 <br>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 */
public class NumberUtils {

    /**
     * 保留6位小数
     */
    public static final int SYS_DECIMAL_NUMBER_6 = 6;
    /**
     * 保留18位小数
     */
    public static final int SYS_DECIMAL_NUMBER_18 = 18;

    /**
     * 校验
     *
     * @param regex   表达式
     * @param orginal 参数
     * @return
     */
    private static boolean isMatch(String regex, String orginal) {
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }

    /**
     * 非负整数
     *
     * @param orginal
     * @return
     */
    public static boolean isPositiveInteger(String orginal) {
        return isMatch("^\\d+$", orginal);
    }

    /**
     * 非负浮点数
     *
     * @param orginal
     * @return
     */
    public static boolean isPositiveDecimal(String orginal) {
        return isMatch("\\d+\\.\\d+", orginal);
    }

    /**
     * 是否是数字
     * @param ch 字符
     * @return
     */
    public static boolean isNumber(char ch){
        return ch > '0' && ch < '9';
    }

    /**
     * 验证是否为货币金额：整数或者小数,可为0
     *
     * @param orginal
     * @return
     */
    public static boolean isCoinAmount(String orginal) {
        return isPositiveInteger(orginal) || isPositiveDecimal(orginal);
    }


    /**
     * 小数截取
     *
     * @param number 数字
     * @param point 保留几位小数
     * @return
     */
    public static String cutByPoint(String number, int point) {
        return cutByPoint(number,point,true);
    }
    /**
     * 小数截取
     *
     * @param number   数字
     * @param point 保留几位小数
     * @param removeEndZero 是否移除末尾的0
     * @return
     */
    public static String cutByPoint(String number, int point, boolean removeEndZero) {
        String result = new BigDecimal(number).setScale(point, BigDecimal.ROUND_DOWN).toPlainString();
        if(removeEndZero){
            result = removeEndZero(result);
        }
        return result;
    }


    /**
     * 保留小数（向上取值）
     * @param number
     * @param point 保留几位小数
     * @param removeEndZero 是否移除末尾的0
     * @return
     */
    public static String cutByPointUp(String number, int point, boolean removeEndZero) {
        String result = new BigDecimal(number).setScale(point, BigDecimal.ROUND_UP).toPlainString();
        if(removeEndZero){
           result = removeEndZero(result);
        }
        return result;
    }

    /**
     * 保留小数（向上取值）
     * @param number
     * @param point 保留几位小数
     * @return
     */
    public static String cutByPointUp(String number, int point) {

        return cutByPointUp(number, point, true);
    }


    /**
     * 去除末尾的0
     *
     * @param amount
     * @return
     */
    public static String removeEndZero(String amount) {
        if (!amount.contains(".")) {
            return amount;
        }
        return amount.replaceAll("0+?$", "").replaceAll("[.]$", "");
    }

    /**
     * 扩展
     *
     * @param number
     * @param point 保留几位小数
     * @param roundingMode 舍位模式 {@code BigDecimal.ROUND_HALF_DOWN、BigDecimal.ROUND_UP...}
     * @return
     */
    public static String cutByPoint(String number, int point, int roundingMode) {

        String resu = new BigDecimal(number).setScale(point, roundingMode).toPlainString();
        resu = removeEndZero(resu);
        return resu;
    }

    /**
     * 扣除手续费率（乘以费率然后做减法）eg: deductFee(100,0.038)  则相当于 100 - 100 * 0.038
     *
     * @param amount 金额（原价）
     * @param feeRate 费率
     * @return
     */
    public static String deductFee(String amount, int feeRate) {

        return deductFee(amount, feeRate,SYS_DECIMAL_NUMBER_6);
    }
    /**
     * 扣除手续费率（乘以费率然后做减法）eg: deductFee(100,0.038)  则相当于 100 - 100 * 0.038
     *
     * @param amount 金额（原价）
     * @param feeRate 费率
     * @return
     */
    public static String deductFee(String amount, int feeRate,int point) {
        BigDecimal decimal = new BigDecimal(amount);
        //
        BigDecimal result =
                decimal.subtract(decimal.multiply(new BigDecimal(feeRate)));
        return cutByPoint(result.toPlainString(), point);
    }



    /**
     * 两个金额相减
     *
     * @param num1
     * @param num2
     * @param point 保留几位小数
     * @return num1 - num2
     */
    public static String subtract(String num1, String num2, int point) {
        BigDecimal subtract1Big = new BigDecimal(num1);
        BigDecimal subtract2Big = new BigDecimal(num2);
        String result = cutByPoint(subtract1Big.subtract(subtract2Big).toPlainString(), point);
        return result;
    }

    /**
     * 添加
     *
     * @param add1
     * @param add2
     * @param point 保留几位小数
     * @return
     */
    public static String add(String add1, String add2, int point) {
        BigDecimal add1Big = new BigDecimal(add1);
        BigDecimal add2Big = new BigDecimal(add2);
        String result = cutByPoint(add1Big.add(add2Big).toPlainString(), point);
        return result;
    }

    /**
     * 多个数相加
     * @param point 保留的小数点位数
     * @param nums 多个要相加的数组成的数组
     * @return
     */
    public static String add(int point, String... nums) {
        String result;
        if (nums != null && nums.length > 0) {
            BigDecimal addDecimal = new BigDecimal(0);
            for (String num : nums) {
                if (num == null) {
                    num = "0";
                }
                BigDecimal decimal = new BigDecimal(num);
                addDecimal = addDecimal.add(decimal);
            }

            result = cutByPoint(addDecimal.toPlainString(), point);
        } else {
            result = "0";
        }

        return result;
    }

    /**
     * 乘法
     *
     * @param num1
     * @param num2
     * @param point 保留几位小数
     * @return
     */
    public static String multiply(String num1, String num2, int point) {
        BigDecimal multiply1Big = new BigDecimal(num1);
        BigDecimal multiply2Big = new BigDecimal(num2);
        String result = cutByPoint(multiply1Big.multiply(multiply2Big).toPlainString(), point);
        return result;
    }


    /**
     * 除法（最后一位四舍五入）
     *
     * @param num1
     * @param num2
     * @param point 保留几位小数
     * @return
     */
    public static String divide(String num1, String num2, int point) {
        BigDecimal divide1Big = new BigDecimal(num1);
        BigDecimal divide2Big = new BigDecimal(num2);
        String result =
                cutByPoint(divide1Big.divide(divide2Big, SYS_DECIMAL_NUMBER_18, BigDecimal.ROUND_HALF_UP)
                        .toPlainString(), point);
        return result;
    }

    /**
     * 除法（最后一位向上取整）
     * @param num1
     * @param num2
     * @param point 保留几位小数
     * @return
     */
    public static String divideUp(String num1, String num2, int point) {
        BigDecimal divide1Big = new BigDecimal(num1);
        BigDecimal divide2Big = new BigDecimal(num2);
        String result =
                cutByPointUp(divide1Big.divide(divide2Big, SYS_DECIMAL_NUMBER_18, BigDecimal.ROUND_UP)
                        .toPlainString(), point);
        return result;
    }

    /**
     * 16进制转成10进制
     *
     * @param hexNumber 16进制的数字
     * @return
     */
    public static String hexToDecimal(String hexNumber) {
        return new BigInteger(hexNumber, 16).toString();
    }

    /**
     * 任意进制转换
     *
     * @param number       数字
     * @param sourceDigits 源数字进制
     * @param targetDigits 目标数字进制
     * @return
     */
    public static String hexTransfer(String number, int sourceDigits, int targetDigits) {
        String[] ss = null;
        String returnStr = "";
        if (number.indexOf(".") != -1) {
            ss = number.split("\\.");
            String s1 = new BigInteger(ss[0], sourceDigits).toString(targetDigits);
            String s2 = new BigInteger(ss[1], sourceDigits).toString(targetDigits);
            returnStr = s1 + "." + s2;
        } else {
            returnStr = new BigInteger(number, sourceDigits).toString(targetDigits);
        }
        return returnStr;
    }

    /**
     * 比较两个数大小
     *
     * @param number1
     * @param number2
     * @return
     */
    public static int compare(String number1, String number2) {
        BigDecimal decimal1 = new BigDecimal(number1);
        BigDecimal decimal2 = new BigDecimal(number2);
        return decimal1.compareTo(decimal2);
    }

    /**
     * 比较两个数绝对值的大小
     * @param number1
     * @param number2
     * @return
     * @author vefuwell
     */
    public static int compareAbs(String number1, String number2) {
        BigDecimal decimal1 = new BigDecimal(number1).abs();
        BigDecimal decimal2 = new BigDecimal(number2).abs();
        return decimal1.compareTo(decimal2);
    }

    /**
     *  给一个数增加一个step点<br/>
     * <li>number = 23.112383,step=1,return 23.112384</li>
     * <li>number = 24,step=1,return <em>25</em></li>
     * <li>number = 23.112383,step=-3,return 23.112380</li>
     * @param number 数字
     * @param step 增加步长，可为负数
     * @return
     */
    public static String increaseStep(String number,int step) {
        if (StringUtils.isEmpty(number)) {
            return null;
        }
        int index = number.indexOf(".");
        int length = number.length();
        BigDecimal decimal;
        if(index !=-1){
            int offset = length - index - 1;
            decimal = new BigDecimal(number).movePointRight(offset).add(new BigDecimal(step)).movePointLeft(offset);
        }else{
            decimal = new BigDecimal(number).add(new BigDecimal(step));
        }

        return decimal.toPlainString();
    }

    /**
     * 求平均数
     * @param number1
     * @param number2
     * @param point  保留小数
     * @return
     */
    public static String average(String number1, String number2, int point) {
        if (StringUtils.isEmpty(number1) || StringUtils.isEmpty(number2)) {
            return null;
        }
        String result = new BigDecimal(number1).add(new BigDecimal(number2)).divide(new BigDecimal(2)).toPlainString();
        return cutByPoint(result, point);
    }



    /**
     * 获取不大于的随机数
     * @param max
     * @return
     */
    public static double getRandomMax(double max){
        double random = Math.random();
        if(random > max){
            random = Math.abs(random*(1-random));
            if(random > max){
                return getRandomMax(max);
            }
        }
        return random;
    }

    /**
     * 获取精度（超过18位小数后的精度会被截断）
     * @param price 金额数字
     * @return
     */
    public static int getAccuracy(String price) {
        price = cutByPoint(price, SYS_DECIMAL_NUMBER_18);
        if (!price.contains(".")) {
            return 0;
        }
        return price.split("\\.")[1].length();
    }

    /**
     * 获取精度（精度不会被截断）
     * @param price 金额数字
     * @return
     */
    public static int getAccuracyAll(String price) {
        if (!price.contains(".")) {
            return 0;
        }
        return price.split("\\.")[1].length();
    }



}
