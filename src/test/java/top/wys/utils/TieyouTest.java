package top.wys.utils;
/**
 * Created by 郑明亮 on 2019/1/7 13:45.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

import java.util.Arrays;

/**
 * <ol>
 * 2019/1/7 13:45 <br>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 */
public class TieyouTest {
    protected String privateKey = "a8e350f1177"+"0d1e0ce9f11ea7b9b2850";

    /**
     * https://sec-m.ctrip.com/restapi/soa2/10957/json/SendLoginCode     POST
     *  text/plain
     * 铁友sign计算方式：
     * extendInfo 不参与sign的运算；type:0为短信，type:1为语音
     */
    @Test
    public void loginTest(){
        String params = "{\"appVersion\":\"7.4.2\",\"clientId\":\"android\",\"partnerName\":\"tieyou\",\"userMobile\":\"18519094522\",\"channel\":\"ark_android_hicloud\",\"sign\":\"78e63b58d45f30e26868aaa44f925ab8\",\"clientInfo\":\"android|COL-AL10|8.1.0|7.4.2|706009|ark_android_hicloud|869384032241018|WIFI|32001059510031348080\",\"reqTime\":\"1539100795\",\"extendInfo\":{\"type\":0},\"deviceId\":\"161206928a7dee7d8d41cd9effbe5bb1aa9ca6\",\"token\":\"\"}";
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject.toJSONString());
        System.out.println("------------------");
        System.out.println("sign:"+jsonObject.remove("sign"));
//        System.out.println(jsonObject.remove("extendInfo"));
        jsonObject.remove("reqTime");
        jsonObject.remove("userMobile");
        long time = System.currentTimeMillis() / 1000;
        System.out.println(time);
        jsonObject.put("reqTime",time);
        jsonObject.put("userMobile","15733100573");
        Object[] toArray = jsonObject.keySet().toArray();

        Arrays.sort(toArray);
       /* StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toArray.length; i++) {
            sb.append(toArray[i]).append("=").append(jsonObject.get(toArray[i])).append("&");
        }*/
        String str;
        String str2 = "extendInfo";
        StringBuffer stringBuffer = new StringBuffer();
        String str3 = "";
        int i = 0;
        while (i < toArray.length) {
            StringBuffer stringBuffer2;
            if ((toArray[i] instanceof String) && str2.equalsIgnoreCase((String) toArray[i])) {
                str = str3;
                stringBuffer2 = stringBuffer;
            } else {
                stringBuffer2 = stringBuffer.append(str3 + toArray[i] + "=" + jsonObject.get(toArray[i]));
                str = "&";
            }
            i++;
            stringBuffer = stringBuffer2;
            str3 = str;
        }
//        System.out.println(stringBuffer.toString());
        str = EncryptUtils.md5(stringBuffer.toString()).toLowerCase();
        jsonObject.put("sign", EncryptUtils.md5(String.format("%s%s", new Object[]{this.privateKey, str})).toLowerCase());
        System.out.println(jsonObject.toJSONString());

    }
}
