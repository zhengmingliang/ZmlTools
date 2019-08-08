package top.wys.utils;

import com.google.common.collect.Maps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * @author 郑明亮
 * @version 1.0
 * @time 2017年2月15日 下午4:42:51
 * @description <p>  </P>
 */
public class HttpUtils {

    private static final Logger log = LogManager.getLogger();

    /**
     * @author 郑明亮
     * @time 2017年3月19日 下午5:13:23
     * @description <p>网络失败最大尝试次数  </p>
     */
    protected static final int MAX_SERVER_LOAD_TIMES = 3;
    /**
     * 是否伪造IP头
     */
    public static boolean fakeIp = false;

    public static void supportHttps(){
        System.setProperty("jsse.enableSNIExtension","false");
    }

    /**
     * @param url
     * @return
     * @throws IOException
     * @description <p>简单的http get请求</p>
     */
    public static String get(String url) throws IOException {
        OkHttpClient client = getOkHttpClient();
        Request.Builder builder = new Request.Builder()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .url(url);
        if(fakeIp){
            builder.addHeader("X-FORWARDED-FOR", RandomUtils.getRandomIp());
        }
        Request request = builder
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    /**
     * @param url  请求host地址
     * @param params 请求key-value参数
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, Object> params) throws IOException{
        return get(url,params,null);
    }

    /**
     * @param url 请求host地址
     * @param params 请求key-value参数
     * @param headers 添加的header的map集合
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, Object> params,Map<String, String> headers) throws IOException {
        OkHttpClient client = getOkHttpClient();
        if(url == null){
            throw new RuntimeException("the request URL can not be null");
        }
        //拼接url
        String requestParamString = getRequestParamString(params);
        if (url.indexOf('?') != -1) {
            url+="&"+requestParamString;
        }else{
            url+="?"+requestParamString;
        }
        log.debug(url);
        Request.Builder build = new Request.Builder()
                .url(url)
                .headers(Headers.of(getDefaultHeaders()));
        if(fakeIp){
            build.addHeader("X-FORWARDED-FOR",RandomUtils.getRandomIp());
        }
        if(headers != null && headers.size() > 0){
            build.headers(Headers.of(headers));
        }
        Request request = build.build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 获取get请求的url拼接参数
     * @param params get请求的key-value集合
     * @return url拼接参数，eg: name=zhangsan&amp;age=11&amp;sex=male
     */
    public static String getRequestParamString(Map<String,Object> params){
        if(params == null || params.size() == 0){
            return "";
        }
        StringBuilder urlParam = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlParam.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
         urlParam = urlParam.deleteCharAt(urlParam.length() - 1);

        return urlParam.toString();
    }

    private static Proxy proxy;

    /**
     * 获取OKHttpClient实例
     */
    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder().connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true);
        if(proxy == null){
            return builder.build();
        }else{
            return builder.proxy(proxy).build();
        }
    }

    /**
     *  设置代理
     * @param proxy
     */
    public static void setProxy(Proxy proxy){
        HttpUtils.proxy = proxy;
    }

    /**
     * 设置代理，默认使用http代理，无授权方式
     * @param host 主机名/ip
     * @param port 端口
     */
    public static void setHttpProxy(String host,int port){
        SocketAddress address = new InetSocketAddress(host,port);
       HttpUtils.proxy = new Proxy(Proxy.Type.HTTP,address);
    }

    /**
     * 设置代理，默认使用socks代理,支持SOCKS (V4 or V5) ，无授权方式
     * @param host 主机名/ip
     * @param port 端口
     */
    public static void setSocksProxy(String host,int port){
        SocketAddress address = new InetSocketAddress(host,port);
        HttpUtils.proxy = new Proxy(Proxy.Type.SOCKS,address);
    }

    /**
     * 是否在使用代理
     */
    public static boolean isUseProxy(){
        if(proxy != null){
            return true;
        }else{
            return false;
        }
    }
    /**
     * POST请求
     * @param url 访问地址
     * @param params 请求参数
     * @param headers 请求Header
     * @return
     * @throws IOException
     */
    public static ResponseBody post(String url, Map<String, Object> params, Map<String, String> headers) throws IOException {
        OkHttpClient client = getOkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        //添加request参数
        if (params != null) {
            for (Map.Entry entry : params.entrySet()) {
                builder.add(entry.getKey()+"", entry.getValue()+ "");
            }
        }
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))    //添加header参数；
                .post(builder.build()); //添加requestBody;
        // 添加伪造IP
        if(fakeIp){
            requestBuilder.addHeader("X-FORWARDED-FOR", RandomUtils.getRandomIp());
        }

        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();
        return response.body();
    }

    /**
     * POST请求
     * @param url 访问地址
     * @param params 请求参数
     * @param headers 请求Header
     * @return
     * @throws IOException
     */
    public static String getStringFromPost(String url, Map<String, Object> params, Map<String, String> headers) throws IOException {
        return post(url, params, headers).string();
    }

    /**
     * POST请求
     * @param url 访问地址
     * @param params 请求参数
     * @param headers 请求Header
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromPost(String url, Map<String, Object> params, Map<String, String> headers) throws IOException {
        return post(url, params, headers).bytes();
    }

    /**
     *  POST请求
     * @param url 访问地址
     * @param params 请求参数
     * @param headers 请求Header
     * @return
     * @throws IOException
     */
    public static InputStream getStreamFromPost(String url, Map<String, Object> params, Map<String, String> headers) throws IOException {
        return post(url, params, headers).byteStream();
    }

    /**
     * POST请求
     * @param url   访问日志
     * @param param post参数
     * @return
     * @throws IOException
     */
    public static ResponseBody post(String url, Map<String, Object> param) throws IOException {
        OkHttpClient client = getOkHttpClient();
        Builder builder = new FormBody.Builder();
        if (param != null) {
            for (Map.Entry entry : param.entrySet()) {
                builder.add(entry.getKey()+"", entry.getValue()+ "");
            }
        }
        RequestBody body = builder.build();

        Request.Builder requestBuilder = new Request.Builder()
                                                .url(url)
                                                .post(body);
        if(fakeIp){
            requestBuilder.addHeader("X-FORWARDED-FOR", RandomUtils.getRandomIp());
        }
        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();
        return response.body();
    }

    public static String getStringFromPost(String url, Map<String, Object> param) throws IOException {
        return post(url,param).string();
    }

    public static byte[] getBytesFromPost(String url, Map<String, Object> param) throws IOException {
        return post(url,param).bytes();
    }

    public static InputStream getInputStreamFromPost(String url, Map<String, Object> param) throws IOException {
        return post(url,param).byteStream();
    }

    /**
     * @param url           文件地址
     * @param isSynchronous 是否同步     {@code true} 同步的  {@code false} 异步的
     * @return 下载到本地的绝对路径地址
     * @throws IOException
     * @author 郑明亮
     * @time 2017年3月19日 下午3:17:47
     * @description <p>下载文件  </p>
     */
    public static String getFileFromHttpData(String url, boolean isSynchronous) throws IOException {
        if (isSynchronous) {
            return getFileFromHttpDataBySyn(url, null);
        } else {
            return getFileFromHttpDataByAsyn(url, null);
        }

    }

    /**
     * @author 郑明亮
     * @time 2017年3月19日 下午4:59:41
     * @description <p> 文件绝对路径地址</p>
     */
    private static String fileAbsolutePath = null;

    /**
     * @param url
     * @param fileName 文件名（含扩展名）/路径+文件名（含扩展名）
     * @return 下载到本地的绝对路径地址
     * @throws IOException
     * @author 郑明亮
     * @time 2017年3月19日 下午3:17:07
     * @description <p>下载文件（Http同步请求，阻塞线程,只有在得到返回路径值时，程序下方代码才会执行）   </p>
     */
    public static String getFileFromHttpDataBySyn(final String url, final String fileName) throws IOException {


        final OkHttpClient client = getOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        int serverLoadTimes = 0;

        try {
            httpReLoad(url, client, request, fileName);
        } catch (Exception e) {
            e.printStackTrace();

            if (SocketTimeoutException.class.equals(e) && serverLoadTimes <= MAX_SERVER_LOAD_TIMES) {
                serverLoadTimes++;
                log.error("网络连接超时" + serverLoadTimes + "次");
                httpReLoad(url, client, request, fileName);
            } else {
                e.printStackTrace();
                log.error("连接超时" + MAX_SERVER_LOAD_TIMES + "次", e);
            }
        }
        System.out.println(fileAbsolutePath);

        return fileAbsolutePath;
    }

    public static String getFileFromHttpDataBySyn(final String url, final String fileName,String dir,Map<String,String>... headers ) throws IOException {


        final OkHttpClient client = getOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers[0]))
                .build();
        int serverLoadTimes = 0;
