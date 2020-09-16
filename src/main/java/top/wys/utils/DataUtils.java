package top.wys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 数据类型转换、格式校验、简易信息的获取，如系统时间
 *
 * @author 郑明亮
 * @version 1.0
 * 2016-1-27 下午6:26:55
 */
public class DataUtils {

    private static final Logger log = LoggerFactory.getLogger(DataUtils.class);

    private DataUtils() {
        /* 不能被实例化 */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 当source 和targets中的任意一个相等，则返回true，否则返回false
     * @param source 源字符串
     * @param targets 要比较的字符串
     * @return true or false
     */
    public static boolean orEquals(String source,String... targets){
        if (source == null) {
            return false;
        }
        if (targets == null){
            return false;
        }

        for (String target : targets) {
            if (source.equals(target)){
                return true;
            }
        }
        return false;
    }
    /**
     * 参数排序
     * @param map 参数
     * @return 计算的sign
     */
    public static String getRequestSign(Map<String, Object> map) {
        if (map == null)
            return "";
        map.remove("sign");
        List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1,
                               Map.Entry<String, Object> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, Object> map1 =  infoIds.get(i);
            // value是数组 不加入签名
            Pattern p = Pattern.compile("^.*\\[[.*]+\\]$");
            Matcher m = p.matcher(map1.getKey());
            if (!m.matches()) {
                builder.append(map1.getKey() + map1.getValue());
            }
        }
        builder.append(Constants.COUPON_KEY);
        return EncryptUtils.md5(builder.toString());
    }

