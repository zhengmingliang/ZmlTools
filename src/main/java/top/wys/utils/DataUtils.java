package top.wys.utils;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * TODO 数据类型转换、格式校验、简易信息的获取，如系统时间
 * 
 * @author 郑明亮
 * @date 2016-1-27 下午6:26:55
 * @version 1.0
 */
public class DataUtils {

	private static final Logger log = LoggerFactory.getLogger(DataUtils.class);
	
	public DataUtils() {
		/* 不能被实例化 */
		throw new UnsupportedOperationException("cannot be instantiated");
	}
	// 根据Unicode编码完美的判断中文汉字和符号
		private static boolean isChinese(char c) {
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
					|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
					|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
					|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
				return true;
			}
			return false;
		}

		
		/**
		 * @author 郑明亮
		 * @Email zhengmingliang911@gmail.com
		 * @Time 2017年5月2日 下午5:14:07
		 * @Description <p>判断字符串中是否包含中文或中文(全角)符号  </P>
		 * @param strName 
		 * @return 包含返回true，否则返回false
		 */
		public static boolean isChinese(String strName) {
			char[] ch = strName.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				char c = ch[i];
				if (isChinese(c)) {
					return true;
				}
			}
			return false;
		}
	/**
	 * @author 郑明亮
	 * @Time 2017年3月10日 下午12:17:34
	 * @Description <p> 将字符串转换为double类型,当值为null时，返回0.0  </p>
	 * @param num 数字字符串
	 * @return
	 */
	public static Double getDoubleValueIgnoreNull(String num){
		double d = 0;
		try {
			if (num != null) {
				d = Double.parseDouble(num);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error("不能转换为Double类型",e);
		}
		return d;
	}
	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年2月28日 下午12:43:01
	 * @Description <p>将base64字符串转换为图片  </P>
	 * @param base64
	 * @param fileName
	 * @return
	 */
	public static String getFilePathFromBase64(String base64,String fileName){
		
//		byte data[] = com.alibaba.fastjson.util.Base64.decodeFast(base64);
		byte data[] = Base64Coder.decode(base64);
		File file = new File(fileName);
		try(FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(data);
			fos.flush();System.out.println("完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}
	
	/**
	 * @author 郑明亮
	 * @Time 2017年2月6日 上午9:38:29
	 * @Description <p>得到对明文加密后的文本   </p>
	 * @param source 需要加密的文本（明文密码）
	 * @param hashIterations MD5加密散列次数
	 * @return
	 */
//	public static String getEncryptionPasswd(String source,int hashIterations){
//		Md5Hash md5Hash = new Md5Hash(source, hashIterations);
//		return md5Hash.toString();
//	}
	/**
	 * @author 郑明亮
	 * @Time 2017年2月6日 上午9:33:27
	 * @Description <p> 得到对明文加密后的文本  (因Android端AS导shiro包编译有问题，所以md5加盐加密暂时不用shiro包中的)</p>
	 * @param source 需要加密的文本（明文密码）
	 * @param salt 需要对明文密码进行随机组合的任意字符（用户不需要记住，只是对于文本加密使用）
	 * @param hashIterations MD5加密散列次数
	 * @return
	 */
//	public static String getEncryptionPasswd(String source,String salt,int hashIterations){
//		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
//		return md5Hash.toString();
//	}
	
	/**
	 * @author 郑明亮
	 * @Time 2017年2月5日 下午2:50:58
	 * @Description <p>
	 *              获取唯一标识id字符串（32位）
	 *              </p>
	 * @return 32位UUID
	 */
	public static String getUUID() {
		//生成一个唯一的36位UUID
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		//把"-"去掉，则剩下为32位数字和字母随机组合的唯一字符id
		id = id.replaceAll("-", "");
		return id;
	}

	/**
	 * MD5加密
	 */
	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString().toUpperCase();
	}
	 /**
     * @author 郑明亮
     * @Time 2017年2月6日 上午9:38:29
     * @Description <p>得到对明文加密后的文本   </p>
     * @param source 需要加密的文本（明文密码）
     * @param salt 作料、盐值
     * @return
     */
    public static String getEncryptionPasswd(String source,String salt){
        return md5(source+md5(salt));
    }
    /**
     * @author 郑明亮
     * @Time 2017年2月6日 上午9:33:27
     * @Description <p> 得到对明文加密后的文本  </p>
     * @param source 需要加密的文本（明文密码）
     * @param salt 需要对明文密码进行随机组合的任意字符（用户不需要记住，只是对于文本加密使用）
     * @param hashIterations MD5加密散列次数
     * @return
     */
    @SuppressWarnings("unused")
	public static synchronized String getEncryptionPasswd(String source,String salt,int hashIterations){
        String md5 = source+md5(salt==null?"":salt);
        if (md5 == null){
            return "";
        }
        for (int i =1;i <= hashIterations;i++ ){
            md5 = md5(md5).toUpperCase();
        }
       return md5;
    }
	/**
	 * 获取【电脑】本机所有IP
	 */
	public static String getLocalHostIP() {
		List<String> res = new ArrayList<String>();
		Enumeration<?> netInterfaces;
		String ipAddr = "";
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces
						.nextElement();
				// System.out.println("---Name---:" + ni.getName());
				Enumeration<?> nii = ni.getInetAddresses();
				while (nii.hasMoreElements()) {
					ip = (InetAddress) nii.nextElement();
					if (ip.getHostAddress().indexOf(":") == -1) {
						res.add(ip.getHostAddress());
						// System.out.println("本机的ip=" + ip.getHostAddress());
						ipAddr = ip.getHostAddress();
						// System.err.println(ipAddr);
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		// return (String[]) res.toArray(new String[0]);
		return ipAddr;
	}

	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年3月28日 下午4:19:35
	 * @Description <p>获取公网ip  </P>
	 * @return
	 */
	public static String getWebIpAddress1(){
		String ip = "公网ip获取失败";
		try {
			
			String html =Jsoup.connect("http://city.ip138.com/ip2city.asp").get().select("center").html(); 
			 ip = html.substring(html.indexOf("[")+1,html.indexOf("]"));
			log.info("公网ip："+html);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("获取公网ip失败", e);
		}
		return ip;
	}
	/**
	 * @author 郑明亮
	 * @time 2017年11月7日15:39:52
	 * @description <p>获取公网ip</p>
	 * @return
	 */
	public static String getWebIpAddress2(){
		String ip = "公网ip获取失败";
		try {
			URL url = new URL("http://city.ip138.com/ip2city.asp");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
			connection.setRequestProperty("Host","city.ip138.com");
			connection.setRequestProperty("Connection","keep-alive");
			connection.setReadTimeout(10000);
			InputStream inputStream = connection.getInputStream();
			String str = new String(IsToByte(inputStream),"gb2312").trim();
			ip = str.substring(str.indexOf('[')+1, str.lastIndexOf(']'));

//			log.info("公网ip："+ip);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("获取公网ip失败", e);
		}
		return ip;
	}
	/**
	 * 获取本机mac
	 * @return
	 */
	public static String getLocalMac() {
		StringBuffer sb = new StringBuffer("");
		try {
			InetAddress ia = InetAddress.getLocalHost();
			//获取网卡，获取地址
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			System.out.println("mac数组长度："+mac.length);
			for(int i=0; i<mac.length; i++) {
				if(i!=0) {
					sb.append("-");
				}
				//字节转换为整数
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				//            System.out.println("每8位:"+str);
				if(str.length()==1) {
					sb.append("0"+str);
				}else {
					sb.append(str);
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		String addr = sb.toString().toUpperCase();
		System.out.println("本机MAC地址:"+addr);
		return addr;
	}
	/**
	 * getLocalIpAddress:[获得手机的ip地址].
	 * 
	 * @author 郑明亮 （应该联网权限）
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return null;
	}

	/**
	 * 将输入流转换为byte[]
	 * 
	 * @param is
	 *            输入流
	 * @return
	 */
	public static byte[] IsToByte(InputStream is) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte buffer[] = new byte[1024];
		int len = 0;
		try {
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				bos.flush();
				bos.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return bos.toByteArray();
	}

	/**
	 * isLeapYear:[判断是否为闰年].
	 * 
	 * @author 郑明亮
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		boolean flag = false;
		int y1 = year % 100;
		if (y1 == 0) {
			if (year % 400 == 0) {
				flag = true;
			}
		} else {
			if (year % 4 == 0) {
				flag = true;
			}
		}
		return flag;

	}

	/**
	 * @param month
	 *            1~12
	 * @return 根据月份，返回这个月有多少天
	 */
	public static int getDaysOfMonths(int month) {
		int day = 30;
		switch (month) {
		case 1:
			day = 31;
			break;
		case 2:
			day = 28;
			break;
		case 3:
			day = 31;
			break;
		case 4:
			day = 30;
			break;
		case 5:
			day = 31;
			break;
		case 6:
			day = 30;
			break;
		case 7:
			day = 31;
			break;
		case 8:
			day = 31;
			break;
		case 9:
			day = 30;
			break;
		case 10:
			day = 31;
			break;
		case 11:
			day = 30;
			break;
		case 12:
			day = 31;
			break;
		default:
			break;
		}

		return day;
	}

	/**
	 * [根据传入的month 和day拼接成一个日期字符串 ;如：传入getMonthsAndDays(1,1) 返回0101]
	 * 
	 * @author 郑明亮
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getMonthsAndDays(int month, int day) {
		String monthString = month + "";
		String dayString = day + "";
		int monlen = monthString.length();
		int daylen = dayString.length();
		if (monlen == 1) {
			monthString = 0 + monthString;
		}
		if (daylen == 1) {
			dayString = 0 + dayString;
		}

		return monthString + dayString;
	}

	/**
	 * 默认请传入null 获得当前的系统时间，默认格式为 yyyy-MM-dd HH:mm:ss（24小时制） 你也许需要 yyyy-MM-dd
	 * hh:mm:ss (12小时制) yyyy年MM月dd日 HH:mm:ss yyyy年MM月dd日 HH时mm分ss秒
	 * 
	 * @return String 指定格式的当前时间
	 */
	public static String getSystemTime(String formateString) {
		SimpleDateFormat dateFormat = null;
		if (formateString == null) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			dateFormat = new SimpleDateFormat(formateString);
		}

		return dateFormat.format(new Date());

	}

	/**
	 * 验证手机号是否合法
	 * 
	 * @param mobiles
	 *            手机号码
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

		Matcher m = p.matcher(mobiles);

		System.out.println(m.matches() + "---");
		return m.matches();
	}
	
	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年4月24日 上午11:51:18
	 * @Description <p>判断是不是网址  </P>
	 * @param url
	 * @return
	 */
	public static boolean isWebUrl(String url) {
		String patternString = "^((https|http|ftp|rtsp|mms)?://)"  
				         + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
				        + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184  
				         + "|" // 允许IP和DOMAIN（域名） 
				         + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.  
				         + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名  
				        + "[a-z]{2,6})" // first level domain- .com or .museum  
				        + "(:[0-9]{1,4})?" // 端口- :80  
				        + "((/?)|" // a slash isn't required if there is no file name  
				       + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";  
		Pattern p = Pattern
				.compile(patternString);

		Matcher m = p.matcher(url);

		System.out.println(m.matches() + "---");
		return m.matches();
	}

	/**
	 * 校验邮箱是否合法
	 * 
	 * @param s
	 *            传入的邮箱字符串
	 * @return
	 */
	public static boolean isEmail1(String s) {
		Pattern pattern = Pattern
				.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	/**
	 * Android 工具包自带验证Email方法
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail2(String email) {

		return EMAIL_ADDRESS.matcher(email).matches();
	}
	
	/**
	 * @author 郑明亮
	 * @Time 2017年3月13日 下午6:48:31
	 * @Description <p> 是否全是中文  </p>
	 * @param text 
	 * @return {@code true}全是中文 <br>   {@code false} 不全是中文、或不是中文
	 */
	public static boolean isChinessOnly(String text) {

		return PATTERN_IS_CHICHNESS.matcher(text).matches();
	}
	
	/**
	 * @author 郑明亮
	 * @Time 2017年3月13日 下午7:00:37
	 * @Description <p>判断是否是身份证号    </p>
	 * @param idCardNum 身份证号
	 * @return  {@code true}符合身份证号验证 <br>   {@code false} 不符合身份证号验证
	 */
	public static boolean isIdCardNumber(String idCardNum){
		return PATTERN_IS_ID_CARD_NUMBER_15.matcher(idCardNum).matches()||PATTERN_IS_ID_CARD_NUMBER_18.matcher(idCardNum).matches();
	}

	// 引用于Android.util.Patterns
	public static final Pattern EMAIL_ADDRESS = Pattern
			.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
	
	/**
	 * @author 郑明亮
	 * @Time 2017年3月13日 下午5:50:29
	 * @Description <p>匹配中文 </p>
	 */
	public static final Pattern PATTERN_IS_CHICHNESS = Pattern
			.compile("^[\\u4e00-\\u9fa5]{0,}$");
	
	/**
	 * @author 郑明亮
	 * @Time 2017年3月13日 下午6:58:08
	 * @Description <p> 15位身份证号验证</p>
	 */
	public static final Pattern PATTERN_IS_ID_CARD_NUMBER_15 = Pattern
			.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
	
	/**
	 * @author 郑明亮
	 * @Time 2017年3月13日 下午6:58:13
	 * @Description <p>18位身份证号验证 </p>
	 */
	public static final Pattern PATTERN_IS_ID_CARD_NUMBER_18 = Pattern
			.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");

	/**
	 * 判断字符串是否非空非null
	 * 
	 * @param strParm
	 *            需要判断的字符串
	 * @return 真假
	 */
	public static boolean isNoBlankAndNoNull(String strParm) {
		return !((strParm == null) || (strParm.equals("")));
	}
	
	

	
	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年2月17日 上午9:31:14
	 * @Description <p>截取字符串，并做一些异常处理  </P>
	 * @param source 要截取的字符串
	 * @param end 截取到多少位，默认从第一位开始截取
	 * @return  要截取范围的字符串
	 */
	public static String getSubString(String source,int end) {
		return getSubString(source, 0, end);
	}
	

	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年2月17日 上午9:21:52
	 * @Description <p> 截取字符串，并做一些异常处理 </P>
	 * @param source 要截取的字符串
	 * @param start 从多少位开始截取
	 * @param end 截取到多少位
	 * @return 要截取范围的字符串
	 */
	public static String getSubString(String source,int start, int end) {
		if (start < 0 || end < 0) {
			System.err.println("start or end < 0");
			return "";
		}
		
		if (source == null) {
			System.err.println("传入字符串为null");
			source = "";
		}else {
			int length = source.length() ;
		if (length > start) {
			if (length>=end) {
				source = source.substring(start,end);
			}
		}	
		}
		return source;
	}
	/**
     * @author 郑明亮
     * @Time 2017年3月19日 下午4:35:13
     * @Description <p>获取 从网络请求中请求到的文件名称  </p>
     * @param url 网络请求
     * @return 文件名称
     * @throws IOException
     */
    public static String getFileNameFromHttp(String url) throws IOException{
    	String fileName = null;
    	OkHttpClient client = new OkHttpClient().newBuilder()
    											.connectTimeout(30, TimeUnit.SECONDS)
    											.readTimeout(30, TimeUnit.SECONDS)
    											.build();
    	Request request = new Request.Builder().build();
    	Response response = client.newCall(request).execute();
    	String disposition = response.header("Content-Disposition");
    	if (disposition != null) {
			fileName = disposition.substring(disposition.indexOf("\""),disposition.lastIndexOf("\""));
		}
    	//如果从Head中获取失败，则从URL中进行截取文件名称
    	return fileName == null ? getFileNameFromUrl(url) : fileName;
    	
    }
    
    /**
     * @author 郑明亮
     * @Time 2017年3月19日 下午4:35:13
     * @Description <p>从网络请求中获取请求到的文件名称   </p>
     * @param head  OkHttp3  Header
     * @return 文件名称
     * @throws IOException
     */
    public static String getFileNameFromHttp(Headers head) throws IOException{
    	log.info("Header中的值:"+head.toString());
    	String fileName = null;
    	String disposition = head.get("Content-Disposition");
    	if (StringUtils.isNotEmpty(disposition)) {
    		
    		disposition = disposition.replaceAll("\"", "");
			fileName = disposition.substring(disposition.indexOf("=")+1);
		}
    	log.info("从Header中获取到的文件名称为："+fileName);
    	return fileName;
    	
    }
    
    /**
	 * @author 郑明亮
	 * @Time 2017年3月19日 下午4:41:50
	 * @Description <p> 从URL中截取文件名称  </p>
	 * @param url 从URL中截取文件名称
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getFileNameFromUrl(String url)
			throws UnsupportedEncodingException {
		String fileName;
		//对URL进行解码处理
		fileName = URLDecoder.decode(url.substring(url.lastIndexOf("/")+1), "utf-8");
		
		//如果URL结尾不是文件名，而是相关参数，则截取?前的内容
		if (fileName.contains("?")) {
			fileName = fileName.substring(0,fileName.indexOf("?"));
		}
		log.info("从URL中截取到的文件名："+fileName);
		return fileName;
	}
	
	/**
     * @author 郑明亮
     * @time 2017年4月3日11:47:00
     * @Description <p>将文件的大小传入（单位byte），返回转换成KB、MB、GB单位的文件大小（带单位）</p>
     * @param byteSize
     * @return
     */
    public static String getSize(long  byteSize) {
        if (byteSize<1024){
            return byteSize+"B";
        }
        double kb = byteSize/1024.0;
        if (kb<1024){
            return getRoundNum(kb,1)+"KB";
        }else {
        	double mb = kb/1024.0;
            if (mb < 1024){
                return getRoundNum(mb)+"MB";
            }else{
            	double gb = mb/1024.0;
                return getRoundNum(gb) + "GB";
            }

        }
    }

	/**
	 * 四舍五入，默认保留两位小数
	 * @param num
	 * @return
	 */
	public static String getRoundNum(double num){
    	return getRoundNum(num,null);
    }
    /**
     * @author 郑明亮
     * @time 2017年4月3日12:06:45
     * @Description <p>四舍五入</p>
     * @param num 要进行四舍五入的数字
     * @param pointNum 要保留几位小数
     * @return
     */
    public static String getRoundNum(double num,Integer pointNum){
	    String point ="";
	    DecimalFormat format = null;
	    if(point != null){
	    	 format = new DecimalFormat("#."+"00");
	    }
	    if(StringUtils.isEmpty(point)){
	    	for(int i = 0; i<pointNum; i++){
	    		point += "0";
	    	}
	    	 format = new DecimalFormat("#."+point);
	    }
	    
	    return format.format(num);
    }
    
    public static String getRandomNumCode(int number){
    	String codeNum = "";
    		int [] numbers = {0,1,2,3,4,5,6,7,8,9};
    		Random random = new Random();
    		for (int i = 0; i < number; i++) {
    			int next = random.nextInt(10000);
    			System.out.println(next);
				codeNum+=numbers[next%10];
			}
    		System.out.println("--------");
    	System.out.println(codeNum);
    	
    	return codeNum; 
    }

	/**
	 * 生成随机码值，包含数字、大小写字母
     * @author 郑明亮
	 * @param number 生成的随机码位数
	 * @return
	 */
	public static String getRandomCode(int number){
		String codeNum = "";
		int [] code = new int[3];
		Random random = new Random();
		for (int i = 0; i < number; i++) {
			int num = random.nextInt(10) + 48;
			int uppercase = random.nextInt(26) + 65;
			int lowercase = random.nextInt(26) + 97;
			code[0] = num;
			code[1] = uppercase;
			code[2] = lowercase;
			codeNum+=(char)code[random.nextInt(3)];
		}
		System.out.println(codeNum);

		return codeNum;
	}
    /**
	 * @author 郑明亮
	 * @time 2017年6月29日 上午9:21:15
	 * @description <p>从文本中截取出日期，并返回,格式为yyyy-MM-dd </p>
	 * @modifyBy
	 * @modifyTime 
	 * @modifyDescription<p> </p>
	 * @param text 文本信息
	 * @return
	 */
	public static String getDateFromText(String text){
		Pattern pattern = Pattern.compile("\\d+-[0-9]{2}-[0-9]{2}");
		String date = "";
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()){
			date = matcher.group(0);
		}
		return date;
	}
	
	/**
	 * @author 郑明亮
	 * @time 2017年6月29日 上午9:22:33
	 * @description <p>从文本中截取出日期  格式为yyyy-MM-dd HH:mm:ss </p>
	 * @modifyBy
	 * @modifyTime 
	 * @modifyDescription<p> </p>
	 * @param text
	 * @return
	 */
	public static String getDateTimeFromText(String text){
		Pattern pattern = Pattern.compile("\\d+-[0-9]{2}-[0-9]{2}\\s{1}[0-2][0-9](:[0-5][0-9]){2}");
		String date = "";
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()){
			date = matcher.group(0);
		}
		return date;
	}
	
	/**
	 * @author 郑明亮
	 * @time 2017年7月3日 下午4:13:26
	 * @description <p>根据正则表达式获取文本中的指定内容</p>
	 * @modifyBy
	 * @modifyTime 
	 * @modifyDescription<p> </p>
	 * @param text
	 * @param pattern
	 * @return
	 */
	public  static String getStringFromPattern(String text,String pattern){
		String content = "";
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		while(matcher.find()){
			content = matcher.group(0);
		}
		return content;
	}
	
	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年7月5日 下午9:19:51
	 * @Description <p> 将对象集合转换为List<Map<String, Object[]>>   </p>
	 * @version 1.0.0
	 * @param list
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static <T> List<Map<String, Object[]>> List2Array(List<T> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		T t = list.get(0);
		String[] fields  = ReflectionUtils.getFields(t.getClass());
		Object[][] objects = new Object[fields.length][list.size()];
		
		for (int i = 0;i < list.size();i++) {
			for (int j = 0; j< fields.length; j++) {
				Object object = ReflectionUtils.methodInvoke(list.get(i), "get"+fields[j]);
				objects[j][i] = object;
			}
			
		}
		
		List<Map<String, Object[]>> list2 = new ArrayList<>();
		for (int i = 0; i < objects.length; i++) {
			Map<String, Object[]> map = new HashMap<>();
			map.put(fields[i], objects[i]);
			list2.add(map);
			for (int j = 0; j < objects[i].length; j++) {
				System.out.printf("%s  ",objects[i][j]);
			}
			System.out.println();
		}
		
		return list2;
		
	}

	/**
	 * 获取随机ip地址
	 * @return
	 */
	public static String getRandomIp() {
		//ip的范围
		int[][] range_ip = {{607649792, 608174079},//36.56.0.0-36.63.255.255
				{1038614528, 1039007743},//61.232.0.0-61.237.255.255
				{1783627776, 1784676351},//106.80.0.0-106.95.255.255
				{2035023872, 2035154943},//121.76.0.0-121.77.255.255
				{2078801920, 2079064063},//123.232.0.0-123.235.255.255
				{-1950089216, -1948778497},//139.196.0.0-139.215.255.255
				{-1425539072, -1425014785},//171.8.0.0-171.15.255.255
				{-1236271104, -1235419137},//182.80.0.0-182.92.255.255
				{-770113536, -768606209},//210.25.0.0-210.47.255.255
				{-569376768, -564133889}, //222.16.0.0-222.95.255.255
		};
		//生成一个随机数
		Random random = new Random();
		int index = random.nextInt(10);
		String ip = numToip(range_ip[index][0] + new Random().nextInt(range_ip[index][1] - range_ip[index][0]));//获取ip

		return ip;
	}

	/**
	 * 数字拼接成ip字符串
	 *
	 * @param ip
	 * @return
	 */
	private static String numToip(int ip) {
		int[] b = new int[4];

		b[0] = (int) ((ip >> 24) & 0xff);
		b[1] = (int) ((ip >> 16) & 0xff);
		b[2] = (int) ((ip >> 8) & 0xff);
		b[3] = (int) (ip & 0xff);
		String ip_str = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);
		return ip_str;
	}

}
