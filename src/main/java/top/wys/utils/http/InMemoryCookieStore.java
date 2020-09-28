/*
 * Created by 郑明亮 on 2020/9/28 11:38.
 */

//

package top.wys.utils.http;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.sun.jndi.toolkit.url.Uri;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2020/10/01 11:38
 * @email mpro@vip.qq.com
 */
public class InMemoryCookieStore implements CookieStore {

    com.google.common.cache.Cache<String,List<Cookie>> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(120, TimeUnit.MINUTES)
            .maximumSize(10000)
            .build();
    @Override
    public void add(Uri uri, Cookie cookie) {
        String cookieToken = getCookieToken(cookie);
        List<Cookie> cookies = cache.getIfPresent(cookieToken);
        if (cookies == null) {
            cookies = Lists.newArrayList();
            cache.put(cookieToken,cookies);
        }
        if (!cookies.contains(cookie)) {
            cookies.add(cookie);
        }
    }

    public String getCookieToken(Cookie cookie){
        return cookie.name()+"@"+cookie.domain();
    }

    @Override
    public List<Cookie> get(Uri uri) {
        return null;
    }

    @Override
    public List<Cookie> get(String host) {
        return cache.getIfPresent(host);
    }

    @Override
    public List<Cookie> getCookies() {
        return null;
    }
}
