package top.wys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 数据类型转换、格式校验、简易信息的获取，如系统时间
 *
 * @author 郑明亮
 * @version 1.0
 * @time 2016-1-27 下午6:26:55
 */
public class DataUtils {

    private static final Logger log = LoggerFactory.getLogger(DataUtils.class);

    private DataUtils() {
        /* 不能被实例化 */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 当source 和targets中的任意一个相等，则返回true，否则返回false
     * @param source
     * @param targets
     * @return
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
        return string2MD5(builder.toString());
    }

    /**
     * @param longSign 除了sign以外的所有参数拼接起来的值
     * @return
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年6月17日 下午3:16:35
     * @description <p> 计算校验码  <br>
     * @version 1.0.0
     */
    public static String getRequestSign(String longSign) {
        if (longSign == null)
            return "";

        return string2MD5(longSign + Constants.COUPON_KEY);
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
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
     * @param strName
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
     * @return
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
     * @param base64
     * @param fileName
     * @return
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
     * @return 32位UUID
     * @author 郑明亮
     * @time 2017年2月5日 下午2:50:58
     * @description <p>
     * 获取唯一标识id字符串（32位）
     * <br>
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
     * @param source 需要加密的文本（明文密码）
     * @param salt   作料、盐值
     * @return
     * @author 郑明亮
     * @time 2017年2月6日 上午9:38:29
     * @description <p>得到对明文加密后的文本   <br>
     */
    public static String getEncryptionPasswd(String source, String salt) {
        return md5(source + md5(salt));
    }

    /**
     * @param source         需要加密的文本（明文密码）
     * @param salt           需要对明文密码进行随机组合的任意字符（用户不需要记住，只是对于文本加密使用）
     * @param hashIterations MD5加密散列次数
     * @return
     * @author 郑明亮
     * @time 2017年2月6日 上午9:33:27
     * @description <p> 得到对明文加密后的文本  <br>
     */
    @SuppressWarnings("unused")
    public static synchronized String getEncryptionPasswd(String source, String salt, int hashIterations) {
        String md5 = source + md5(salt == null ? "" : salt);

        for (int i = 1; i <= hashIterations; i++) {
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
     * @return
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
            String str = new String(IOUtils.isToBytes(inputStream),"gb2312").trim();
            ip = str.substring(str.indexOf('[')+1, str.lastIndexOf(']'));

			log.info("公网ip：{}",ip);
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
		return getLocalMac(getLocalHostIP());
	}

	/**
	 * 根据本地ip地址获取 该网卡的物理地址
	 * @param host
	 * @return
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
		System.out.println("本机MAC地址:"+addr);
		return addr;
	}

	/**
	 * 获取所有的物理地址
	 * @return
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
     * @return
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
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * @param url
     * @return
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
     * @param text
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
     * @throws IOException
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
        Request request = new Request.Builder().build();
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
     * @throws IOException
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
     * @throws UnsupportedEncodingException
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
     * @return
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
     * @param num
     * @return
     */
    public static String getRoundNum(double num) {
        return getRoundNum(num, null);
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

    private static final char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    /**
     * 获取几位随机数
     *
     * @param number
     * @return
     */
    public static String getRandomNumCode(int number) {
        StringBuilder codeNum = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int next = random.nextInt(10000);
            codeNum .append(numbers[next % 10]);
        }
        return codeNum.toString();
    }

    /**
     * 生成随机码值，包含数字、大小写字母
     *
     * @param number 生成的随机码位数
     * @return
     * @author 郑明亮
     */
    public static String getRandomCode(int number) {
        StringBuilder codeNum = new StringBuilder();
        int[] code = new int[3];
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int num = random.nextInt(10) + 48;
            int uppercase = random.nextInt(26) + 65;
            int lowercase = random.nextInt(26) + 97;
            code[0] = num;
            code[1] = uppercase;
            code[2] = lowercase;
            codeNum.append((char) code[random.nextInt(3)]);
        }

        return codeNum.toString();
    }

    /**
     * @param text 文本信息
     * @return
     * @author 郑明亮
     * @time 2017年6月29日 上午9:21:15
     * @description <p>从文本中截取出日期，并返回,格式为yyyy-MM-dd <br>
     * @modifyBy
     * @modifyTime
     * @modifyDescription<p> <br>
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
     * @param text
     * @return
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
     * @param text
     * @param pattern
     * @return
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
     * @param list
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年7月5日 下午9:19:51
     * @description <p> 将对象集合转换为List<Map<String, Object[]>>   <br>
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
            /*for (int j = 0; j < objects[i].length; j++) {
                System.out.printf("%s  ", objects[i][j]);
            }*/
        }