//		String fileName = fileName_;
        try {
            httpReLoad(url, client, request, fileName,dir);
        } catch (Exception e) {
            e.printStackTrace();

            if (SocketTimeoutException.class.equals(e) && serverLoadTimes <= MAX_SERVER_LOAD_TIMES) {
                serverLoadTimes++;
                log.error("网络连接超时" + serverLoadTimes + "次");
                httpReLoad(url, client, request, fileName);
            } else {
                e.printStackTrace();
                log.error("连接超时" + MAX_SERVER_LOAD_TIMES + "次", e);
            }
        }
        System.out.println(fileAbsolutePath);

        return fileAbsolutePath;
    }

    /**
     * @param url
     * @param client   OKHttpClient
     * @param request
     * @param fileName 文件名称
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     * @author 郑明亮
     * @time 2017年3月19日 下午6:20:08
     * @description <p>从网络获取文件，如果失败，需要重复执行的方法体   </p>
     */
    private static void httpReLoad(final String url, final OkHttpClient client,
                                   Request request, String fileName) throws IOException,
            UnsupportedEncodingException, FileNotFoundException {
        File file;
        Response response = client.newCall(request).execute();

        //如果不传入文件名的话，则截取URL的“/”后的字符串为文件名
        if (StringUtils.isEmpty(fileName)) {
            fileName = DataUtils.getFileNameFromHttp(response.headers());
            if (fileName == null) {//如果从Head中获取文件名失败，则从URL中进行截取名
                fileName = DataUtils.getFileNameFromUrl(url);
            }

            file = new File(fileName);
        } else if (!fileName.contains(".")) {//如果文件名不含“.”，则说明不包含扩展名，则改为截取URL中的文件名
            //如果从Head中获取文件名失败，则从URL中进行截取文件名
            fileName = fileName + DataUtils.getFileNameFromHttp(response.headers()) == null ? DataUtils.getFileNameFromUrl(url) : DataUtils.getFileNameFromHttp(response.headers());

            file = new File(fileName);
        } else {
            file = new File(fileName);
        }
        //如果文件夹不存在，则创建父级文件夹
//			FileUtils.createFile(file);
        try (FileOutputStream fos = new FileOutputStream(file);) {

            fos.write(response.body().bytes());
            fos.flush();
        }
        fileAbsolutePath = file.getAbsolutePath();
    }


    private static void httpReLoad(final String url, final OkHttpClient client,
                                   Request request, String fileName,String dir) throws IOException,
            UnsupportedEncodingException, FileNotFoundException {
        File pathFile = new File(dir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        String basePath = pathFile.getAbsolutePath() + File.separator;
        File file;
        Response response = client.newCall(request).execute();

        //如果不传入文件名的话，则截取URL的“/”后的字符串为文件名
        if (StringUtils.isEmpty(fileName)) {
            fileName = DataUtils.getFileNameFromHttp(response.headers());
            if (fileName == null) {//如果从Head中获取文件名失败，则从URL中进行截取名
                fileName = basePath + DataUtils.getFileNameFromUrl(url);
            }

            file = new File(fileName);
        } else if (!fileName.contains(".")) {//如果文件名不含“.”，则说明不包含扩展名，则改为截取URL中的文件名
            //如果从Head中获取文件名失败，则从URL中进行截取文件名
            fileName = basePath + DataUtils.getFileNameFromHttp(response.headers()) == null ? DataUtils.getFileNameFromUrl(url) : DataUtils.getFileNameFromHttp(response.headers());

            file = new File(fileName);
        } else {
            file = new File(basePath + fileName);
        }
        //如果文件夹不存在，则创建父级文件夹
//			FileUtils.createFile(file);
        try (FileOutputStream fos = new FileOutputStream(file);) {

            fos.write(response.body().bytes());
            fos.flush();
        }
        fileAbsolutePath = file.getAbsolutePath();
    }

    /**
     * @param url
     * @param fileName_ 文件名（含扩展名）/路径+文件名（含扩展名）
     * @return 下载到本地的绝对路径地址
     * @throws IOException
     * @author 郑明亮
     * @time 2017年3月19日 下午3:17:07
     * @description <p>下载文件（Http异步请求，方法尚未返回路径值时，程序下方代码仍会执行）   </p>
     */
    public static String getFileFromHttpDataByAsyn(final String url, final String fileName_) throws IOException {


        final OkHttpClient client = getOkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true).build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            int serverLoadTimes = 0;
            File file = null;
            String fileName = fileName_;

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //如果不传入文件名的话，则截取URL的“/”后的字符串为文件名
                if (StringUtils.isEmpty(fileName)) {
                    fileName = DataUtils.getFileNameFromHttp(response.headers());
                    if (fileName == null) {//如果从Head中获取文件名失败，则从URL中进行截取名
                        fileName = DataUtils.getFileNameFromUrl(url);
                    }

                    file = new File(fileName);
                } else if (!fileName.contains(".")) {//如果文件名不含“.”，则说明不包含扩展名，则改为截取URL中的文件名
                    //如果从Head中获取文件名失败，则从URL中进行截取文件名
                    fileName = fileName + DataUtils.getFileNameFromHttp(response.headers()) == null ? DataUtils.getFileNameFromUrl(url) : DataUtils.getFileNameFromHttp(response.headers());

                    file = new File(fileName);
                } else {
                    file = new File(fileName);
                }
                //如果文件夹不存在，则创建父级文件夹
//						FileUtils.createFile(file);
                try (FileOutputStream fos = new FileOutputStream(file)) {

                    fos.write(response.body().bytes());
                    fos.flush();
                }
                fileAbsolutePath = file.getAbsolutePath();
            }

            @Override
            public void onFailure(Call call, IOException e) {

                if (SocketTimeoutException.class.equals(e) && serverLoadTimes <= MAX_SERVER_LOAD_TIMES) {
                    serverLoadTimes++;
                    log.error("网络连接超时" + serverLoadTimes + "次");
                    client.newCall(call.request()).enqueue(this);
                } else {
                    e.printStackTrace();
                    log.error("连接超时" + MAX_SERVER_LOAD_TIMES + "次", e);
                }

            }
        });
        ;


        return fileAbsolutePath;
    }

    public static String getFileFromHttpDataByAsyn(final String url, final String fileName_, final String dir) throws IOException {

        final OkHttpClient client = getOkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true).build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            int serverLoadTimes = 0;
            File file = null;


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                File pathFile = null;
                if (StringUtils.isNotEmpty(dir)) {
                    pathFile = new File(dir);
                    if (!pathFile.exists()) {
                        pathFile.mkdirs();
                    }
                }
                String basePath =pathFile.getAbsolutePath() + File.separator;
                String fileName = basePath +fileName_;
                //如果不传入文件名的话，则截取URL的“/”后的字符串为文件名
                if (StringUtils.isEmpty(fileName)) {
                    fileName = DataUtils.getFileNameFromHttp(response.headers());
                    if (fileName == null) {//如果从Head中获取文件名失败，则从URL中进行截取名
                        fileName = basePath + DataUtils.getFileNameFromUrl(url);
                    }

                    file = new File(fileName);
                } else if (!fileName.contains(".")) {//如果文件名不含“.”，则说明不包含扩展名，则改为截取URL中的文件名
                    //如果从Head中获取文件名失败，则从URL中进行截取文件名
                    fileName = basePath + DataUtils.getFileNameFromHttp(response.headers()) == null ? DataUtils.getFileNameFromUrl(url) : DataUtils.getFileNameFromHttp(response.headers());

                    file = new File(fileName);
                } else {
                    file = new File(fileName);
                }
                //如果文件夹不存在，则创建父级文件夹
