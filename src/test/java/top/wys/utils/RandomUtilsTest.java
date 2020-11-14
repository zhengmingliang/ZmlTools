package top.wys.utils;

import com.google.common.collect.Maps;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by 郑明亮 on 2018/10/14 16:49.
 */
public class RandomUtilsTest {

//    @Test
    public void getRandomIp() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getRandomIp());
        }
    }

//    @Test
    public void getRandomEmail() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getRandomEmail(5,10));
        }
    }

//    @Test
    public void getNum() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getNum(5,10));
        }
    }

//    @Test
    public void getRandomNumCode() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getRandomNumCode(5));
        }
    }

//    @Test
    public void getRandomCode() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getRandomCode(5));
        }
    }

//    @Test
    public void getDoubleNum() {
        long start = System.currentTimeMillis();
        System.out.println("------start--------"+DateUtils.getStringByPattern(new Date(start),"yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < 10000; i++) {

            System.out.println(RandomUtils.getDoubleNum(9, 10));
        }
        long end = System.currentTimeMillis();
        System.out.println("------end--------"+DateUtils.getStringByPattern(new Date(end),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("耗时："+(end - start)+"ms");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("------start--------"+DateUtils.getStringByPattern(new Date(start),"yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < 1000000; i++) {
            RandomUtils.getDoubleNum(9, 10);
//            System.out.println();
//            System.out.println();
        }
        long end = System.currentTimeMillis();
        System.out.println("------end--------"+DateUtils.getStringByPattern(new Date(end),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("耗时："+(end - start)+"ms");
    }
//    @Test
    public void getDoubleNum2() {
        long start = System.currentTimeMillis();
        System.out.println("------start--------"+DateUtils.getStringByPattern(new Date(start),"yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < 10000; i++) {

            System.out.println(RandomUtils.getDoubleNum(9, 10));
        }
        long end = System.currentTimeMillis();
        System.out.println("------end--------"+DateUtils.getStringByPattern(new Date(end),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("耗时："+(end - start)+"ms");
    }

    @Test
    public void getRandomUserAgent() {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtils.getRandomUserAgent());
        }
    }

    @Test
    public void cookieJarTest() throws IOException {
        Map<String,Object> params = Maps.newHashMap();
        params.put("name","zhangsan");
        String testResult = HttpUtils.get("http://localhost:8084/conf/test",params);
        System.out.println("testResult = " + testResult);
        String testCookieResult = HttpUtils.get("http://localhost:8084/conf/testCookie");
        System.out.println("testCookieResult = " + testCookieResult);
    }
}