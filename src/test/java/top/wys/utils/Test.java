package top.wys.utils;
/**
 * Created by 郑明亮 on 2018/1/10 16:28.
 */

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import com.google.common.eventbus.AsyncEventBus;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import okhttp3.MediaType;
import okhttp3.Response;

/**
 * @author 郑明亮   @email 1072307340@qq.com
 * @version 1.0
 * @time 2018/1/10 16:28
 * @description <p> </p>
 */
public class Test {

    Logger log = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void test() {
        String str = "76ED9C7B9F42365C5EE4B9EDA2BD2B57";
        str = "eyJjb21wdXRlck5hbWUiOiJXQU5HWUMiLCJtYWNBZGRyIjoiNDAtOEQtNUMtN0YtNTktMzEiLCJvc05hbWUiOiJXaW5kb3dzIDciLCJ1c2VyTmFtZSI6IkFkbWluaXN0cmF0b3IifQ==";
        str = "eyJjb21wdXRlck5hbWUiOiJERVNLVE9QLUpCVUE5R08iLCJtYWNBZGRyIjoiOTAtMkItMzQtNTctOTUtQzYiLCJvc05hbWUiOiJXaW5kb3dzIDEwIiwidXNlck5hbWUiOiK0sM3iIn0=";
        str = "eyJjb21wdXRlck5hbWUiOiJXQU5HWUMiLCJtYWNBZGRyIjoiNDAtOEQtNUMtN0YtNTktMzEiLCJvc05hbWUiOiJXaW5kb3dzIDciLCJ1c2VyTmFtZSI6IkFkbWluaXN0cmF0b3IifQ==";
        str = "eyJjb21wdXRlck5hbWUiOiJERVNLVE9QLTlDMUk3UFIiLCJtYWNBZGRyIjoiMTgtREItRjItMkItOTgtNDIiLCJvc05hbWUiOiJXaW5kb3dzIDEwIiwidXNlck5hbWUiOiLC7cfHIn0=";
        str = "eyJjb21wdXRlck5hbWUiOiJMQVBUT1AtMDlOOTZLVU8iLCJtYWNBZGRyIjoiMDAtNTAtNTYtQzAtMDAtMDgiLCJvc05hbWUiOiJXaW5kb3dzIDEwIiwidXNlck5hbWUiOiJ3bGoifQ==";
        str = "eyJjb21wdXRlck5hbWUiOiJERVNLVE9QLTRJNEg1T0UiLCJtYWNBZGRyIjoiMDAtNTAtNTYtQzAtMDAtMDgiLCJvc05hbWUiOiJXaW5kb3dzIDEwIiwidXNlck5hbWUiOiJBZG1pbmlzdHJhdG9yIn0=";
        str = "eyJjb21wdXRlck5hbWUiOiJpWnZoY2lqN2RybW1yeloiLCJtYWNBZGRyIjoiMDAtMTYtM0UtMDAtRUUtOEYiLCJvc05hbWUiOiJXaW5kb3dzIFNlcnZlciAyMDE2IiwidXNlck5hbWUiOiJBZG1pbmlzdHJhdG9yIn0=";
        str = "eyJjb21wdXRlck5hbWUiOiJERVNLVE9QLU5FRzQ3NTAiLCJtYWNBZGRyIjoiQTQtMDItQjktN0YtQzctRjQiLCJvc05hbWUiOiJXaW5kb3dzIDEwIiwidXNlck5hbWUiOiJCQ1RDLVIifQ==";
        str = "eyJjb21wdXRlck5hbWUiOiJaTUwtUEMiLCJtYWNBZGRyIjoiRUMtNTUtRjktQzktNEItNjQiLCJvc05hbWUiOiJXaW5kb3dzIE5UICh1bmtub3duKSIsInVzZXJOYW1lIjoiem1sIn0=";
        str = "eyJjb21wdXRlck5hbWUiOiJDQkhYVVRUIiwibWFjQWRkciI6IkI4LTg4LUUzLUYxLUZBLUZFIiwib3NOYW1lIjoiV2luZG93cyA3IiwidXNlck5hbWUiOiJDQkgifQ==";
        str = "eyJjb21wdXRlck5hbWUiOiJERVNLVE9QLUxGUkJQTDAiLCJtYWNBZGRyIjoiMDAtNTAtNTYtQzAtMDAtMDgiLCJvc05hbWUiOiJXaW5kb3dzIDEwIiwidXNlck5hbWUiOiJhZG1pbiJ9";
        str = "eyJjb21wdXRlck5hbWUiOiJQN1g4MlNMU0xGOTBISUsiLCJtYWNBZGRyIjoiMDAtNTAtNTYtQzAtMDAtMDgiLCJvc05hbWUiOiJXaW5kb3dzIDciLCJ1c2VyTmFtZSI6IkFkbWluaXN0cmF0b3IifQ==";
        str = "eyJjb21wdXRlck5hbWUiOiJQN1g4MlNMU0xGOTBISUsiLCJtYWNBZGRyIjoiMDAtNTAtNTYtQzAtMDAtMDgiLCJvc05hbWUiOiJXaW5kb3dzIDciLCJ1c2VyTmFtZSI6IkFkbWluaXN0cmF0b3IifQ==";
        str = "eyJjb21wdXRlck5hbWUiOiJQQy0yMDE4MDYwNktORUoiLCJtYWNBZGRyIjoiMDAtRTAtNEMtQTgtNzItMzAiLCJvc05hbWUiOiJXaW5kb3dzIDciLCJ1c2VyTmFtZSI6IkFkbWluaXN0cmF0b3IifQ==";
        str = "eyJjb21wdXRlck5hbWUiOiJaTUwtUEMiLCJtYWNBZGRyIjoiNkMtODgtMTQtMjctN0QtRDQiLCJvc05hbWUiOiJXaW5kb3dzIE5UICh1bmtub3duKSIsInVzZXJOYW1lIjoiem1sIn0=";
        String decode = Base64Coder.decodeString(str);
//        String decode = "{\"computerName\":\"DESKTOP-JBUA9GO\",\"macAddr\":\"90-2B-34-57-95-C6\",\"osName\":\"Windows 10\",\"userName\":\"38290\"}";
        System.out.println(decode);
        String key = EncryptUtils.getEncryptionPasswd(decode, "zml_2015");
        key = EncryptUtils.md5(decode + EncryptUtils.md5("zml_2015").toUpperCase()).toUpperCase();
        System.out.println(key);
    }


