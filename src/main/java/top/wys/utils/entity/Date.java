/**
 * Created by 郑明亮 on 2022/1/29 17:54.
 */
package top.wys.utils.entity;

import sun.util.calendar.CalendarUtils;
import top.wys.utils.DateUtils;

import java.util.Calendar;

/**
 * <p> 自定义日期增强类</p>
 *
 * @author 郑明亮
 * @since 1.3.4
 * @time 2022/1/29 17:54
 */
public class Date extends java.util.Date {
    Calendar calendar;
    public Date() {
        this(System.currentTimeMillis());
    }
    public Date(long timestamp) {
        super(timestamp);
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
    }

    /**
     * 转换为 指定模式的时间字符串
     * @param pattern
     * @return
     */
    public String format(String pattern){
        return DateUtils.getStringByPattern(this, pattern);
    }

    /**
     * @return 转换为 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     */
    public String format(){
        return DateUtils.getStringByPattern(this, DateUtils.DATE_TIME_PATTERN);
    }

    /**
     * 转换为 yyyy-MM-dd格式的日期字符串
     * @return
     */
    public String toDateString(){
        StringBuilder builder = new StringBuilder(10);
        builder.append(calendar.get(Calendar.YEAR)).append('-');
        CalendarUtils.sprintf0d(builder,calendar.get(Calendar.MONTH) + 1,2).append('-');
        CalendarUtils.sprintf0d(builder,calendar.get(Calendar.DAY_OF_MONTH),2);
       return builder.toString();
    }

    /**
     * 转换为 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     * @return
     */
    public String toDateTimeString(){
        StringBuilder builder = new StringBuilder(19);
        builder.append(calendar.get(Calendar.YEAR)).append('-');
        CalendarUtils.sprintf0d(builder,calendar.get(Calendar.MONTH) + 1,2).append('-');
        CalendarUtils.sprintf0d(builder,calendar.get(Calendar.DAY_OF_MONTH),2).append(' ');
        CalendarUtils.sprintf0d(builder,calendar.get(Calendar.HOUR_OF_DAY),2).append(':');
        CalendarUtils.sprintf0d(builder,calendar.get(Calendar.MINUTE),2).append(':');
        CalendarUtils.sprintf0d(builder,calendar.get(Calendar.SECOND),2);
        return builder.toString();
    }

}
