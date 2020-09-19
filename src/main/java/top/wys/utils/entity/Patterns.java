/*
 * Created by 郑明亮 on 2020/9/19 15:17.
 */

//

package top.wys.utils.entity;

import java.util.regex.Pattern;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2020/9/19 15:17
 * @email mpro@vip.qq.com
 */
public class Patterns {
    /**
     * http url
     */
    public static final Pattern HTTP_URL = Pattern.compile("(https://|http://)?([\\w-]+\\.)+[\\w-]+(:\\d+)*(/[\\w- ./?%&=]*)?");
    /**
     * email 校验
     */
    public static final Pattern EMAIL = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    /**
     * 引用于Android.util.Patterns
     * email 校验
     */
    public static final Pattern EMAIL_ADDRESS = Pattern
            .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");


    /**
     * @author 郑明亮
     * @time 2017年3月13日 下午5:50:29
     * @description <p>匹配中文 <br>
     */
    public static final Pattern PATTERN_IS_CHICHNESS = Pattern
            .compile("^[\\u4e00-\\u9fa5]{0,}$");

    /**
     * @author 郑明亮
     * @time 2017年3月13日 下午6:58:08
     * @description <p> 15位身份证号验证<br>
     */
    public static final Pattern PATTERN_IS_ID_CARD_NUMBER_15 = Pattern
            .compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

    /**
     * @author 郑明亮
     * @time 2017年3月13日 下午6:58:13
     * @description <p>18位身份证号验证 <br>
     */
    public static final Pattern PATTERN_IS_ID_CARD_NUMBER_18 = Pattern
            .compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");


    public static final Pattern ARRAY = Pattern.compile("^.*\\[[.*]+\\]$");

}