    @org.junit.Test
    public void testEmail() {
//        System.out.println(DataUtils.isEmail1("1@wuyongshi.top"));
//        System.out.println(DataUtils.isEmail2("1@wuyongshi.top"));
        for (int i = 0; i < 20; i++) {
            log.info(RandomUtils.getUUID().toUpperCase());
//            System.out.println();
        }
    }

    @org.junit.Test
    public void testGetCoinList() {
        String url = "https://transfer.swft.pro/api/v1/queryCoinList";
        Map<String, Object> param = Maps.newHashMap();
        param.put("supportType", "advanced");
        try {
//        @JSONField(Date)
            System.out.println(HttpUtils.getStringFromPost(url, param));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void meiseShangChengTest() throws IOException {
        String visitUrl = "http://ucenter.xmeise.com/user/signin/";
        Response response = HttpUtils.getResponse(visitUrl);
        String cookieValue = HttpUtils.getCookieValue(response);
        System.out.println(cookieValue);
        //解析formHash这个随机值
        String html = response.body().string();
        String fromHash = Jsoup.parse(html).select("#form_hash").val();
        System.out.println(fromHash);

        String url = "http://ucenter.xmeise.com/index/sms/";
        String mobile = "15733100573";
        Map<String,Object> params = Maps.newHashMap();
        params.put("page_type","signin");
        params.put("form_hash",fromHash);
        params.put("_",System.currentTimeMillis());
        params.put("mobile",mobile);



        Map<String,String> headerMap = Maps.newHashMap();
        headerMap.put("User-Agent",RandomUtils.getRandomUserAgent());
        headerMap.put("X-Requested-With","XMLHttpRequest");
        headerMap.put("Referer",visitUrl);
        headerMap.put("cookie", cookieValue);
        String result = HttpUtils.get(url, params, headerMap);
        System.out.println(result);


    }
    public static final String MOBILE = "15733100573";

    @org.junit.Test
    public void youKaoShiTest() throws IOException {
        String visitUrl = "https://admin.youkaoshi.cn/sign-up.html";
        HttpUtils.supportHttps();
//        String html = HttpUtils.get(visitUrl);
        Response response = HttpUtils.getResponse(visitUrl);
        String html = response.body().string();
        String cookie = HttpUtils.getCookieValue(response);
        System.out.println("cookie = " + cookie);
        String dataToken = Jsoup.parse(html).select(".get-code").attr("data-token");
        System.out.println("dataToken = " + dataToken);

        String url = "https://admin.youkaoshi.cn/sign-up.raw?task=sms.teacherRegisterCode";

        //header param
        Map<String,String> header = Maps.newHashMap();
        header.put("cookie",cookie);

        //body param
        Map<String,Object> params = Maps.newHashMap();
        params.put("phone",MOBILE);
        params.put(dataToken,1);


        String result = HttpUtils.getStringFromPost(url, params,header);
        System.out.println("result = " + result);


    }
    String seed = "abcdefghijklmnopqrstuvwxyz";
    char[] seedArray = seed.toCharArray();
    @org.junit.Test
    public void forTest() {
        StringBuilder sb = new StringBuilder();
        int size = 2;
        int length = seedArray.length;
        for (int i = 0; i < Math.pow(seed.length(),size); i++) {
            int count =0;
            for (int j = 0; j < size; i++) {
                sb.append(getChar(seedArray,count));
                count++;
                if(count == length){
                    count = 0;
                }
            }

        }

       /* for (char c : seedArray) {
            System.out.println(c);
        }*/
    }


    @org.junit.Test
    public void createPassWordTest() {
        String real = "5312";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 9; i++) {
            if (createPassWord(new char[i+1],0,i+1)) {
                long end = System.currentTimeMillis();
                System.out.println((end - start)/1000.0+"s");
            }
        }
    }

    @org.junit.Test
    public void createNumPassTest() {
        testPwd = "018911";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            if (createPassWord(new char[i+1],0,i+1)) {
                long end = System.currentTimeMillis();
                System.out.println((end - start)/1000.0+"s");
            }
        }
    }
    String testPwd = "9999";
    /**
     * @param str 存放拼装的密码的字符数组
     * @param n 下标
     * @param len 字符数组长度，也就是密码长度
     * @return
     */
    private boolean createPassWord( char[] str, int n, int len) {
        if (n == len) {
            String ps = new String(str);
            System.out.println(ps);
            if (testPwd.equalsIgnoreCase(ps)) {
                System.out.println("sucess:" + testPwd + " : " + ps);
                return true;
            }
            return false;
        }
        for (int i = 0; i <= 9; i++) {
            str[n] = (char) (i + '0');
            if (createPassWord(str, n + 1, len))
                return true;
        }
        return false;
    }

