package top.wys.utils;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 郑明亮 on 2020/9/9 12:55.
 */

//

public class StringUtilsTest {

    @Test
    public void unicodeToString() {
        String str = StringUtils.unicodeToString("我是\\u8c03\\u5ea6\\u4efb\\u52a1\\u8ba1\\u5212");

        System.out.println(str);
    }

    @Test
    public void replace() {

        String inString = "select * from table where name = ? and age = ?";
        System.out.println(inString.replaceAll("\\?", "%s"));
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 1000000; i++) {
            StringUtils.replace(inString, '?', "%s");
        }
//        stopwatch.stop();
        System.out.println("重写："+stopwatch.toString());

        stopwatch.reset();
        stopwatch.start();
        for (int i = 0; i < 1000000; i++) {
            inString.replaceAll("\\?","%s");
        }
//        stopwatch.stop();
        System.out.println("replaceAll:"+stopwatch);

    }
    @Test
    public void replace2() {

        String inString = "select * from table where name = ? and age = ?";
        System.out.println(inString.replaceAll("\\?", "%s"));
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 1000000; i++) {
            StringUtils.replace(inString, "?", "%s");
        }
//        stopwatch.stop();
        System.out.println("重写："+stopwatch.toString());

        stopwatch.reset();
        stopwatch.start();
        for (int i = 0; i < 1000000; i++) {
            StringUtils.replace(inString, '?', "%s");
        }
//        stopwatch.stop();
        System.out.println("replaceAll:"+stopwatch);

    }
}