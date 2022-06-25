/**
 * Created by 郑明亮 on 2022/1/29 17:54.
 */
package top.wys.utils.entity;

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
        sprintf0d(builder,calendar.get(Calendar.MONTH) + 1,2).append('-');
        sprintf0d(builder,calendar.get(Calendar.DAY_OF_MONTH),2);
       return builder.toString();
    }

    /**
     * 转换为 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     * @return
     */
    public String toDateTimeString(){
        StringBuilder builder = new StringBuilder(19);
        builder.append(calendar.get(Calendar.YEAR)).append('-');
        sprintf0d(builder,calendar.get(Calendar.MONTH) + 1,2).append('-');
        sprintf0d(builder,calendar.get(Calendar.DAY_OF_MONTH),2).append(' ');
        sprintf0d(builder,calendar.get(Calendar.HOUR_OF_DAY),2).append(':');
        sprintf0d(builder,calendar.get(Calendar.MINUTE),2).append(':');
        sprintf0d(builder,calendar.get(Calendar.SECOND),2);
        return builder.toString();
    }

    private static final StringBuilder sprintf0d(StringBuilder var0, int var1, int var2) {
        long var3 = (long)var1;
        if (var3 < 0L) {
            var0.append('-');
            var3 = -var3;
            --var2;
        }

        int var5 = 10;

        int var6;
        for(var6 = 2; var6 < var2; ++var6) {
            var5 *= 10;
        }

        for(var6 = 1; var6 < var2 && var3 < (long)var5; ++var6) {
            var0.append('0');
            var5 /= 10;
        }

        var0.append(var3);
        return var0;
    }

}
