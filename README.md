# ZmlTools

ZmlTools 是一个面向 Java 应用的通用工具库，集中提供数据转换、字符串处理、日期时间、文件与流、集合、JSON、HTTP、加密、随机数据、反射、系统信息、线程池和文件监听等常用能力。

> 当前项目版本：`1.4.5`
> Maven 坐标：`top.wuyongshi:ZmlTools`
> License：Apache License 2.0

[![Maven Central](https://img.shields.io/maven-central/v/top.wuyongshi/ZmlTools?style=flat-square)](https://search.maven.org/artifact/top.wuyongshi/ZmlTools)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## 目录

- [1. 环境要求](#1-环境要求)
- [2. 引入项目](#2-引入项目)
- [3. 快速开始](#3-快速开始)
- [4. 功能总览](#4-功能总览)
- [5. 数据类型转换](#5-数据类型转换)
- [6. 字符串、数组和集合](#6-字符串数组和集合)
- [7. 数字和日期时间](#7-数字和日期时间)
- [8. 文件、目录和 IO](#8-文件目录和-io)
- [9. JSON 转换](#9-json-转换)
- [10. HTTP、Cookie 和资源](#10-httpcookie-和资源)
- [11. CSV 文件](#11-csv-文件)
- [12. 加密、摘要和 Base64](#12-加密摘要和-base64)
- [13. 随机数据和 ID](#13-随机数据和-id)
- [14. 反射、校验和系统信息](#14-反射校验和系统信息)
- [15. 线程池](#15-线程池)
- [16. 文件监听](#16-文件监听)
- [17. 其他工具](#17-其他工具)
- [18. 构建与测试](#18-构建与测试)
- [19. 使用注意事项](#19-使用注意事项)
- [20. 源码和示例](#20-源码和示例)

---

## 1. 环境要求

- JDK 8 及以上。
- 项目源码以 Java 8 为编译目标。
- 使用 HTTP、JSON、CSV 或图片相关模块时，需要同时保留项目对应的传递依赖。
- 运行测试使用 JUnit 4。

## 2. 引入项目

### 2.1 Maven

```xml
<!-- https://mvnrepository.com/artifact/top.wuyongshi/ZmlTools -->
<dependency>
    <groupId>top.wuyongshi</groupId>
    <artifactId>ZmlTools</artifactId>
    <version>${release-version}</version>
</dependency>
```

如果使用尚未发布的源码版本，可以在项目根目录执行：

```bash
mvn install
```

### 2.2 Gradle

```groovy
implementation 'top.wuyongshi:ZmlTools:1.4.5'
```

### 2.3 主要依赖

项目已经在 `pom.xml` 中声明了常用依赖，主要包括：

| 能力 | 依赖 |
| --- | --- |
| JSON | Gson、Fastjson、Fastjson2 |
| HTTP | OkHttp |
| CSV | Apache Commons CSV |
| 集合 | Guava |
| 日志 | SLF4J、Logback |
| 编译期代码生成 | Lombok |
| HTML 解析 | Jsoup |

如果宿主应用已经自行管理这些依赖，建议统一版本，避免同一依赖出现多个版本。

## 3. 快速开始

工具类大多是静态方法，不需要创建实例：

```java
import top.wys.utils.DateUtils;
import top.wys.utils.FileUtils;
import top.wys.utils.StringUtils;
import top.wys.utils.convert.ConvertUtils;

import java.io.File;

public class QuickStart {
    public static void main(String[] args) throws Exception {
        boolean enabled = ConvertUtils.toBoolean("yes");
        int count = ConvertUtils.toInt("12.8");
        String text = ConvertUtils.toNoneNullString(null);
        String now = DateUtils.getNowDateTime();

        boolean blank = StringUtils.isBlank("  ");
        File file = new File("target/example.txt");
        FileUtils.createFile(file);
        FileUtils.writeFile(file.getPath(), "hello", false);

        System.out.println(enabled); // true
        System.out.println(count);   // 12
        System.out.println(text);    // ""
        System.out.println(now);     // yyyy-MM-dd HH:mm:ss
        System.out.println(blank);   // true
    }
}
```

## 4. 功能总览

| 包 | 主要内容 |
| --- | --- |
| `top.wys.utils` | 字符串、日期、文件、HTTP、JSON、加密、系统、随机数据等基础工具 |
| `top.wys.utils.convert` | 对象、数字、布尔、日期和二进制转换 |
| `top.wys.utils.collection` | 数组、集合、Map、布尔值处理 |
| `top.wys.utils.math` | 数字运算和数字字符串处理 |
| `top.wys.utils.io` | 资源、文件类型、属性文件处理 |
| `top.wys.utils.io.monitor` | 基于 JDK WatchService 的文件监听 |
| `top.wys.utils.http` | Cookie、下载、HTTP 回调和 SSL 辅助类 |
| `top.wys.utils.crypto` | AES、DES 等底层加密实现 |
| `top.wys.utils.image` | 验证码、图片和 GIF 相关工具 |
| `top.wys.utils.thread` | 线程池创建、执行和关闭 |
| `top.wys.utils.reflect` / `jdk` | 字段访问、Unsafe 和 JDK 相关辅助类 |
| `top.wys.utils.valid` | 参数和状态校验 |

## 5. 数据类型转换

核心类：`top.wys.utils.convert.ConvertUtils`。

### 5.1 字符串和空值处理

```java
import top.wys.utils.convert.ConvertUtils;

String a = ConvertUtils.toString(null);                    // null
String b = ConvertUtils.toString(null, "default");         // default
String c = ConvertUtils.toNoneNullString(null);             // ""
String d = ConvertUtils.toNoneNullString("NULL", "N/A");   // N/A
String e = ConvertUtils.toNoneEmptyString("", "N/A");      // N/A
String f = ConvertUtils.toNoneEmptyString("value", "N/A");// value
```

`toNoneNullString` 和 `toNoneEmptyString` 都会把大小写不敏感的字符串 `"null"` 当作无效值处理。

### 5.2 布尔和数字转换

```java
boolean b1 = ConvertUtils.toBoolean("true");
boolean b2 = ConvertUtils.toBoolean("YES");
boolean b3 = ConvertUtils.toBoolean(1, 1, "yes");

Integer i1 = ConvertUtils.toInteger("12.9", 0);  // 12
int i2 = ConvertUtils.toInt("yes");              // 1
long l1 = ConvertUtils.toLongValue("true");      // 1
Double d1 = ConvertUtils.toDouble("3.14", 0D);   // 3.14
```

默认情况下，以下文本会被识别为真值：`ok`、`y`、`yes`、`t`、`true`、`1`，且忽略大小写。

转换方法的失败行为不同：

- 带 `defaultValue` 的包装类型方法通常返回默认值。
- 不带默认值的部分方法可能返回 `null`、`0`，或抛出 `NumberFormatException`。
- 调用外部输入转换前，建议使用带默认值的重载并对业务范围进行校验。

### 5.3 字节和二进制

```java
byte[] bytes = {1, 2, 3, 4};
int value = ConvertUtils.toInt(bytes);       // 0x01020304
int unsigned = ConvertUtils.toInt((byte) -1); // 255

String binaryByte = ConvertUtils.toBinaryString((byte) 5); // 00000101
String binaryInt = ConvertUtils.toBinaryString(5);          // 32 位
String binaryLong = ConvertUtils.toBinaryString(5L);        // 64 位
```

`toInt(byte[])` 按 4 个字节、无符号、大端序拼接；传入数组应至少包含 4 个元素。

### 5.4 日期对象转换

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

Date date1 = ConvertUtils.toDate("2024-01-02");
Date date2 = ConvertUtils.toDate("2024-01-02 12:30:45");
Date date3 = ConvertUtils.toDate(LocalDate.of(2024, 1, 2));
Date date4 = ConvertUtils.toDate(LocalDateTime.now());
Date date5 = ConvertUtils.toDate(null, new Date());
```

同时支持 `Date`、`Calendar`、`LocalDate`、`LocalDateTime`、10 位秒级时间戳、13 位毫秒级时间戳和若干常见日期字符串格式。

## 6. 字符串、数组和集合

### 6.1 `StringUtils`

`top.wys.utils.StringUtils` 提供空值判断、大小写、截取、路径和模板处理等方法：

```java
import top.wys.utils.StringUtils;

StringUtils.isEmpty(null);               // true
StringUtils.isEmpty("null");             // true
StringUtils.isBlank(" \t\n");            // true
StringUtils.isNotBlank(" text ");        // true

StringUtils.substring("abcdef", -2);     // "ef"
StringUtils.substring("abcdef", 1, 4);   // "bcd"
StringUtils.substringBefore("a/b/c", "/");// "a"
StringUtils.substringAfterLast("a/b/c", "/");// "c"
StringUtils.substringBetween("a[body]b", "[", "]");// "body"

StringUtils.upperFirstLetter("hello");   // Hello
StringUtils.lowerFirstLetter("Hello");   // hello
StringUtils.reverse("abc");              // cba
StringUtils.cleanValue("\"value\"");     // value
StringUtils.lenientFormat("name=%s, age=%s", "Tom", 18);
```

常用路径和集合字符串方法：

```java
String normalized = StringUtils.cleanPath("a/../b/./c");
boolean same = StringUtils.pathEquals("a/./b", "a/b");
String csv = StringUtils.collectionToDelimitedString(
        java.util.Arrays.asList("a", "b", "c"), ",");
String[] parts = StringUtils.delimitedListToStringArray("a,b,c", ",");
```

> `StringUtils.isEmpty(Object)` 会把 `null`、空字符串以及字符串 `"null"` 判定为空；如果只需要判断空字符串和 `null`，请使用 `hasLength` 或标准 Java 判断。

### 6.2 数组、集合和 Map

```java
import top.wys.utils.Objects;
import top.wys.utils.collection.ArrayUtils;
import top.wys.utils.collection.Collections;
import top.wys.utils.collection.Maps;

ArrayUtils.isEmpty((String[]) null);       // true
ArrayUtils.getLength(new int[]{1, 2, 3});  // 3
ArrayUtils.deepEquals0(
        new int[]{1, 2}, new int[]{1, 2}); // true

Collections.isEmpty(java.util.Collections.emptyList());
Collections.getFirst(java.util.Arrays.asList("a", "b"));
Collections.getLast(java.util.Arrays.asList("a", "b"));
Collections.findFirst(list, item -> item.startsWith("a"));

java.util.HashMap<String, Integer> map = Maps.newHashMapWithExpectedSize(100);
java.util.LinkedHashMap<String, Integer> ordered = Maps.newLinkedHashMap();
java.util.concurrent.ConcurrentMap<String, Integer> concurrent = Maps.newConcurrentMap();

Objects.isEmpty(java.util.Optional.empty());
Objects.unwrapOptional(java.util.Optional.of("value"));
Objects.deepEquals(new int[]{1, 2}, new int[]{1, 2});
```

`Maps` 还提供 `TreeMap`、`EnumMap`、`IdentityHashMap`、`ConcurrentMap` 等工厂方法；`Collections` 提供集合删除、查找、转换、计数和类型查找方法。

## 7. 数字和日期时间

### 7.1 `NumberUtils`

```java
import top.wys.utils.NumberUtils;

NumberUtils.isPositiveInteger("123");
NumberUtils.isPositiveDecimal("12.30");
NumberUtils.isCoinAmount("12.30");

String truncated = NumberUtils.cutByPoint("12.3456", 2);       // 12.34
String roundedUp = NumberUtils.cutByPointUp("12.341", 2);      // 12.35
String sum = NumberUtils.add("1.20", "2.30", 2);              // 3.5
String total = NumberUtils.add(2, "1.20", "2.30", "3");       // 6.5
String difference = NumberUtils.subtract("10", "3.2", 2);    // 6.8
String rmb = NumberUtils.amount2rmb("1234.56");
```

`NumberUtils` 还包含十六进制转换、大小比较、平均值、精度获取、四舍五入和中文金额转换等方法。金额计算优先使用 `BigDecimal` 语义，不建议使用 `double` 直接进行金融计算。

### 7.2 `DateUtils`

```java
import top.wys.utils.DateUtils;

String now = DateUtils.getNowDateTime();
String today = DateUtils.getDateString(new java.util.Date());
String formatted = DateUtils.getStringByPattern(
        new java.util.Date(), "yyyy/MM/dd HH:mm:ss");

java.util.Date tomorrow = DateUtils.getNextDay();
java.util.Date nextWeek = DateUtils.getNextDate(new java.util.Date(), 7);
java.util.Date dayBegin = DateUtils.getBeginOfDay();
java.util.Date dayEnd = DateUtils.getEndOfDay();

long hours = DateUtils.getTimeGapsInHours(start, end);
long minutes = DateUtils.getTimeGapsInMinutes(start, end);
String elapsed = DateUtils.pastTimes(90_500L); // 1分钟500毫秒的中文描述

java.time.LocalDateTime local = DateUtils.toLocalDateTime(new java.util.Date());
java.util.Date date = DateUtils.toDate(local);
```

常用常量：

- `DateUtils.DATE_PATTERN`：`yyyy-MM-dd`
- `DateUtils.DATE_TIME_PATTERN`：`yyyy-MM-dd HH:mm:ss`
- `DateUtils.ONE_SECOND`、`ONE_MINUTE`、`ONE_HOUR`、`ONE_DAY`

注意：`ONE_MONTH` 按 30 天计算，`ONE_YEAR` 按 365 天计算；涉及自然月、闰年或夏令时的业务应使用 `Calendar` 或 `java.time` 的日历运算方法。

## 8. 文件、目录和 IO

### 8.1 文件读写

```java
import top.wys.utils.FileUtils;

java.io.File file = new java.io.File("target/data/example.txt");
FileUtils.createFile(file);
FileUtils.writeFile(file.getPath(), "第一行\n", false);
FileUtils.writeFile(file.getPath(), "第二行\n", true);

String content = FileUtils.readTxtFile(file, "UTF-8");
long size = FileUtils.getFileSize(file);
String readableSize = FileUtils.getSize(size);
String contentType = FileUtils.getContentType(file);
```

常用能力包括：

- `createFile`、`makeDir`：创建文件和父目录。
- `writeFile`、`readTxtFile`：文本写入和读取。
- `nioTransferCopyFile`：使用 NIO 通道复制文件。
- `deleteDir`：递归删除文件或目录。
- `fileToZip`：将目录内容压缩成 ZIP。
- `cutFile`、`cutFileByLine`：按字节或行切割大文件。
- `getFileNameFromUrl`、`getFileNameFromHttp`：获取下载文件名。
- `getFilename`、`getFilenameExtension`、`stripFilenameExtension`：处理路径文件名。
- `cleanPath`、`applyRelativePath`：规范化和拼接资源路径。

读取流时建议使用 try-with-resources：

```java
try (java.io.InputStream input = FileUtils.class
        .getResourceAsStream("/example.txt")) {
    String text = top.wys.utils.IOUtils.is2String(input);
}
```

### 8.2 `IOUtils`

```java
import top.wys.utils.IOUtils;

byte[] bytes = IOUtils.isToBytes(inputStream);
String text = IOUtils.is2String(inputStream); // 默认 UTF-8
String text2 = IOUtils.is2String(inputStream, "GBK");

IOUtils.flush(writer);
IOUtils.close(inputStream, reader, writer);
```

`IOUtils.close` 会捕获关闭过程中的异常并记录日志；如果业务需要感知关闭失败，请直接使用 Java 流 API。

### 8.3 资源和文件类型

```java
import top.wys.utils.io.FileType;
import top.wys.utils.io.ResourceUtils;

java.net.URL url = ResourceUtils.getURL("classpath:sysconfig.properties");
java.io.File file = ResourceUtils.getFile("file:/tmp/example.txt");
boolean isJar = ResourceUtils.isJarURL(url);

String suffix = FileType.getSuffixByMimeType("image/png");
String mime = FileType.getMimeTypeBySuffix("png");
```

`ResourceUtils` 支持普通文件路径、`file:`、`classpath:` 以及 JAR/WAR 资源判断。JAR 内资源不一定能转换为可写的本地 `File`，需要按 URL 或流读取。

## 9. JSON 转换

项目同时提供 Gson 和 Fastjson 两套封装。

### 9.1 Gson：`GsonTools`

```java
import top.wys.utils.GsonTools;

public class User {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

User user = new User();
user.setName("Tom");
user.setAge(18);

String json = GsonTools.createJsonString(user);
User parsed = GsonTools.getBeanFromJson(json, User.class);
java.util.List<User> users = GsonTools.getList("[{\"name\":\"Tom\",\"age\":18}]", User.class);
java.util.Map<String, Object> map = GsonTools.getMapFromJson(json);
```

常用方法：

- `createJsonString`：对象转 JSON。
- `getBeanFromJson`：JSON 转对象，支持 `Class` 和 `TypeToken`。
- `getList`、`getListFromJson`：JSON 转对象列表。
- `getStrings`：JSON 转字符串列表。
- `getMaps`、`getMapFromJson`：JSON 转 Map 集合。

### 9.2 Fastjson：`FastJsonTools`

```java
import com.alibaba.fastjson.TypeReference;
import top.wys.utils.FastJsonTools;

String json = FastJsonTools.createJsonString(user);
User parsed = FastJsonTools.createJsonBean(json, User.class);
java.util.List<User> list = FastJsonTools.createJsonToListBean(jsonArray, User.class);

java.util.Map<String, Object> map = FastJsonTools.createJsonToMap(json);
java.util.Map<String, Object> beanMap = FastJsonTools.createBeanToMap(user);

java.util.List<User> complex = FastJsonTools.createJsonBean(
        jsonArray,
        new TypeReference<java.util.List<User>>() {}
);
```

`createJsonToMap(json, true)` 和 `createBeanToMap(bean, true)` 会尝试移除空值字段。解析不可信 JSON 前应根据业务限制输入大小和对象结构。

## 10. HTTP、Cookie 和资源

### 10.1 GET 请求

```java
import top.wys.utils.HttpUtils;

String body = HttpUtils.get("https://example.com/api");

java.util.Map<String, Object> params = new java.util.HashMap<>();
params.put("keyword", "Java 工具");
params.put("page", 1);
String result = HttpUtils.get("https://example.com/search", params);
```

获取完整响应时应关闭响应对象：

```java
try (okhttp3.Response response = HttpUtils.getResponse("https://example.com")) {
    if (!response.isSuccessful()) {
        throw new java.io.IOException("HTTP status: " + response.code());
    }
    String body = response.body() == null ? "" : response.body().string();
}
```

GET 参数使用 `HttpUtils.getRequestParamString` 生成，参数值会进行 URL 编码；数组和 `List` 会展开为多个同名参数。

### 10.2 HTTP 客户端、代理和 Cookie

```java
HttpUtils.setHttpProxy("127.0.0.1", 7890);
HttpUtils.setCookieJar(new top.wys.utils.http.CookieJarImpl());
okhttp3.OkHttpClient client = HttpUtils.getOkHttpClient();
```

相关类：

- `CookieJarImpl`：OkHttp CookieJar 实现。
- `InMemoryCookieStore`：内存 Cookie 存储。
- `Cookies`：从 Servlet 请求中读取 Cookie。
- `UploadInfo`：描述 multipart 上传文件。
- `HttpCallBack`：异步 HTTP 回调接口。
- `SSLSocketClient`：SSL 客户端辅助类。

### 10.3 HTTPS 安全说明

当前 `HttpUtils` 的默认配置会启用忽略 SSL 证书校验的逻辑，适合测试环境，不适合生产环境。生产环境请使用严格证书校验的 `OkHttpClient`，不要因为请求失败而全局关闭 hostname 或证书验证。

`ignoreSNI()` 会修改 JVM 全局系统属性 `jsse.enableSNIExtension`，应谨慎使用；`fakeIp` 只是设置请求头，不会改变真实网络来源。

## 11. CSV 文件

核心类：`top.wys.utils.CSVUtils`。

### 11.1 写入 CSV

```java
import top.wys.utils.CSVUtils;

java.io.File output = new java.io.File("target/users.csv");
java.util.List<java.util.List<String>> rows = java.util.Arrays.asList(
        java.util.Arrays.asList("Tom", "18"),
        java.util.Arrays.asList("Lucy", "20")
);
CSVUtils.write(output, rows, "name", "age");
```

### 11.2 读取 CSV

```java
try (org.apache.commons.csv.CSVParser parser = CSVUtils.getCSVParser(output)) {
    for (org.apache.commons.csv.CSVRecord record : parser) {
        String name = record.get("name");
        String age = record.get("age");
        System.out.println(name + ": " + age);
    }
}
```

默认格式 `CSVUtils.DEFAULT_CSVFORMAT`：

- 使用 Oracle 风格 CSV 格式。
- 第一行作为表头。
- 忽略表头大小写。
- 允许缺失列名。
- 忽略空行并自动 trim。

如果输入文件不是 UTF-8，可以使用带 `encoding` 的 `getCSVParser` 或 `getCSVPrinter` 重载。

## 12. 加密、摘要和 Base64

### 12.1 摘要和 Base64

```java
import top.wys.utils.EncryptUtils;

String md5 = EncryptUtils.md5("hello");
String sha256 = EncryptUtils.sha256("hello");
String sha512 = EncryptUtils.sha512("hello");
String encoded = EncryptUtils.base64Encode("hello");
String decoded = EncryptUtils.base64Decode(encoded);
```

摘要是不可逆的完整性校验，不是加密。密码存储不要直接使用单次 MD5，应使用专门的密码哈希算法和随机盐。

文件 Base64：

```java
java.io.File image = new java.io.File("image.png");
String dataUrl = EncryptUtils.base64EncodeWithPrefix(image);
java.io.File restored = EncryptUtils.base64DecodeFile(dataUrl, "target/restored");
```

### 12.2 AES / DES

```java
import top.wys.utils.EncryptUtils;

EncryptUtils.AES aes = EncryptUtils.AES.newInstance(
        "strong-password", EncryptUtils.AES.BIT_256);
String encrypted = aes.encrypt("hello");
String decrypted = aes.decrypt(encrypted);

EncryptUtils.DES des = EncryptUtils.DES.newInstance("password");
String desEncrypted = des.encrypt("hello");
String desDecrypted = des.decrypt(desEncrypted);
```

AES 支持 128、192、256 位密钥；不要把密码硬编码到源码或日志中。`zmlEncode` / `zmlDecode` 是项目自定义的可逆编码方案，解码时必须使用相同的时间参数，不应替代标准密码学算法。

### 12.3 RSA

```java
java.security.KeyPair pair = EncryptUtils.RSA.buildKeyPair();
byte[] encrypted = EncryptUtils.RSA.encrypt(
        "hello".getBytes(java.nio.charset.StandardCharsets.UTF_8),
        pair.getPublic());
byte[] plain = EncryptUtils.RSA.decrypt(encrypted, pair.getPrivate());
```

RSA 适合加密短数据或加密对称密钥；大文件应使用混合加密。项目中的 RSA 示例使用 1024 位密钥，生产系统建议根据安全策略使用更高强度密钥和明确的填充方案。

## 13. 随机数据和 ID

### 13.1 随机测试数据

```java
import top.wys.utils.RandomUtils;

String ip = RandomUtils.getRandomIp();
String phone = RandomUtils.getRandomTel();
String email = RandomUtils.getRandomEmail(6, 10);
String code = RandomUtils.getRandomCode(6);
String numericCode = RandomUtils.getRandomNumCode(6);
String uuid = RandomUtils.getUUID();
String userAgent = RandomUtils.getRandomUserAgent();
String idCard = RandomUtils.getRandomIdCard(18, 60);
```

这些方法适合测试数据、演示数据和模拟请求，不适合生成密码、Token、支付随机数或其他安全敏感随机值。安全随机数请使用 `SecureRandom`。

### 13.2 Snowflake ID

```java
import top.wys.utils.SnowFlakeIdWorker;

long id1 = SnowFlakeIdWorker.INSTANCE.nextId();
String id2 = SnowFlakeIdWorker.INSTANCE.nextStringId();

SnowFlakeIdWorker worker = new SnowFlakeIdWorker(
        1, // workerId，0~31
        2, // datacenterId，0~31
        0  // 初始序列
);
long id3 = worker.nextId();
```

ID 结构为：

- 1 位符号位。
- 41 位毫秒时间戳差值。
- 5 位数据中心 ID。
- 5 位工作机器 ID。
- 12 位毫秒内序列号。

部署多个实例时，应确保 `workerId` 和 `datacenterId` 的组合不会重复；系统时钟回拨时，`nextId()` 会拒绝生成 ID 并抛出异常。

## 14. 反射、校验和系统信息

### 14.1 反射

```java
import top.wys.utils.ReflectionUtils;

java.lang.reflect.Field field = ReflectionUtils.getField(User.class, "name");
java.util.List<java.lang.reflect.Field> fields =
        ReflectionUtils.getFields(User.class, true);

Object value = ReflectionUtils.methodInvoke(user, "getName");
```

`ReflectionUtils` 还提供字段名、父类字段、Getter/Setter、方法查找和 Bean 属性处理方法。反射调用可能抛出 `IllegalAccessException`、`InvocationTargetException` 或 `NoSuchMethodException`，应在边界层处理。

`UnsafeUtils` 直接封装 `sun.misc.Unsafe`，属于 JDK 内部 API，除非明确了解内存偏移、对象布局和模块限制，否则不要在业务代码中使用。

### 14.2 参数校验

```java
import top.wys.utils.Assert;
import top.wys.utils.valid.Preconditions;

Assert.notNull(user, "user 不能为空");
Assert.hasText(user.getName(), "name 不能为空");
Assert.isTrue(user.getAge() >= 0, "age 不能小于 0");

Preconditions.checkArgument(user.getAge() < 150, "age=%s 非法", user.getAge());
```

`Assert` 失败时通常抛出 `IllegalArgumentException`；`Preconditions` 提供更接近 Guava 风格的参数、状态和非空检查。

### 14.3 系统信息

```java
import top.wys.utils.Systems;

String os = Systems.osName();
String host = Systems.HOST_NAME;
String arch = Systems.OS_ARCH;
long pid = Systems.getPid();
String tmp = Systems.tmpDirName();
String dump = Systems.threadDump();

String value = Systems.getProperty("app.name", "default-app");
long size = Systems.parseSize("cache.size", "128m");
long timeout = Systems.parseDuration("request.timeout", "2s");
```

`Systems` 还提供 JVM 参数、调试器检测、属性文件加载、线程转储、操作系统判断和系统尺寸/时长解析。

### 14.4 端口检测

```java
import top.wys.utils.PortsUtils;

boolean open = PortsUtils.isOpen("127.0.0.1", 8080);
java.util.List<Integer> ports = PortsUtils.scanPorts(
        "127.0.0.1", "8080,8443,9000-9010");
```

端口扫描只应对自己拥有或明确获准测试的主机执行，并控制扫描范围、超时时间和并发数。

## 15. 线程池

```java
import top.wys.utils.thread.ExecutorServiceUtil;

java.util.concurrent.ExecutorService pool =
        ExecutorServiceUtil.newExecutorService();
try {
    pool.submit(() -> System.out.println("run task"));
} finally {
    ExecutorServiceUtil.shutdown(pool);
}

java.util.concurrent.ScheduledExecutorService scheduler =
        ExecutorServiceUtil.newSingleScheduledExecutorService();
scheduler.schedule(() -> System.out.println("delayed"),
        1, java.util.concurrent.TimeUnit.SECONDS);

ExecutorServiceUtil.execute(() -> System.out.println("default pool"));
boolean slept = ExecutorServiceUtil.sleep(100);
```

线程工具默认创建守护线程。长期运行的服务仍应显式管理线程池生命周期；临时线程池使用完毕后调用 `shutdown` 或直接调用标准 Executor API 关闭。

## 16. 文件监听

文件监听位于 `top.wys.utils.io.monitor`，基于 JDK `WatchService`，支持创建、修改、删除和溢出事件。

### 16.1 基础监听

```java
import top.wys.utils.io.monitor.BaseMonitor;
import top.wys.utils.io.monitor.FileMonitor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;

Path directory = Paths.get("/tmp/config");

BaseMonitor watcher = new BaseMonitor() {
    @Override
    public void onCreate(WatchEvent<?> event, Path currentPath) {
        System.out.println("创建：" + resolve(event, currentPath));
    }

    @Override
    public void onModify(WatchEvent<?> event, Path currentPath) {
        System.out.println("修改：" + resolve(event, currentPath));
    }

    @Override
    public void onDelete(WatchEvent<?> event, Path currentPath) {
        System.out.println("删除：" + resolve(event, currentPath));
    }

    @Override
    public void onOverflow(WatchEvent<?> event, Path currentPath) {
        System.out.println("事件溢出：" + currentPath);
    }

    private Path resolve(WatchEvent<?> event, Path currentPath) {
        return currentPath.resolve((Path) event.context());
    }
};

FileMonitor monitor = FileMonitor.createAll(directory, watcher);
monitor.start(); // 后台线程监听

// 应用退出或不再使用时关闭
Runtime.getRuntime().addShutdownHook(new Thread(monitor::close));
```

`currentPath` 是事件所在的目录，`event.context()` 通常是相对于该目录的 `Path`。处理 `OVERFLOW` 时，context 不保证是 `Path`，不要直接强制转换。

### 16.2 监听单个文件

```java
Path file = Paths.get("/tmp/config/application.yml");

FileMonitor monitor = FileMonitor.create(
        file,
        FileMonitor.ENTRY_CREATE,
        FileMonitor.ENTRY_MODIFY,
        FileMonitor.ENTRY_DELETE
).setWatcher(watcher);

monitor.start();
```

监听单个文件时，底层实际监听的是文件所在目录，再根据目标文件过滤事件；目标文件删除后重新创建仍可收到事件。

### 16.3 递归监听目录

```java
FileMonitor monitor = FileMonitor.create(
        Paths.get("/tmp/project"),
        3,
        FileMonitor.EVENTS_ALL
).setWatcher(watcher);

monitor.start();
```

`maxDepth` 含义：

- `maxDepth <= 1`：只监听当前目录。
- `maxDepth = 2`：监听当前目录和直接子目录。
- `maxDepth = 3`：监听当前目录和下两层目录。

监听过程中创建的子目录会按剩余深度自动注册。

### 16.4 合并重复修改事件

一次保存操作可能产生多次 `ENTRY_MODIFY`。可以使用延迟合并：

```java
FileMonitor monitor = FileMonitor.createAll(
        Paths.get("/tmp/config/application.yml"),
        watcher,
        200L
);
monitor.start();
```

上例表示同一个文件连续修改时重新计时，文件停止修改 200 毫秒后只回调一次 `onModify`。创建、删除和溢出事件会立即转发。

也可以显式组合递归监听和 `DelayMonitor`：

```java
import top.wys.utils.io.monitor.impl.DelayMonitor;

FileMonitor monitor = FileMonitor.create(
        Paths.get("/tmp/project"),
        3,
        FileMonitor.EVENTS_ALL
).setWatcher(new DelayMonitor(watcher, 200L));
monitor.start();
```

### 16.5 生命周期

1. `FileMonitor.create(...)` 创建并初始化监听服务。
2. `start()` 启动后台监听线程，不阻塞当前线程。
3. `watch()` 在当前线程中阻塞监听。
4. 使用结束后调用 `close()`，释放 `WatchService`、线程和延迟任务。
5. `MonitorException` 用于包装监听初始化、路径注册和监听过程异常。

## 17. 其他工具

### 17.1 HTML 和 XPath

`JsoupUtils` 在 Jsoup 基础上提供 CSS 内容查找、父节点查找和 XPath 选择：

```java
org.jsoup.nodes.Document document =
        org.jsoup.Jsoup.parse("<div><span>Hello</span></div>");
org.jsoup.nodes.Element element =
        top.wys.utils.JsoupUtils.findElement(document, "span", "Hello");
org.w3c.dom.NodeList nodes =
        top.wys.utils.JsoupUtils.selectXpath(element, ".//text()");
```

### 17.2 图片和验证码

`top.wys.utils.image` 包包含：

- `Captcha`、`SpecCaptcha`、`GifCaptcha`：验证码生成。
- `Encoder`、`GifEncoder`：图片/GIF 编码辅助。
- `Quant`：图片颜色量化。
- `Randoms`：图片模块使用的随机数据。

具体验证码样式和输出方法请以对应类的 Javadoc 为准。

### 17.3 编码检测

```java
String encoding = top.wys.utils.EncodingDetect
        .getJavaEncode("target/example.txt");
```

编码检测是启发式判断，对内容过短、混合编码或二进制文件可能不准确；读取重要文件时应优先明确编码。

### 17.4 异常和堆栈

```java
String stack = top.wys.utils.ExceptionUtils.getStackTrace(exception);
Throwable root = top.wys.utils.ExceptionUtils.getRootCause(exception);
```

## 18. 构建与测试

### 18.1 编译和测试

```bash
# 编译并执行 Maven 测试生命周期
mvn test -DskipTests=false

# 默认项目属性 skipTests=true，以下命令只编译测试代码但不运行测试
mvn test

# 打包并安装到本地 Maven 仓库
mvn install -Dmaven.javadoc.skip=true
```

项目的 `pom.xml` 当前默认配置了 `skipTests=true`。CI 或本地验证时建议显式传入 `-DskipTests=false`。

### 18.2 运行指定测试

```bash
mvn test -DskipTests=false \
    -Dtest=ConvertUtilsTest,SnowFlakeIdWorkerTest
```

### 18.3 Javadoc

发布配置包含源码包和 Javadoc 包生成。若本机 Maven 无法找到 `javadoc` 命令，请先正确设置 `JAVA_HOME`，或在仅验证编译时使用：

```bash
mvn install -Dmaven.javadoc.skip=true
```

## 19. 使用注意事项

### 19.1 空值与异常

- 不同工具类对无效输入的处理方式不同：返回 `null`、默认值、空集合，或抛出异常。
- 对外部输入优先使用带默认值的转换方法，并在转换后进行范围校验。
- `FileUtils` 的部分历史方法会记录日志或打印堆栈，不要把它们当作事务性文件操作使用。
- `ResourceUtils.getFile` 只能把文件系统资源转换为 `File`；JAR 内资源通常应通过 `URL` 或流读取。

### 19.2 资源释放

- `InputStream`、`Reader`、`CSVParser`、`CSVPrinter`、OkHttp `Response` 使用后必须关闭。
- `FileMonitor`、`DelayMonitor`、`ExecutorService` 使用结束后必须关闭。
- 网络请求不要长时间持有 response body；读取完成后及时释放连接。

### 19.3 安全

- 生产环境不要默认信任所有 HTTPS 证书。
- 不要使用 MD5、SHA-1 作为密码存储算法。
- 不要使用 `RandomUtils` 生成安全 Token 或密码。
- `UnsafeUtils` 只能用于明确了解 JDK 内部实现和内存模型的场景。
- `PortsUtils` 仅用于获得授权的资产检测。
- 解析外部 JSON、HTML、文件时应限制输入大小，必要时设置超时和资源上限。

### 19.4 线程安全

- 大部分工具方法是无状态静态方法，可以直接调用。
- `GsonTools` 使用全局 Gson 实例，替换全局实例会影响整个进程。
- `HttpUtils` 的 Cookie、代理和部分配置是静态共享状态，多个业务模块使用时应统一管理。
- `SnowFlakeIdWorker#nextId` 已同步，但不同实例的机器 ID 仍需由部署系统保证唯一。

## 20. 源码和示例

- 源码目录：`src/main/java`
- 测试目录：`src/test/java`
- 测试资源：`src/test/resources`
- Maven 配置：`pom.xml`
- 文件监听测试：`src/test/java/top/wys/utils/io/monitor/FileMonitorTest.java`

如果某个方法的 README 示例与当前版本 Javadoc 不一致，应以当前源码签名和测试用例为准，并欢迎提交修正文档或示例。