    private boolean createNumPass( char[] str, int n, int len) {
        if (n == len) {
            String ps = new String(str);
            if (testPwd.equalsIgnoreCase(ps)) {
                System.out.println("sucess:" + testPwd + " : " + ps);
                return true;
            }
            return false;
        }
        for (int i = 0; i <= 9; i++) {
            str[n] = (char) (i + '0');
            if (createNumPass(str, n + 1, len))
                return true;
        }
        return false;
    }


    public char getChar(char[] array,int index){
        return array[index];
    }

    @org.junit.Test
    public void passwdTest() {
   
        
        int min = 2;
        int max = 2;
        int length = seedArray.length;
        for (int i = min; i <= max; i++) {
            //每n位数进行遍历
            for (int seedIndex = 0; seedIndex < seedArray.length; seedIndex++) {
                StringBuilder sb = new StringBuilder();
                char c1 = seedArray[seedIndex];
                sb.append(c1);
                for (int seedIndex2 = seedIndex; seedIndex2 < seedArray.length; seedIndex2++) {
                    char c2 = seedArray[seedIndex2];
                    sb.append(c2);
                    /*for (int j = 0; j < i; j++) {
                        sb.append(seedArray[seedIndex2]);
                    }*/
                    System.out.println(sb.toString());
                }

            }

        }
    }