        return list2;

    }

    /**
     * 获取随机ip地址
     *
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
     * 返回随机手机号码
     */
    private static final String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    public static String getRandomTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    private static final String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
    private static final String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    private static final String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    private static final String[] road="重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路".split(",");
    private static final String[] email_suffix="@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");

    /**
     * 返回随机Email
     * @param lMin 最小长度
     * @param lMax 最大长度
     * @return
     */
    public static String getRandomEmail(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int)(Math.random()*email_suffix.length)]);
        return sb.toString();
    }

    public static final String company ="谷信维点讯云网科宝壳久恒唐神洲百度雷米格子美团饿了么拉钩智联";
    public static String getRandomCompany(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuffer sb = new StringBuffer("北京");
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*base.length());
            sb.append(company.charAt(number%company.length()));
        }
        sb.append("科技有限公司");
        return sb.toString();
    }
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    /**
     * 返回中文姓名
     */
    private static String name_sex = "";
    public static String getChineseName() {
        int index=getNum(0, firstName.length()-1);
        String first=firstName.substring(index, index+1);
        int sex=getNum(0,1);
        String str=boy;
        int length=boy.length();
        if(sex==0){
            str=girl;
            length=girl.length();
//            name_sex = "女";
            name_sex = "0";
        }else {
//            name_sex="男";
            name_sex="1";
        }
        index=getNum(0,length-1);
        String second=str.substring(index, index+1);
        int hasThird=getNum(0,1);
        String third="";
        if(hasThird==1){
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third;
    }

    /**
     * 返回地址
     * @return
     */
    private static String getRandomRoad() {
        int index=getNum(0,road.length-1);
        String first=road[index];
        String second=String.valueOf(getNum(11,150))+"号";
        String third="-"+getNum(1,20)+"-"+getNum(1,10);
        return first+second+third;
    }

    /**
     * 数据封装
     * @return
     */
    public static Map getRandomPerson() {
        Map map=new HashMap();
        map.put("name", getChineseName());
        map.put("sex", name_sex);
        map.put("company", getRandomCompany(2,4));
        map.put("road", getRandomRoad());
        map.put("telephone", getRandomTel());
        map.put("mailbox", getRandomEmail(6,9));
        return map;
    }
    /**
     * 数字拼接成ip字符串
     *
     * @param ip
     * @return
     */
    private static String numToip(int ip) {
        int[] b = new int[4];

        b[0] = (ip >> 24) & 0xff;
        b[1] =  (ip >> 16) & 0xff;
        b[2] = (ip >> 8) & 0xff;
        b[3] = ip & 0xff;
        String ip_str = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);
        return ip_str;
    }


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

    public static Throwable getCauseException(Exception e) {
        if (e == null) return null;
        Throwable cause = e.getCause();
        if (cause != null) return cause;
        else return e;
    }

    public static final char UNDERLINE = '_';

    /**
     * @param param
     * @return
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
     * @param list
     * @return
     * @throws IllegalAccessException
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
     *<li> map转换成bean</li>
     * @param map
     * @param t
     * @param <T>
     * @return
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
     * @param map
     * @param beanClass
     * @return
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
     * @return
     * @throws Exception
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

    public static <T> List<T> mapsToBeans(List<Map<String, Object>> maps, Class<T> t) {
        if (maps == null || maps.isEmpty())
            return null;
        List<T> list = new ArrayList<T>();
        for (Map<String, Object> map : maps) {
            T obj = mapToBean(map, t);
            list.add(obj);
        }
        return list;
    }

    /**
     * 从jsonp中获取json字符串
     * @param jsonp
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
     * 当source 和targets中的任意一个相等，则返回true，否则返回false
     * @param source
     * @param targets
     * @return
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


}
