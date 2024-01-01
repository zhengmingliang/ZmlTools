package top.wys.utils;
/**
 * Created by 郑明亮 on 2021/12/4 16:49.
 */

import top.wys.utils.convert.ConvertUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
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

    private static final Pattern AMOUNT_PATTERN =
            Pattern.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d)$"); // 不考虑分隔符的正确性
    private static final char[] RMB_NUMS = "零壹贰叁肆伍陆柒捌玖".toCharArray();
    private static final String[] UNITS = {"元", "角", "分", "整"};
    private static final String[] U1 = {"", "拾", "佰", "仟"};
    private static final String[] U2 = {"", "万", "亿"};

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
     * @return 是数字返回true，否则返回false
     */
    public static boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }

    /**
     * 是否是数字
     * @param str 字符
     * @return 是数字返回true，否则返回false
     */
    public static boolean isNumber(String str){
       return isNumber(str.toCharArray());
    }

    /**
     * 是否是数字
     * @param chars 字符
     * @return 是数字返回true，否则返回false
     */
    public static boolean isNumber(char[] chars){
        for (char ch : chars) {
            if(isNotNumber(ch)){
                return false;
            }
        }
        return true;
    }

    /**
     * 是否不是数字
     * @param ch 字符
     * @return 不是数字返回true，否则返回false
     */
    public static boolean isNotNumber(char ch){
        return !isNumber(ch);
    }
    /**
     * 是否不是数字
     * @param str 字符
     * @return 不是数字返回true，否则返回false
     */
    public static boolean isNotNumber(String str){
        return !isNumber(str);
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
        num1 = ConvertUtils.toNoneEmptyString(num1,"0");
        num2 = ConvertUtils.toNoneEmptyString(num2,"0");

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
        add1 = ConvertUtils.toNoneEmptyString(add1,"0");
        add2 = ConvertUtils.toNoneEmptyString(add2,"0");
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
                num = ConvertUtils.toNoneEmptyString(num,"0");
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
     * 数字比较 {@code number1} 大于 {@code number2} 返回1，小于返回 -1 ，等于返回 0，其中任一对象为null则返回 -1
     * @param number1
     * @param number2
     * @return
     */
    public static int compare(Number number1, Number number2) {
        if(number1 == null || number2 == null){
            return -1;
        }

        if (number1 instanceof Long) {
            return Long.compare((long)number1, number2.longValue());
        } else if (number1 instanceof Integer) {
            return Integer.compare((int)number1, number2.intValue());
        } else if (number1 instanceof Double) {
            return Double.compare((double)number1, number2.doubleValue());
        } else if (number1 instanceof Float) {
            return Float.compare((float)number1, number2.floatValue());
        } else if (number1 instanceof BigDecimal) {
            return ((BigDecimal) number1).compareTo(ConvertUtils.toBigDecimal(number2) );
        } else {
            return compare(number1.toString(),number2.toString());
        }
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

    /**
     * 需要将数字格式化为1,000,000.00的形式（可调用）
     * 将金额（整数部分等于或少于12位，小数部分2位）转换为中文大写形式.
     *
     * @param amount 金额数字
     * @since 1.3.7
     * @return 中文大写
     * @throws IllegalArgumentException
     */
    public static String amount2rmb(String amount) throws IllegalArgumentException {


        // 如果有带逗号的分隔符则去掉分隔符。否则返回原值
        amount = amount.contains(",") ? amount.replace(",", "") : amount;
        //判断是否输入小数点，如果没有输入小数点，则进行格式化
        amount = amount.contains(".") ? amount : (amount + ".00");
        // 如果是小数点以后为1位，则在末尾添加个0，凑齐两位小数
        // String temp = amount.substring(amount.indexOf(".")+1, amount.length())
        // amount = temp.length() == 1?amount+"0":amount;

        // 验证金额正确性
        if (amount.equals("0.00")) {
            throw new IllegalArgumentException("金额不能为零.");
        }
        Matcher matcher = AMOUNT_PATTERN.matcher(amount);
        if (!matcher.find()) {
            throw new IllegalArgumentException("输入金额有误.");
        }

        String integer = matcher.group(1); // 整数部分
        String fraction = matcher.group(2); // 小数部分

        String result = "";
        if (!integer.equals("0")) {
            result += integer2rmb(integer) + UNITS[0]; // 整数部分
        }
        if (fraction.equals("00")) {
            result += UNITS[3]; // 添加[整]
        } else if (fraction.startsWith("0") && integer.equals("0")) {
            result += fraction2rmb(fraction).substring(1); // 去掉分前面的[零]
        } else {
            result += fraction2rmb(fraction); // 小数部分
        }

        return result;
    }

    // 将金额小数部分转换为中文大写
    private static String fraction2rmb(String fraction) {
        char jiao = fraction.charAt(0); // 角
        char fen = fraction.charAt(1); // 分
        return (RMB_NUMS[jiao - '0'] + (jiao > '0' ? UNITS[1] : ""))
                + (fen > '0' ? RMB_NUMS[fen - '0'] + UNITS[2] : "");
    }

    // 将金额整数部分转换为中文大写
    private static String integer2rmb(String integer) {
        StringBuilder buffer = new StringBuilder();
        // 从个位数开始转换
        int i, j;
        for (i = integer.length() - 1, j = 0; i >= 0; i--, j++) {
            char n = integer.charAt(i);
            if (n == '0') {
                // 当n是0且n的右边一位不是0时，插入[零]
                if (i < integer.length() - 1 && integer.charAt(i + 1) != '0') {
                    buffer.append(RMB_NUMS[0]);
                }
                // 插入[万]或者[亿]
                if (j % 4 == 0) {
                    if (i > 0 && integer.charAt(i - 1) != '0'
                            || i > 1 && integer.charAt(i - 2) != '0'
                            || i > 2 && integer.charAt(i - 3) != '0') {
                        buffer.append(U2[j / 4]);
                    }
                }
            } else {
                if (j % 4 == 0) {
                    buffer.append(U2[j / 4]);     // 插入[万]或者[亿]
                }
                buffer.append(U1[j % 4]);         // 插入[拾]、[佰]或[仟]
                buffer.append(RMB_NUMS[n - '0']); // 插入数字
            }
        }
        return buffer.reverse().toString();
    }


    /**
     * 将数据格式化为 ###,###.00
     *
     * @param decimal
     * @return
     */
    public static String formatNumber(BigDecimal decimal) {
        String num = null;
        DecimalFormat formate = new DecimalFormat("###,###.00");
        num = formate.format(decimal);


        return num;
    }

    /**
     * @param num      要进行四舍五入的数字
     * @param pointNum 要保留几位小数
     * @return
     * @author 郑明亮
     * @time 2017年4月3日12:06:45
     * @description <p>四舍五入<br>
     */
    public static String getRoundNum(double num, Integer pointNum) {
        StringBuilder point = new StringBuilder();
        DecimalFormat format = new DecimalFormat("#." + "00");
        if (StringUtils.isEmpty(point)) {
            for (int i = 0; i < pointNum; i++) {
                point.append("0") ;
            }
            format = new DecimalFormat("#." + point);
        }

        return format.format(num);
    }

    /**
     * 四舍五入，默认保留两位小数
     *
     * @param num 数字
     * @return 处理后的数字
     */
    public static String getRoundNum(double num) {
        return getRoundNum(num, 2);
    }

}