    @org.junit.Test
    public void zhiPuZiTest() throws IOException {
        String visitUrl = "https://www.zhipuzi.com/register.html";
        String html = HttpUtils.get(visitUrl);
        html.split("timestamp");
        String url = "https://pf.zhipuzi.com/user/merchant/account/regverifyphonecode";
    }

    public void getStringFromPost() {
        String url = "http://localhost:8888/er";
        Stopwatch stopwatch = Stopwatch.createStarted();
        String json = "{\"name\":\"RPD\",\"receiveCoinCode\":\"ETH\"}";
        Map<String, Object> map = new HashMap(FastJsonTools.createJsonToMap(json));
        for (int i = 0; i < 1; i++) {
            try {
                String result = HttpUtils.sendRequestBody(url,map);
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stopwatch.stop();
        String stop = stopwatch.toString();
        System.out.println(stop);
    }


    @org.junit.Test
    public void javaScritptTest() throws IOException, ScriptException, NoSuchMethodException {
        String js = HttpUtils.get("http://m.dhc.net.cn/js/md5.js");
        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine engine = manager.getEngineByExtension("js");
        engine.eval(js);
        if(engine instanceof Invocable) {
            Invocable invoke = (Invocable)engine;    // 调用merge方法，并传入两个参数


            String result = (String)invoke.invokeFunction("hex_md5", "18519094522");

            System.out.println("result = " + result);
        }
    }

    @org.junit.Test
    public void getResponse() throws IOException {
        Response response = HttpUtils.getResponse("http://m.yifatong.com/Customers/register");
        String cookieValue = HttpUtils.getCookieValue(response);
        System.out.println("cookieValue = " + cookieValue);
        Map<String,String> header = Maps.newHashMap();
        header.put("cookie",cookieValue);
        String result = HttpUtils.get("http://m.yifatong.com/Customers/gettsms?rnd=1571478800.922&mobile=18519094522");
        System.out.println(result);
    }

    @org.junit.Test
    public void getResponse2() throws IOException {
        Response response = HttpUtils.getResponse("https://tb.ele.me/wow/zele/act/sjcz?wh_biz=tm");
        String cookieValue = HttpUtils.getCookieValue(response);
        System.out.println("cookieValue = " + cookieValue);
        Map<String,String> header = Maps.newHashMap();
        header.put("cookie",cookieValue);
        String url = "https://tb.ele.me/restapi/traffic/code/send";

        String result = HttpUtils.sendRequestBody(url,"{\"phone\":\"15733100573\"}",header);
        System.out.println(result);
    }


    @org.junit.Test
    public void xmlTest() throws IOException {
        String xml = "<xlmReq>\n" +
                "    <name>张三</name>\n" +
                "</xlmReq>";
        String url = "http://localhost:8888/xlm";
        MediaType mediaType = MediaType.parse("application/xml");
        HttpUtils.defaultMediaType = (mediaType);
        String result = HttpUtils.sendRequestBody(url, xml,"application/xml");
        System.out.println("result = " + result);
         result = HttpUtils.sendRequestBody(url, xml,"application/xml");
        System.out.println("result = " + result);
        getStringFromPost();
    }

    public static void main(String[] args) {
        EventBusTest();
    }
//    @org.junit.Test
    public static void EventBusTest()  {
//        EventBus bus = new EventBus();
//        bus.register(new EventBusListener());
//        bus.post("hello world");
//        bus.post(1024);
//        bus.post(2048L);
//        int a = 10240;
//        bus.post(a);
        Executor pool = Executors.newFixedThreadPool(2);
        AsyncEventBus asyncEventBus = new AsyncEventBus(pool);
        asyncEventBus.register(new EventBusListener());


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                asyncEventBus.post("开始模拟异步通知（子线程通知主线程更新数据）");
                int i = 0;
                while (i < 100) {
                    try {
                        //模拟进度数计算
                        int num = RandomUtils.getNum(1, 10);
                        TimeUnit.SECONDS.sleep(num);
                        i += num;
                        // 通知主线程进行进度更新
                        asyncEventBus.post(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        pool.execute(runnable);
//        new Thread(runnable).start();

//        pool.wait();

//        bus.register();
//        DoubleMath.factorial()
    }
}


