package top.wys.utils;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by 郑明亮 on 2021/12/5 19:18.
 */
public class NumberUtilsTest {

    @Test
    public void isPositiveInteger() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        String str = "1638702321200";
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 10000; i++) {
            NumberUtils.isNumber(str);
        }
        System.out.println(   NumberUtils.isNumber(str));
        stopwatch.stop();
        System.out.println(stopwatch.toString());
        stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 10000; i++) {
            NumberUtils.isPositiveInteger(str);

        }
        System.out.println(NumberUtils.isPositiveInteger(str));
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }
}