    /**
     * @param longSign 除了sign以外的所有参数拼接起来的值
     * @return getRequestSign
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年6月17日 下午3:16:35
     * @description <p> 计算校验码  <br>
     * @version 1.0.0
     */
    public static String getRequestSign(String longSign) {
        if (longSign == null)
            return "";

        return EncryptUtils.md5(longSign + Constants.COUPON_KEY);
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
     * @param strName 字符串
     * @return 包含返回true，否则返回false
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年5月2日 下午5:14:07
     * @description <p>判断字符串中是否包含中文或中文(全角)符号  </P>
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
     * @param num 数字字符串
     * @return 获得一个非null的double
     * @author 郑明亮
     * @time 2017年3月10日 下午12:17:34
     * @description <p> 将字符串转换为double类型,当值为null时，返回0.0  <br>
     */
    public static Double getDoubleValueIgnoreNull(String num) {
        double d = 0;
        try {
            if (num != null) {
                d = Double.parseDouble(num);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.error("不能转换为Double类型", e);
        }
        return d;
    }

    /**
     * @param base64 base64编码
     * @param fileName
     * @return 得到转成图片的存储路径
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年2月28日 下午12:43:01
     * @description <p>将base64字符串转换为图片  </P>
     */
    public static String getFilePathFromBase64(String base64, String fileName) {

        byte[] data = Base64Coder.decode(base64);
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
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
                Enumeration<?> nii = ni.getInetAddresses();
                while (nii.hasMoreElements()) {
                    ip = (InetAddress) nii.nextElement();
                    if (ip.getHostAddress().indexOf(':') == -1) {
                        res.add(ip.getHostAddress());
                         log.info("本机的ip=" + ip.getHostAddress());
                        ipAddr = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipAddr;
    }


    /**
     * 通过jsoup方式解析公网ip
     * @author 郑明亮
     * @return 公网ip
     */
	public static String getWebIpAddress1(){
		String ip = "公网ip获取失败";
		try {
		    Map<String,String> headers = Maps.newHashMap();
		    headers.put("Connection","keep-alive");
		    headers.put("Host","city.ip138.com");
		    headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            Connection connection = Jsoup.connect("http://city.ip138.com/ip2city.asp").headers(headers).timeout(Integer.MAX_VALUE);
            String html = connection.get().select("center").html();
			 ip = html.substring(html.indexOf('[')+1,html.indexOf(']'));
			log.info("公网ip：{}",html);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("获取公网ip失败", e);
		}
		return ip;
	}

    /**
     * @author 郑明亮
     * @time 2017年11月7日15:39:52
     * @description <p>获取公网ip<br>
     * @return 公网ip
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
            String str = new String(IOUtils.isToBytes(inputStream),"gb2312").trim();
            ip = str.substring(str.indexOf('[')+1, str.lastIndexOf(']'));

		} catch (IOException e) {
			e.printStackTrace();
			log.error("获取公网ip失败", e);
		}
		return ip;
	}
	/**
	 * @return  获取本机mac
	 */
	public static String getLocalMac() {
		return getLocalMac(getLocalHostIP());
	}

	/**
	 * 根据本地ip地址获取 该网卡的物理地址
	 * @param host
	 * @return 获取本机mac
	 */
	public static String getLocalMac(String host) {
		StringBuilder sb = new StringBuilder();
		try {
			//获取网卡，获取地址
			byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getByName(host)).getHardwareAddress();
			log.info("mac数组长度：{}",mac.length);
			for(int i=0; i<mac.length; i++) {
				if(i!=0) {
					sb.append("-");
				}
				//字节转换为整数
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
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
		log.debug("本机MAC地址:"+addr);
		return addr;
	}

	/**
	 *
	 * @return 获取所有的物理地址
	 */
	public static Set<String> getAllMacAddress() {
		Set<String> set = new TreeSet<>();
		try {
			Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
			while (el.hasMoreElements()) {
				byte[] mac = el.nextElement().getHardwareAddress();
				if (mac == null){
					continue;
				}
				StringBuilder macString = new StringBuilder("");
				for (byte b : mac) {

					//convert to hex string.
					String hex = Integer.toHexString(0xff & b).toUpperCase();
					if(hex.length() == 1){
						hex  = "0" + hex;
					}
					macString.append(hex);
					macString.append("-");
				}
				macString.deleteCharAt(macString.length() - 1);
				set.add(macString.toString());
			}

			if(set.size() == 0){
				log.info("Sorry, can't find your MAC Address.");
			}else{
				log.info("Your MAC Address is{} " , set);
			}
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return set;
	}
	/**
	 * getLocalIpAddress:[获得手机的ip地址].
	 *
	 * @author 郑明亮 （应该联网权限）
	 * @return 获得手机的ip地址
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
		    ex.printStackTrace();
		}
		return null;
	}

    private static String getIpInfoBySina(String ip){
	    String address = "";
	    String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
        try {
            String json = HttpUtils.get(url + ip);
            if(StringUtils.isNotEmpty(json)){
                JSONObject jsonObject = JSON.parseObject(json);
                String country = jsonObject.getString("country");
                String province = jsonObject.getString("province");
                String city = jsonObject.getString("city");
                if (Objects.equals(city,province)) {
                    address = country +" "+ province;
                }else{
                    address = country +" "+province +" "+ city;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    private static String getIpInfoByTaobao(String ip){
        String address = "";
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=";
        try {
            String json = HttpUtils.get(url + ip);
            if(StringUtils.isNotEmpty(json)){
                JSONObject jsonObject = JSON.parseObject(json);
                String country = jsonObject.getString("country");
                String province = jsonObject.getString("region");
                String city = jsonObject.getString("city");
                if (Objects.equals(city,province)) {
                    address = country +" "+ province;
                }else{
                    address = country +" "+province +" "+ city;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    private static String getAddressFromIpByOthers(String ip){
        String address = "";
        address = getIpInfoByTaobao(ip);
        if("".equals(address)){
            address = getIpInfoBySina(ip);
        }
        return address;
    }
    /**
     * 根据ip获取归属地
     * @param ips ip地址，多个ip可用逗号隔开
     * @return 归属地
     */
    public static String getAddressFromIp(String ips){
        String address = "";
        if (StringUtils.isEmpty(ips)) {
            return address;
        }
        if(ips.indexOf(',') != -1){
            StringBuilder sb = new StringBuilder();
            for (String ip : ips.split(",")) {
                String info = getAddressFromIpByOthers(ip);
                sb.append(info).append(",");
            }
            sb.deleteCharAt(sb.length() -1);
            address = sb.toString();
        }else{
            address = getAddressFromIpByOthers(ips);
        }
        return address;

    }

    /**
     * 验证手机号是否合法
     *
     * @param mobiles 手机号码
     * @return true or false
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^1\\d{10}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * @param url
     * @return true of false
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年4月24日 上午11:51:18
     * @description <p>判断是不是网址  </P>
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

        return m.matches();
    }

    /**
     * 校验邮箱是否合法
     *
     * @param s 传入的邮箱字符串
     * @return true or false
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
     * @param email 邮箱
     * @return true or false
     */
    public static boolean isEmail2(String email) {

        return EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * @param text 文本
     * @return {@code true}全是中文 <br>   {@code false} 不全是中文、或不是中文
     * @author 郑明亮
     * @time 2017年3月13日 下午6:48:31
     * @description <p> 是否全是中文  <br>
     */
    public static boolean isChinessOnly(String text) {

        return PATTERN_IS_CHICHNESS.matcher(text).matches();
    }

    /**
     * @param idCardNum 身份证号
     * @return {@code true}符合身份证号验证 <br>   {@code false} 不符合身份证号验证
     * @author 郑明亮
     * @time 2017年3月13日 下午7:00:37
     * @description <p>判断是否是身份证号    <br>
     */
    public static boolean isIdCardNumber(String idCardNum) {
        return PATTERN_IS_ID_CARD_NUMBER_15.matcher(idCardNum).matches() || PATTERN_IS_ID_CARD_NUMBER_18.matcher(idCardNum).matches();
    }

    // 引用于Android.util.Patterns
    public static final Pattern EMAIL_ADDRESS = Pattern
            .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

    /**
     * 判断是否为合法IP
     * @param ipAddress
     * @return
     */
    public static boolean isIp(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
    /**
     * @author 郑明亮
     * @time 2017年3月13日 下午5:50:29
     * @description <p>匹配中文 <br>
     */
    public static final Pattern PATTERN_IS_CHICHNESS = Pattern
            .compile("^[\\u4e00-\\u9fa5]{0,}$");

    /**
     * @author 郑明亮
     * @time 2017年3月13日 下午6:58:08
     * @description <p> 15位身份证号验证<br>
     */
    public static final Pattern PATTERN_IS_ID_CARD_NUMBER_15 = Pattern
            .compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

    /**
     * @author 郑明亮
     * @time 2017年3月13日 下午6:58:13
     * @description <p>18位身份证号验证 <br>
     */
    public static final Pattern PATTERN_IS_ID_CARD_NUMBER_18 = Pattern
            .compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");

    /**
     * 判断字符串是否非空非null
     *
     * @param strParm 需要判断的字符串
     * @return 真假
     */
    public static boolean isNoBlankAndNoNull(String strParm) {
        return !((strParm == null) || (strParm.equals("")));
    }


    /**
     * @param source 要截取的字符串
     * @param end    截取到多少位，默认从第一位开始截取
     * @return 要截取范围的字符串
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年2月17日 上午9:31:14
     * @description <p>截取字符串，并做一些异常处理  </P>
     */
    public static String getSubString(String source, int end) {
        return getSubString(source, 0, end);
    }


    /**
     * @param source 要截取的字符串
     * @param start  从多少位开始截取
     * @param end    截取到多少位
     * @return 要截取范围的字符串
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年2月17日 上午9:21:52
     * @description <p> 截取字符串，并做一些异常处理 </P>
     */
    public static String getSubString(String source, int start, int end) {
        if (start < 0 || end < 0) {
            log.error("start or end < 0");
            return "";
        }

        if (source == null) {
           log.error("传入字符串为null");
            source = "";
        } else {
            int length = source.length();
            if (length > start && length >= end) {
                    source = source.substring(start, end);
            }
        }
        return source;
    }

    /**
     * @param url 网络请求
     * @return 文件名称
     * @throws IOException 网络超时等异常
     * @author 郑明亮
     * @time 2017年3月19日 下午4:35:13
     * @description <p>获取 从网络请求中请求到的文件名称  <br>
     */
    public static String getFileNameFromHttp(String url) throws IOException {
        String fileName = null;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String disposition = response.header("Content-Disposition");
        if (disposition != null) {
            fileName = disposition.substring(disposition.indexOf('\"'), disposition.lastIndexOf('\"'));
        }
        //如果从Head中获取失败，则从URL中进行截取文件名称
        return fileName == null ? getFileNameFromUrl(url) : fileName;

    }

    /**
     * @param head OkHttp3  Header
     * @return 文件名称
     * @author 郑明亮
     * @time 2017年3月19日 下午4:35:13
     * @description <p>从网络请求中获取请求到的文件名称   <br>
     */
    public static String getFileNameFromHttp(Headers head){
        log.info("Header中的值:{}" , head);
        String fileName = null;
        String disposition = head.get("Content-Disposition");
        if (StringUtils.isNotEmpty(disposition)) {

            disposition = disposition.replaceAll("\"", "");
            fileName = disposition.substring(disposition.indexOf('=') + 1);
        }
        log.info("从Header中获取到的文件名称为：{}", fileName);
        return fileName;

    }

    /**
     * @param url 从URL中截取文件名称
     * @return
     * @throws UnsupportedEncodingException 解码异常
     * @author 郑明亮
     * @time 2017年3月19日 下午4:41:50
     * @description <p> 从URL中截取文件名称  <br>
     */
    public static String getFileNameFromUrl(String url)
            throws UnsupportedEncodingException {
        String fileName;
        //对URL进行解码处理
        fileName = URLDecoder.decode(url.substring(url.lastIndexOf('/') + 1), "utf-8");

        //如果URL结尾不是文件名，而是相关参数，则截取?前的内容
        if (fileName.contains("?")) {
            fileName = fileName.substring(0, fileName.indexOf('?'));
        }
        log.info("从URL中截取到的文件名：{}", fileName);
        return fileName;
    }

    /**
     * @param byteSize
     * @return 文件大小
     * @author 郑明亮
     * @time 2017年4月3日11:47:00
     * @description <p>将文件的大小传入（单位byte），返回转换成KB、MB、GB单位的文件大小（带单位）<br>
     */
    public static String getSize(long byteSize) {
        if (byteSize < 1024) {
            return byteSize + "B";
        }
        double kb = byteSize / 1024.0;
        if (kb < 1024) {
            return getRoundNum(kb, 1) + "KB";
        } else {
            double mb = kb / 1024.0;
            if (mb < 1024) {
                return getRoundNum(mb) + "MB";
            } else {
                double gb = mb / 1024.0;
                return getRoundNum(gb) + "GB";
            }

        }
    }

    /**
     * 四舍五入，默认保留两位小数
     *
     * @param num 数字
     * @return 处理后的数字
     */
    public static String getRoundNum(double num) {
        return getRoundNum(num, 2);
    }

    /**
     * @param num      要进行四舍五入的数字
     * @param pointNum 要保留几位小数
     * @return
     * @author 郑明亮
     * @time 2017年4月3日12:06:45
     * @description <p>四舍五入<br>
     */
    public static String getRoundNum(double num, Integer pointNum) {
        StringBuilder point = new StringBuilder();
        DecimalFormat format = new DecimalFormat("#." + "00");
        if (StringUtils.isEmpty(point)) {
            for (int i = 0; i < pointNum; i++) {
                point.append("0") ;
            }
            format = new DecimalFormat("#." + point);
        }

        return format.format(num);
    }


    /**
     * @param text 文本信息
     * @return 日期
     * @author 郑明亮
     * @time 2017年6月29日 上午9:21:15
     * @description <p>从文本中截取出日期，并返回,格式为yyyy-MM-dd <br>
     */
    public static String getDateFromText(String text) {
        Pattern pattern = Pattern.compile("\\d+-[0-9]{2}-[0-9]{2}");
        String date = "";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            date = matcher.group(0);
        }
        return date;
    }

    /**
     * @param text 文本
     * @return 日期
     * @author 郑明亮
     * @time 2017年6月29日 上午9:22:33
     * @description <p>从文本中截取出日期  格式为yyyy-MM-dd HH:mm:ss <br>
     * @modifyBy
     * @modifyTime
     * @modifyDescription<p> <br>
     */
    public static String getDateTimeFromText(String text) {
        Pattern pattern = Pattern.compile("\\d+-[0-9]{2}-[0-9]{2}\\s{1}[0-2][0-9](:[0-5][0-9]){2}");
        String date = "";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            date = matcher.group(0);
        }
        return date;
    }

    /**
     * @param text 文本
     * @param pattern 正则
     * @return 根据正则表达式获取文本中的指定内容
     * @author 郑明亮
     * @time 2017年7月3日 下午4:13:26
     * @description <p>根据正则表达式获取文本中的指定内容<br>
     * @modifyBy
     * @modifyTime
     * @modifyDescription<p> <br>
     */
    public static String getStringFromPattern(String text, String pattern) {
        String content = "";
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        while (matcher.find()) {
            content = matcher.group(0);
        }
        return content;
    }

    /**
     * @param list 指定集合
     * @return 指定对象
     * @throws IllegalAccessException 反射异常
     * @throws IllegalArgumentException 反射异常
     * @throws InvocationTargetException 反射异常
     * @throws NoSuchMethodException 反射异常
     * @throws SecurityException 反射异常
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年7月5日 下午9:19:51
     * @description <p> 将对象集合转换为List&lt;Mapt&lt;String, Object[]&gt;&gt; </p>
     * @version 1.0.0
     */
    public static <T> List<Map<String, Object[]>> List2Array(List<T> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        T t = list.get(0);
        String[] fields = ReflectionUtils.getFieldsNames(t.getClass());
        Object[][] objects = new Object[fields.length][list.size()];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < fields.length; j++) {
                Object object = ReflectionUtils.methodInvoke(list.get(i), "get" + fields[j]);
                objects[j][i] = object;
            }

        }

        List<Map<String, Object[]>> list2 = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            Map<String, Object[]> map = new HashMap<>();
            map.put(fields[i], objects[i]);
            list2.add(map);
        }

        return list2;

    }


    /**
     * @param e 异常
     * @return 堆栈错误信息
     */
    public static String getStackTrace(Throwable e) {
        String detail = null;
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw)) {

            e.printStackTrace(pw);
            detail = sw.toString();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return detail;
    }

    /**
     * @param e 异常
     * @return 堆栈错误信息
     */
    public static Throwable getCauseException(Exception e) {
        if (e == null) return null;
        Throwable cause = e.getCause();
        if (cause != null) return cause;
        else return e;
    }

    public static final char UNDERLINE = '_';

    /**
     * @param param 驼峰式字符串
     * @return 驼峰转下划线
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * @param param 带下划线的字符串
     * @return 下划线转驼峰
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将下划线转为驼峰字符串
     * @param param 带下划线的字符串
     * @return 驼峰字符串
     */
    public static String underlineToCamel2(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。<br>
     * 例如：HelloWorld-&gt;HELLO_WORLD
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String camelToUnderscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。<br>
     * 例如：HELLO_WORLD-&gt;HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String underLineToCamelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * @param list 集合
     * @return 指定map集合
     * @throws IllegalAccessException 反射异常
     * @author 郑明亮
     * @time 2017年10月17日14:09:03
     * @description <p>将Bean集合转换为map集合<br>
     */
    public static List<Map<String, Object>> toListMap(List<? extends java.io.Serializable> list) throws  IllegalAccessException{
        List<Map<String, Object>> listMaps = new ArrayList<>();
        Map map = null;
        for (int i = 0; i < list.size(); i++) {
            Object baseModel = list.get(i);
            Field[] fields = ReflectionUtils.getAllFields(baseModel);
            map = new LinkedHashMap();
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                map.put(fields[j].getName(), fields[j].get(baseModel));
            }
            listMaps.add(map);
        }
        return listMaps;
    }

    /**
     * TODO 当类型不一致时，会导致设值失败
     * <ul>
     * <li> map转换成bean</li>
     * </ul>
     * @param map map集合
     * @param t 要转换的类型
     * @param <T> 泛型
     * @return 转换后的对象
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> t){
        if (map == null)
            return null;

        T obj = null;
        try {
            obj = t.newInstance();

            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * 把一个map转换为一个对象
     * @param map map集合
     * @param beanClass 要转换的类型
     * @return 转换后的对象
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass){
        if (map == null)
            return null;

        Object obj = null;
        try {
            obj = beanClass.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }

                field.setAccessible(true);
                Class type = field.getType();
                Object value = map.get(field.getName());
                if (value instanceof String) {//因为不能保证的到的值是否有空值，如果有则先过滤掉
                    value = Strings.emptyToNull(value + "");
                }
                if (value != null) {//防止强转空指针
                    if (type.equals(Integer.class)) {//保证类型安全
                        field.set(obj, Integer.valueOf((String) value));//属性时Integer类型，反射时不能用setInt来赋值，否则会报Can not set java.lang.Integer field ... to (int)的错误
                    } else if (type.equals(Double.class)) {
                        field.setDouble(obj, Double.parseDouble(((String) value)));
                    } else if (type.equals(Long.class)) {
                        field.setLong(obj, Long.parseLong(((String) value)));
                    } else if (type.equals(Date.class)) {
                        field.set(obj, DateUtils.getDateByGiven(((String) value), "yyyy-MM-dd HH:mm:ss"));
                    } else {
                        field.set(obj, value);
                    }
                }


            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }


    /**
     * 将map集合转换为bean集合
     *
     * @param maps map集合
     * @param t    要转换成的对象集合的类型
     * @return 转换后的对象
     */
    public static List<?> mapsToObjects(List<Map<String, Object>> maps, Class<?> t){
        if (maps ==null || maps.isEmpty())
            return Collections.EMPTY_LIST;
        List<Object> list = new ArrayList<Object>();
        for (Map<String, Object> map : maps) {
            Object obj = mapToObject(map, t);
            list.add(obj);
        }
        return list;
    }

    /**
     * 将map数组集合转换为对象集合
     * @param maps map数组
     * @param t 要转换成的对象集合
     * @param <T> 泛型
     * @return 转换结果
     */
    public static <T> List<T> mapsToBeans(List<Map<String, Object>> maps, Class<T> t) {
        if (maps == null || maps.isEmpty())
            return Collections.emptyList();
        List<T> list = new ArrayList<T>();
        for (Map<String, Object> map : maps) {
            T obj = mapToBean(map, t);
            list.add(obj);
        }
        return list;
    }

    /**
     * 从jsonp中获取json字符串
     * @param jsonp jsonp格式字符串
     * @param callback 回调方法名称
     * @return
     */
    public static String getJsonFromJsonp(String jsonp,String callback){
        int  offset = 1;
        if(StringUtils.isNotEmpty(callback)){
            offset += callback.length();
        }
        if(StringUtils.isEmpty(jsonp)){
            return "{}";
        }
        String json = jsonp.substring(offset,jsonp.length() -2);
        return json;
    }

    /**
     * 将数据写回到客户端
     *
     * @param response
     * @param object
     */
    public static void writeToClient(ServletResponse response, Object object) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(object));
        } catch (IOException e) {
            log.error("Write the request data back to the client exception",e);
        }
    }

    /**
     * 从request中获取一个已排序的Map集合
     * @param request
     * @return
     */
    public static Map<String, Object> getSortedRequestParam(HttpServletRequest request) {
        Map<String,Object> params = Maps.newTreeMap();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            params.put(key,value[0]);
        }
        return params;
    }

    private static final String UNKNOWN = "unknown";
    /**
     *
     * 获取访问系统的IP地址
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        if (request != null) {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = "127.0.0.1";
            }
            return ip;
        } else {
            return "";
        }
    }

    /**
     * 获取对象中空值的属性名
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 获取文件名或任意字符串的后缀字符
     * @param fileName 文件名或任意字符串
     * @param splitRegex 分隔符（支持正则表达式）
     * @return
     */
    public static String getSuffix(String fileName,String splitRegex) {
        if (StringUtils.isEmpty(splitRegex)) {
            splitRegex = "\\.";
        }
        String[] split = fileName.split(splitRegex);
        String suffix = "";
        if(split.length> 1){
            suffix = split[split.length - 1];
        }
        return suffix;
    }

    /**
     * 获取文件名的后缀名，默认分隔符为“.”
     * @param fileName 文件名或任意字符串
     * @return
     */
    public static String getSuffix(String fileName) {
        return getSuffix(fileName,"\\.");
    }

}
