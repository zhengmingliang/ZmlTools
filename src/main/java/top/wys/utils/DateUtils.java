package top.wys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author 郑明亮
 * @Time 2017年2月1日 下午6:35:24
 * @Description <p>
 *              日期工具类，部分方法来自金融项目
 *              </p>
 */
public class DateUtils {

	private DateUtils() {
		throw new UnsupportedOperationException("不能对我进行实例化哦");
	}

	/**
	 * 将当前日期转换为int类型返回; 如20170201
	 * 
	 * @param pattern
	 * @return
	 */
	public static int getIntNowDate(String pattern) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String nowDateString = format.format(date);
		System.out.println(nowDateString);
		if (nowDateString != null) {
			return Integer.parseInt(nowDateString);
		}
		return -1;
	}

	/**
	 * @author 郑明亮
	 * @Time 2017年2月1日 下午8:13:59
	 * @Description <p>
	 *              当前时间转换成特定形式Date
	 *              </p>
	 *              <p>
	 *              因为有时在存入到数据库中的date类型带有微秒值，在再次从数据库中读取为String类型时，仍会带有微秒值，
	 *              所以为保险起见，经格式化后，再保存就不会出现微秒值
	 *              </p>
	 * @param datePattern
	 * @return
	 */
	public static Date getFormatNowDate(String datePattern) {

		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		String dateString = formatter.format(currentTime);
		Date d = null;
		try {
			d = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 当前时间转换成特定形式Date
	 * 
	 * @param datePattern
	 * @return
	 */
	public static String getNowDateStrByPattern(String datePattern) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取当前年
	 * 
	 * @return
	 */
	public static Integer getCurrentYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取当前天，当月的当天
	 * 
	 * @return
	 */
	public static Integer getCurrentDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前小时,24时制
	 * 
	 * @return
	 */
	public static Integer getCurrentHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到下一个日期
	 * 
	 * @param date
	 * @param step
	 * @return
	 */
	public static Date getNextDate(Date date, int step) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, step);
		return c.getTime();
	}

	/**
	 * 根据日期和格式得到Date形式日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date getDateByPattern(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(date);
		Date d = null;
		try {
			d = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年2月16日 下午4:26:32
	 * @Description <p>UTC时间转换为本地时间，默认的UTC的格式为yyyy-MM-dd'T'HH:mm:ss.SSS'Z'  </P>
	 * @param utcTime  utc时间字符串
	 * @param localTimePatten  要转换成的本地时间格式
	 * @return
	 * @throws ParseException 
	 */
	public static String utc2Local(String utcTime,String localTimePatten) throws ParseException {
		String defaultPartern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		return utc2Local(utcTime, defaultPartern, localTimePatten);
	}

	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年2月16日 下午2:51:57
	 * @Description <p>UTC时间转换为本地时间
	 *              </P>
	 * @param utcTime utc时间字符串
	 * @param utcTimePatten UTC时间符合的pattern
	 * @param localTimePatten 要转换成的本地时间格式
	 * @return
	 * @throws ParseException 
	 */
	public static String utc2Local(String utcTime, String utcTimePatten,
												   String localTimePatten) throws ParseException {
		SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
		utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date gpsUTCDate = utcFormater.parse(utcTime);
		SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
		localFormater.setTimeZone(TimeZone.getDefault());
		String localTime = localFormater.format(gpsUTCDate.getTime());
		return localTime;
	}
	
	
	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年2月16日 下午6:37:17
	 * @Description <p>UTC时间字符串转换为Date类型 时间</P>
	 * @param utcTime UTC时间字符
	 * @return Date类型时间
	 */
	public static Date utc2LocalDate(String utcTime) {
		String defaultPartern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		return utc2LocalDate(utcTime,defaultPartern);
	}
	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年2月16日 下午6:37:53
	 * @Description <p> UTC时间字符串转换为Date类型 时间 </P>
	 * @param utcTime UTC时间字符
	 * @param utcTimePatten UTC时间格式
	 * @return
	 */
	public static Date utc2LocalDate(String utcTime,String utcTimePatten) {
		SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
		utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date gpsUTCDate = null;
		try {
			gpsUTCDate = utcFormater.parse(utcTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return gpsUTCDate;
	}

	/**
	 * 根据日期和格式得到String形式日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getStringByPattern(Date date, String pattern) {
		Date currentTime = date;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 根据给定的字符串日期，和形式，转化成Date
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date getDateByGiven(String date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 计算两个日期相差多少天
	 * 
	 * @param startday
	 * @param endday
	 * @return
	 */
	public static int getTimeGapsInDays(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * @author 郑明亮
	 * @Time 2017年2月1日 下午7:25:35
	 * @Description <p>
	 *              获取两个时间相差的毫秒数值
	 *              </p>
	 * @param startday
	 *            开始日期
	 * @param endday
	 *            结束日期
	 * @return
	 */
	public static long getTimeGapsInMilliseconds(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return ei;
	}

	/**
	 * @author 郑明亮
	 * @Time 2017年2月1日 下午8:18:31
	 * @Description <p>
	 *              获得两个日期之前相差的月份
	 *              </p>
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return
	 */
	public static int getTimeGapsInMonth(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR)
				- startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH)
				- startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	/**
	 * 获得其他月份的日期
	 * 
	 * @param date
	 *            当前日期
	 * @param month
	 *            要跳过的月份数
	 * @return month个月之后的date日期
	 */
	public static Date getNextDateByMonth(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	/**
	 * 根据给出的日期获取本年的第一天的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeginOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE, 1);
		return c.getTime();
	}

	/**
	 * 根据给出的日期获取本年的最后一天的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DATE, 31);
		return c.getTime();
	}

	/**
	 * 获取上个月的第一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfLastMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DATE, 1);
		return c.getTime();
	}

	/**
	 * 获取上个月的最后一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfLastMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	/**
	 * 生成日期格式为： yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getNowDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
