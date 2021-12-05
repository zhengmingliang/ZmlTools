package top.wys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;

import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by 郑明亮 on 2019/10/19 12:06.
 */
public class HttpUtilsTest {

    @Test
    public void get() {
        String url = "http://unionsug.baidu.com/su?p=3&cb=callback&wd=csdn";
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 50; i++) {
            try {
                String result = HttpUtils.get(url);
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Duration elapsed = stopwatch.elapsed();
        long seconds = elapsed.getSeconds();
        System.out.println("seconds = " + seconds);
        stopwatch.stop();
        String stop = stopwatch.toString();
        System.out.println("stop = " + stop);


    }

    @Test
    public void getStringFromPostBatchTest() {
        for (int i = 0; i < 50; i++) {
            getStringFromPost();
        }
    }


    @Test
    public void bulkTest() throws IOException {
        String url = "http://192.168.4.77:9240/_bulk";
        JSONObject index = new JSONObject();
        index.put("_index","system_log111");
//        index.put("_type","doc");
        JSONObject table = new JSONObject();
        table.put("index",index);
        String json1 = "{\"logType\":\"business\",\"hostName\":\"DESKTOP-B520200\",\"level\":\"INFO\",\"projectPath\":\"D:zmlgithubdtszBI\",\"nodeIp\":\"192.168.56.1\",\"message\":\"eee\",\"threadName\":\"main\",\"time\":\"2021-01-04 19:15:59\",\"position\":\"com.dtsz.davin.util.file.FileUtilsTest.main() L:95\",\"projectName\":\"davin\",\"loggerName\":\"com.dtsz.davin.util.file.FileUtilsTest\",\"timestamp\":1609758959005}";
        String json2 = "{\"logType\":\"business\",\"hostName\":\"DESKTOP-B520200\",\"level\":\"INFO\",\"projectPath\":\"D:zmlgithubdtszBI\",\"nodeIp\":\"192.168.56.1\",\"message\":\"rrr\",\"threadName\":\"main\",\"time\":\"2021-01-04 19:15:59\",\"position\":\"com.dtsz.davin.util.file.FileUtilsTest.main() L:95\",\"projectName\":\"davin\",\"loggerName\":\"com.dtsz.davin.util.file.FileUtilsTest\",\"timestamp\":1609758959005}";
        JSONObject jsonObject = JSON.parseObject(json1);
        JSONObject jsonObject2 = JSON.parseObject(json2);
        String requestJson = table.toJSONString()+"\n"+jsonObject.toJSONString()+"\n"+table.toJSONString()+"\n"+jsonObject2.toJSONString()+"\n";
        System.out.println(requestJson);
        String result = HttpUtils.sendRequestBody(url, requestJson);
        System.out.println(result);
    }
    @Test
    public void getStringFromPost() {
        String url = "http://localhost:8888/er";
        Stopwatch stopwatch = Stopwatch.createStarted();
        String json = "{\"source\":\"RPD\",\"receiveCoinCode\":\"ETH\"}";
        Map<String, Object> map = new HashMap(FastJsonTools.createJsonToMap(json));
        for (int i = 0; i < 10; i++) {
            try {
                String result = HttpUtils.getStringFromPost(url,map);
//                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stopwatch.stop();
        String stop = stopwatch.toString();
        System.out.println(stop);
    }

    @Test
    public void sendRequestBody() {
        String url = "http://localhost:8888/er";
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 10; i++) {
            try {
                String json = "{\"source\":\"RPD\",\"receiveCoinCode\":\"ETH\"}";
                String result = HttpUtils.sendRequestBody(url,json);
//                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stopwatch.stop();
        String stop = stopwatch.toString();
        System.out.println(stop);
    }

    @Test
    public void testSendRequestBody() {
        for (int i = 0; i < 50; i++) {
            sendRequestBody();
        }
    }

    @Test
    public void xmlTest() throws IOException {
        String xml = "<xlmReq>\n" +
                "    <name>张三</name>\n" +
                "</xlmReq>";
        String url = "http://localhost:8888/xlm";
        //修改默认请求参数类型为xml
        MediaType mediaType = MediaType.parse("application/xml");
        HttpUtils.defaultMediaType = (mediaType);
        String result = HttpUtils.sendRequestBody(url, xml,"application/xml");
        System.out.println("result = " + result);

        // 只修改该此的请求类型为xml
        result = HttpUtils.sendRequestBody(url, xml,"application/xml");
        System.out.println("result = " + result);
        getStringFromPost();
    }
}