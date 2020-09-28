/*
 * Created by 郑明亮 on 2020/9/28 11:29.
 */

//

package top.wys.utils.http;

import com.sun.jndi.toolkit.url.Uri;

import java.util.List;

import okhttp3.Cookie;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2020/10/01 11:29
 * @email mpro@vip.qq.com
 */
public interface CookieStore {
    void add(Uri uri,Cookie cookie);
    List<Cookie> get(Uri uri);
    List<Cookie> get(String host);
    List<Cookie> getCookies();
}
