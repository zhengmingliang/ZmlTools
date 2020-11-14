/*
 * Created by 郑明亮 on 2020/9/28 10:58.
 */

//

package top.wys.utils.http;

import com.google.common.collect.Lists;

import org.jetbrains.annotations.NotNull;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2020/10/01 10:58
 * @email mpro@vip.qq.com
 */
public class CookieJarImpl implements CookieJar {

    private final CookieManager cookieManager = new CookieManager();

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        List<HttpCookie> httpCookies = cookieManager.getCookieStore().get(httpUrl.uri());
        List<Cookie> cookies = convertToCookies(httpCookies);
        return cookies;
    }

    @NotNull
    public List<Cookie> convertToCookies(List<HttpCookie> httpCookies) {
        List<Cookie> cookies = Lists.newArrayList();
        for (HttpCookie httpCookie : httpCookies) {
            Cookie.Builder builder = new Cookie.Builder().name(httpCookie.getName())
                    .value(httpCookie.getValue())
                    .domain(httpCookie.getDomain())
                    .path(httpCookie.getPath())
                    .expiresAt(httpCookie.getMaxAge());
            if (httpCookie.getSecure()) {
                builder.secure();
            }
            if (httpCookie.isHttpOnly()) {
                builder.httpOnly();
            }
            cookies.add(builder.build());
        }
        return cookies;
    }
    @NotNull
    public List<HttpCookie> convertToHttpCookies(List<Cookie> list) {
        List<HttpCookie> cookies = Lists.newArrayList();
        for (Cookie cookie : list) {
            HttpCookie httpCookie = convertCookieToHttpCookie(cookie);;
            cookies.add(httpCookie);
        }
        return cookies;
    }

    public HttpCookie convertCookieToHttpCookie(Cookie cookie) {
        HttpCookie httpCookie = new HttpCookie(cookie.name(), cookie.value());
        httpCookie.setDomain(cookie.domain());
        httpCookie.setPath(cookie.path());
        httpCookie.setSecure(cookie.secure());
        httpCookie.setHttpOnly(cookie.httpOnly());
        httpCookie.setMaxAge(cookie.expiresAt());
        return httpCookie;
    }

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        for (Cookie cookie : list) {
            cookieManager.getCookieStore().add(httpUrl.uri(),convertCookieToHttpCookie(cookie));
        }
    }
}