//						FileUtils.createFile(file);
                try (FileOutputStream fos = new FileOutputStream(file)) {

                    fos.write(response.body().bytes());
                    fos.flush();
                }
                fileAbsolutePath = file.getAbsolutePath();
            }

            @Override
            public void onFailure(Call call, IOException e) {

                if (SocketTimeoutException.class.equals(e) && serverLoadTimes <= MAX_SERVER_LOAD_TIMES) {
                    serverLoadTimes++;
                    log.error("网络连接超时" + serverLoadTimes + "次");
                    client.newCall(call.request()).enqueue(this);
                } else {
                    e.printStackTrace();
                    log.error("连接超时" + MAX_SERVER_LOAD_TIMES + "次", e);
                }

            }
        });
        ;


        return fileAbsolutePath;
    }


    /**
     * @param url    服务器地址
     * @param object 要发送的对象
     * @return
     * @throws IOException
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年4月25日 下午5:46:51
     * @description <p> post请求，仅发送RequestBody </P>
     */
    public static String sendRequestBody(String url, Object object) throws IOException {
        OkHttpClient client = getOkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, GsonTools.createJsonString(object));

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(body);

        if(fakeIp){
            builder.addHeader("X-REAL-IP",RandomUtils.getRandomIp());
            builder.addHeader("X-FORWARDED-FOR", RandomUtils.getRandomIp());
        }
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 以requestBody的形式发送
     * @param url  服务器地址
     * @param object  要发送的对象
     * @param header 请求头
     * @return
     * @throws IOException
     */
    public static String sendRequestBody(String url, Object object,Map<String,String> header) throws IOException {
        OkHttpClient client = getOkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, GsonTools.createJsonString(object));
        if(header == null){
            header = getDefaultHeaders();
        }
        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(body)
                .headers(Headers.of(header));

        //伪造IP头
        if(fakeIp){
            builder.addHeader("X-REAL-IP",RandomUtils.getRandomIp());
            builder.addHeader("X-FORWARDED-FOR", RandomUtils.getRandomIp());
        }
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static Map<String,String> getDefaultHeaders(){
        Map<String,String> header = Maps.newHashMap();
        header.put("User-Agent",RandomUtils.getRandomUserAgent());
        return header;
    }

    public static void main(String[] args) {
//		try {
//			 OkHttpClient client = new OkHttpClient();
//		        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//		        SysUser user = new SysUser("developerclub@126.com", "郑明亮", "123");
//		        RequestBody body = RequestBody.create(JSON, GsonTools.createJsonString(user));
//		        Request request = new Request.Builder()
//		                .url("http://localhost:8087/DeveloperClub/SysUserController/register")
////		                .header("User-Agent", "OkHttp Headers.java")
////		                .addHeader("Accept", "application/json; q=0.5")
//		                .post(body)
//		                .build();
//		    Response   response = client.newCall(request).execute();
//		    System.out.println(response.body().string());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

}
