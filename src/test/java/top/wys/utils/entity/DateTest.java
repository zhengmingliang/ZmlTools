package top.wys.utils.entity;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import top.wys.utils.DateUtils;

import static org.junit.Assert.*;

/**
 * Created by 郑明亮 on 2022/1/29 18:50.
 */
public class DateTest {

    @Test
    public void toDateString() {
        Date date = new Date();
        System.out.println("date.toDateString() = " + date.toDateString());
        System.out.println("date.toDateString2() = " + toDateString2(date));
        Stopwatch stopWatch = Stopwatch.createStarted();
        for (int i = 0; i < 100000; i++) {
            date.toDateString();
        }
        System.out.println("1:"+stopWatch);
        stopWatch = Stopwatch.createStarted();
        for (int i = 0; i < 100000; i++) {
            toDateString2(date);
        }
        System.out.println("2:"+stopWatch);


    }

    @Test
    public void toDateTimeString() {
        Date date = new Date();
        System.out.println("date.toDateTimeString() = " + date.toDateTimeString());
        System.out.println("date.toDateTimeString2() = " + toDateTimeString2(date));
        Stopwatch stopWatch = Stopwatch.createStarted();
        for (int i = 0; i < 100000; i++) {
            date.toDateTimeString();
        }
        System.out.println("1:"+stopWatch);
        stopWatch = Stopwatch.createStarted();
        for (int i = 0; i < 100000; i++) {
            toDateTimeString2(date);
        }
        System.out.println("2:"+stopWatch);
    }

    public String toDateString2(Date date) {
        return DateUtils.getStringByPattern(date, DateUtils.DATE_PATTERN);
    }
    /**
     * 转换为 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     * @return
     */
    public String toDateTimeString2(Date date){
        return DateUtils.getStringByPattern(date,DateUtils.DATE_TIME_PATTERN);
    }

}