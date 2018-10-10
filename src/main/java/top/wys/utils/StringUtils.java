package top.wys.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 郑明亮
 * @time 2017年2月1日 下午8:48:58
 * @description <p>字符串相关工具类  </p>
 */
public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("Can not be instantiated...");
    }
 
/**
 * @author 郑明亮
 * @time 2017年1月23日 上午9:58:07
 * @description <p> 将Unicode编码的文本进行解码</p>
 * @param str Unicode编码文本
 * @return 解码后的文本
 */
public static String unicodeToString(String str) {
  
    Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");    
    Matcher matcher = pattern.matcher(str);
    char ch;
    while (matcher.find()) {
        ch = (char) Integer.parseInt(matcher.group(2), 16);
        str = str.replace(matcher.group(1), ch + "");    
    }
    return str;
}
 
/**
 * @author 郑明亮
 * @time 2017年1月23日 上午9:57:20
 * @description <p>将中文转换为Unicode编码   </p>
 * @param s 想要进行编码的文本
 * @return Unicode编码 文本
 */
public static String getUnicode(String s) {
    try {
        StringBuffer out = new StringBuffer("");
        byte[] bytes = s.getBytes("unicode");
        for (int i = 0; i < bytes.length - 1; i += 2) {
            out.append("\\u");
            String str = Integer.toHexString(bytes[i + 1] & 0xff);
            for (int j = str.length(); j < 2; j++) {
                out.append("0");
            }
            String str1 = Integer.toHexString(bytes[i] & 0xff);
            out.append(str1);
            out.append(str);
              
        }
        return out.toString();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        return null;
    }
}

/**
 * 将 inStr 转为 UTF-8 的编码形式
 * 
 * @param inStr 输入字符串
 * @return UTF - 8 的编码形式的字符串
 * @throws UnsupportedEncodingException
 */
public static String ISO2UTF(String inStr) throws UnsupportedEncodingException {
    String outStr = "";
    if (inStr != null) {
        outStr = new String(inStr.getBytes("iso-8859-1"), "UTF-8");
    }
    return outStr;
}
 
    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空 {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }
    
    /**
     * @author 郑明亮
     * @time 2017年2月6日 上午11:13:48
     * @description <p> 判断字符串是否不为null或长度大于0  </p>
     * @param s 待校验字符串
     * @return {@code false}: 空 {@code true}: 不为空
     */
    public static boolean isNotEmpty(CharSequence s) {
        return !isEmpty(s);
    }
 
    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格 {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }
 
    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }
 
    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
    	
        return (a != null && b != null && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length()));
    }
 
    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }
 
    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }
 
    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }
 
    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }
 
    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }
 
    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }
 
    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }
     
    /**
     * @author 郑明亮
     * @time 2017年1月12日 下午1:16:39
     * @description <p>去掉浮点类型的小数点，并进行四舍五入   </p>
     *              <p>应用场景：将浮点型转换为整型数据，并保留1位小数</p>
     * @param amountString 浮点型金额字符串
     * @return 整型数字符串
     */
    public static String removeAmountPoint(String amountString){
        String noPointString = "";
        if (amountString == null) {
            return noPointString;
        }else{
            Integer amount;
            if (amountString.indexOf("." )!= -1) {
                String pointNum =  amountString.substring(amountString.indexOf("." ));
                amount  = Integer.parseInt(amountString.replace(pointNum, ""));
                if (pointNum.length()>2) {
                    int num = Integer.parseInt(pointNum.substring(1, 2));
                    if (num >=5) {
                        amount ++;
                         
                    }
                }
                noPointString = ""+amount;
            }else {
                return amountString;
            }
            return noPointString;
        }
             
    }
/**
     * @author 郑明亮
     * @time 2017年1月13日 下午4:18:14
     * @param amount 金额
     * @param pattern 将数字转换为指定格式
     * <p>如 amount： 10000000  ，当传入金额为null时，默认为0 </p>
     * <p>如 pattern：  \u00A5,###.00 ，当pattern为null时，使用默认pattern ,即"\u00A5,##0.00" </p>
     * <p>如 return： ¥10,000,000   </p>
     * @return 格式化后的数字字符串
     * @throws ParseException
     */
    public static String formateAmount(Object amount,String pattern) throws ParseException{
        if (amount == null) {//当传入金额为null时，默认为0
            return formateAmount(0,pattern);
        }
        if (pattern == null) {//当pattern为null时，使用默认pattern
            pattern = "\u00A5,##0.00";
        }
        if (amount instanceof String) {
            return formateAmount(Double.parseDouble(amount.toString()),pattern);
        }
        DecimalFormat format = new DecimalFormat(pattern);
        String number = format.format(amount);
        return number;
    }
}