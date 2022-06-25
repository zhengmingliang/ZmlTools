/**
 * Created by 郑明亮 on 2022/4/23 17:09.
 */
package top.wys.utils.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * <p> Cookie工具类</p>
 *
 * @author 郑明亮
 * @version 1.0.0
 * @time 2022/4/23 17:09
 */
public class Cookies {

    /**
     * 得到cookie中的某个key的值
     *
     * @param request 请求
     * @param key     cookie中的key名称
     * @return {@link String}
     */
    public static String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
