package top.wys.utils;

import org.junit.Test;
import top.wys.utils.convert.ConvertUtils;

import java.util.Date;


/**
 * Created by 郑明亮 on 2021/12/9 22:48.
 */
public class DateUtilsTest {

    @Test
    public void formatDateTime() {
        long timestamp = 1000000000;
        System.out.println(DateUtils.formatDateTime(timestamp));
    }

    @Test
    public void pastTies() {
        Date date = ConvertUtils.toDate("2021-11-21 21:29:19");
        Date endDate = ConvertUtils.toDate("2021-12-09 12:54:19");
        System.out.println(DateUtils.pastTimes(endDate.getTime() - date.getTime()));
    }
}