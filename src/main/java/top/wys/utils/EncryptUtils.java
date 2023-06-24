package top.wys.utils;/**
 * Created by 郑明亮 on 2018/10/4 20:44.
 */


import com.google.common.collect.Lists;
import top.wys.utils.crypto.AESCrypt;
import top.wys.utils.crypto.AwaruaTiger;
import top.wys.utils.crypto.Crypt;
import top.wys.utils.crypto.DESCrypt;
import top.wys.utils.io.FileType;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 郑明亮   @email 1072307340@qq.com
 * @version 1.0
 * @time 2018/10/4 20:44
 */
public class EncryptUtils {

    private EncryptUtils() {
        throw new UnsupportedOperationException("you can not instantiate me !");
    }

    // sha-256加密
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String SHA_512 = "SHA-512";
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_1 = "SHA1";
    private static final String MD5 = "MD5";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final String UTF_8 = DEFAULT_CHARSET.name();
    private static final String RSA = "RSA";
    
    static {
        fixKeyLength();
    }
    /**
     *  <p>自定义对称加密</p>
     * <ol>
     * <li>利用base64编码的原理；</li>
     * <li>根据时间戳生成随机位置和可还原密钥，将密钥字符插入到生成的随机位置当中，解密则采用相反的方式</li>
     * </ol>
     * @author 郑明亮
     * @time 2018年10月4日20:43:12
     * @param orignString 需要加密的原字符串
     * @param time 时间戳
     * @return 编码后的字符串
     */
    public static String zmlEncode(String orignString, long time){
        Long anotherTime = Long.valueOf(new StringBuilder(time + "").reverse().toString());
        String encoding = RandomUtils.encoding(anotherTime);
        String encodeString = Base64Coder.encodeString(orignString);
        StringBuilder sb = new StringBuilder(encodeString);
        char[] chars = encoding.toCharArray();
        int[] position = getPosition(time, 2);
        for (int i = 0; i < position.length; i++) {
            if(position[i] >= sb.length()){
                sb.insert(i,chars[i]);
            }else{
                sb.insert(position[i],chars[i]);
            }
        }
        return sb.toString();
    }


    /**
     *
     * <p>自定义对称解密，time字段必需为encryptString加密时的时间戳<p>
     * @author 郑明亮
     * @time 2018年10月4日20:42:47
     * @param encryptString 要解码的字符串
     * @param time 时间戳
     * @return 解码后的字符串
     */
    public static String zmlDecode(String encryptString, long time){
        StringBuilder sb = new StringBuilder(encryptString);

        int[] position = getPosition(time, 2);
        for (int i = position.length-1; i >= 0; i--) {
            int length = sb.length();
            if (position[i] >= length) {
                sb.deleteCharAt(i);
            }else{
                sb.deleteCharAt(position[i]);
            }

        }
        String encodeString = Base64Coder.decodeString(sb.toString());
        return encodeString;
    }

    /**
     * 将数字根据step分割成数组
     * @param number 整数
     * @param step 每几位分成一个数组元素
     * @return
     */
    static int[] getPosition(Long number,int step){
        StringBuilder builder = new StringBuilder(number+"");
        int len = builder.length();
        if(step>=len){
            return new int[]{Integer.parseInt(number+"")};
        }
        int size = (len%step==0?len/step:len/step +1);
        int[] positions = new int[size];
        int start = 0,end=start+step;
        for (int i = 0; i < size; i++) {
            if(start == end){
                positions[i] = Integer.parseInt(builder.substring(start));
            }else{
                positions[i] = Integer.parseInt(builder.substring(start,end));
            }
            start +=step;
            end = start+step;
            if(start >= len){
                break;
            }
            if(end >=len){
                end = len -1;
            }
        }
        return positions;
    }

    /**
     * MD5加密
     * @param string 待加密字符
     * @return 加密后的字符，大写字符
     */
    public static String md5(String string) {
        return messageDigest(string,MD5);
    }

    /**
     * base64加密
     * @param text 待加密字符
     * @return 加密后的字符
     */
    public static String base64Encode(String text) {
        if (text == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64加密
     * @param file 待加密文件
     * @return 加密后的字符
     */
    public static String base64Encode(File file) {
        if (file == null || !(file.isFile() && file.canRead())) {
            return null;
        }
        try {
            return Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * base64加密
     * @param bytes 待加密字节
     * @return 加密后的字符
     */
    public static byte[] base64Encode(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    /**
     * @param text 待解密字符串
     * @return 加密后的字符
     */
    public static String base64Decode(String text) {

        if (text == null) {
            return null;
        }
        byte[] decode = Base64.getDecoder().decode(text);
        return new String(decode, StandardCharsets.UTF_8);

    }

    /**
     * 将文件转换为base64字符串,并拼接上图片标识的base64前缀,如 <code>data:image/png;base64,</code>
     * @param file 待加密字节
     * @return 加密后的字符
     */
    public static String base64EncodeWithPrefix(File file) {
        String encode = base64Encode(file);
        if (encode == null) {
            return null;
        }
        encode = "data:" + FileUtils.getContentType(file) + ";base64," + encode;
        return encode;
    }

    /**
     * base64解码文件
     *
     * @param text     待解密字符串
     * @param filePath 文件路径
     * @return 加密后的字符
     */
    public static File base64DecodeFile(String text,String filePath) {

        if (text == null) {
            return null;
        }

        String suffix = "";
        if (text.startsWith("data:")) {
            int base64Position = text.indexOf("base64");
            String contentType = text.substring(5, base64Position - 1);
            suffix = FileType.getSuffixByMimeType(contentType);
            text = text.substring(base64Position + 7);
        }
        File path = new File(filePath);
        File file ;
        if (path.isDirectory()) {
            if (!path.exists()) {
                path.mkdirs();
            }
            file = new File(filePath + File.separator + System.currentTimeMillis() + "." +  suffix);
        }else {
            file = path;
        }
        try(FileOutputStream fos = new FileOutputStream(file)) {
            byte[] decode = Base64.getDecoder().decode(text);
            for (int i = 0; i < decode.length; i++) {
                if (decode[i] < 0) {
                    decode[i] +=256;
                }

            }
           /* RenderedImage image = ImageIO.read(new ByteArrayInputStream(decode));;
            ImageIO.write(image,suffix,file);*/
            fos.write(decode);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return file;
    }
    /**
     * @param text 待解密字符串
     * @return 加密后的字符
     */
    public static File base64DecodeFile(String text) {

        return base64DecodeFile(text,Systems.USER_DIR);

    }


    /**
     * @param source 需要加密的文本（明文密码）
     * @param salt   作料、盐值
     * @return getEncryptionPasswd
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
     * @return getEncryptionPasswd
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
     * SHA-256方式加密
     * @param str
     * @return
     */
    public static String sha1(String str) {
        return messageDigest(str,SHA_1);
    }

    /**
     * SHA-256方式加密
     * @param str
     * @return
     */
    public static String sha256(String str) {
        return messageDigest(str,SHA_256);
    }

    /**
     * SHA-512方式加密
     * @param str
     * @return
     */
    public static String sha512(String str) {
        return messageDigest(str,SHA_512);
    }


    private static String messageDigest(String str,String type){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance(type);
            byte[] hash = messageDigest.digest(str.getBytes(UTF_8));
            encdeStr = bytesToHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(String.format("Huh, %s should be supported?", type), e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        return encdeStr;
    }

    public static class DES extends Encrypt{
        /**
         * 加解密对象
         */
        Crypt crypt ;

        private DES(Crypt crypt) {
            this.crypt = crypt;
        }

        /**
         *
         * @param password 加密文本的密码
         * @return
         */
        public static DES newInstance(String password){
            return newInstance(password,null);
        }

        /**
         * @param password 加密文本的密码
         * @param iv 加密文本的初始向量
         * @return
         */
        public static DES newInstance(String password,String iv){
            final byte[] keyByte,ivByte;

            keyByte = getCRC64Hash(password.getBytes(DEFAULT_CHARSET));

            if(iv != null){
                ivByte = getCRC64Hash(iv.getBytes(DEFAULT_CHARSET));
            } else {
                ivByte = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
            }
            return new DES(new DESCrypt(keyByte, ivByte));
        }



        @Override
        public Crypt getCrypt() {
            return crypt;
        }
    }



    public static class AES extends Encrypt{

        /**
         * 128位
         */
        public static int BIT_128 = 128;
        /**
         * 192位
         */
        public static int BIT_192 = 192;
        /**
         * 256位
         */
        public static int BIT_256 = 256; 
         
        /**
         * 加解密对象
         */
        Crypt crypt ;

        public AES(Crypt crypt) {
            this.crypt = crypt;
        }

        /**
         * @param password 加解密文本的密码
         * @param 
         * @return
         */
        public static AES newInstance(String password){
            return newInstance(password,BIT_256);
        }

        /**
         *
         * @param password 加密文本的密码
         * @param bit     传入密钥长度，可以是128、192、256(位元)
         * @return
         */
        public static AES newInstance(String password,int bit){
            return newInstance(password,bit,null);
        }

        /**
         * @param password 加密文本的密码
         * @param bit   传入密钥长度，可以是128、192、256(位元)   
         * @param iv 加密文本的初始向量
         * @return
         */
        public static AES newInstance(String password,int bit,String iv){

            final byte[] keyByte,ivByte;
            
            switch (bit){
                case 128:
                    keyByte = getHash("MD5", password);
                    break;
                case 192:
                    keyByte = getHash("TIGER", password);
                    break;
                case 256:
                    keyByte = getSha256Hash(password);
                    break;
                default:
                    throw new IllegalStateException("The key must be 16 bytes(128 bits), 24 bytes(192 bits) or 32 bytes(256 bits) " + bit);
            }
           



            if(iv != null){
                ivByte = getHash("MD5",iv.getBytes(DEFAULT_CHARSET));
            } else {
                ivByte = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            }

            return new AES(new AESCrypt(keyByte, ivByte));
        }



        @Override
        public Crypt getCrypt() {
            return crypt;
        }
    }

    /**
     * 修正由于jdk低版本key的长度限制问题
     */
    public static void fixKeyLength() {
        String errorString = "Failed manually overriding key-length permissions.";
        int newMaxKeyLength;
        try {
            if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
                Class c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
                Constructor con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissionCollection = con.newInstance();
                Field f = c.getDeclaredField("all_allowed");
                f.setAccessible(true);
                f.setBoolean(allPermissionCollection, true);

                c = Class.forName("javax.crypto.CryptoPermissions");
                con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissions = con.newInstance();
                f = c.getDeclaredField("perms");
                f.setAccessible(true);
                ((Map) f.get(allPermissions)).put("*", allPermissionCollection);

                c = Class.forName("javax.crypto.JceSecurityManager");
                f = c.getDeclaredField("defaultPolicy");
                f.setAccessible(true);
                Field mf = Field.class.getDeclaredField("modifiers");
                mf.setAccessible(true);
                mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                f.set(null, allPermissions);

                newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
            }
        } catch (Exception e) {
            throw new RuntimeException(errorString, e);
        }
        if (newMaxKeyLength < 256)
            throw new RuntimeException(errorString); // hack failed
    }

    private static abstract class Encrypt{

        /**
         * 
         * @return 获取加解密对象
         */
        public abstract Crypt getCrypt(); 

        /**
         * 加密文字。
         *
         * @param str 传入要加密的文字
         * @return 传回加密后的文字
         */
        public String encrypt(final String str) {
            try {
                return encrypt(str, null);
            } catch (final Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        /**
         * 加密文字。
         *
         * @param str 传入要加密的文字
         * @param listener 传入监听者组件
         * @return 传回加密后的文字
         */
        public String encrypt(final String str, final Crypt.CryptListener listener) {
            try {
                final byte[] data = encrypt(str.getBytes("UTF-8"), listener);
                return Base64.getEncoder().encodeToString(data);
            } catch (final Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        /**
         * 加密内容。
         *
         * @param data 要加密的内容
         * @return 返回加密后的内容
         */
        public byte[] encrypt(final byte[] data) {
            try {
                return encrypt(data, null);
            } catch (final Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }

        /**
         * 加密内容。
         *
         * @param data 传入要加密的内容
         * @param listener 传入监听者组件
         * @return 返回加密后的内容
         */
        public byte[] encrypt(final byte[] data, final Crypt.CryptListener listener) {
            try {
                return getCrypt().encrypt(data, listener);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        /**
         * 加密资料。
         *
         * @param inputFile 传入要加密的文件
         * @param outputFile 传入已加密的文件
         * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
         */
        public void encrypt(final File inputFile, final File outputFile) throws IOException {
            encrypt(inputFile, outputFile, null);
        }

        /**
         * 加密资料。
         *
         * @param inputFile 传入要加密的文件
         * @param outputFile 传入已加密完成的文件
         * @param listener 传入监听者组件
         * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
         */
        public void encrypt(final File inputFile, final File outputFile, final Crypt.CryptListener listener) throws IOException {
            try (final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile)); final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile))) {
                encrypt(bis, bos, listener);
                bos.flush();
            }
        }

        /**
         * 加密内容。
         *
         * @param inputData 传入要加密的内容流
         * @param outputData 传入已加密的内容流
         * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
         */
        public void encrypt(final InputStream inputData, final OutputStream outputData) throws IOException {
            encrypt(inputData, outputData, null);
        }

        /**
         * 加密资料。
         *
         * @param inputData 传入要加密的内容流
         * @param outputData 传入已加密的内容流
         * @param listener 传入监听者组件
         * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
         */
        public void encrypt(final InputStream inputData, final OutputStream outputData, final Crypt.CryptListener listener) throws IOException {
            getCrypt().encrypt(inputData, outputData, listener);
        }

        /**
         * 解密文字。
         *
         * @param str 传入要解密的文字
         * @return 传回解密后的文字
         */
        public String decrypt(final String str) {
            try {
                return decrypt(str, null);
            } catch (final Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }

        /**
         * 解密文字。
         *
         * @param str 传入要解密的文字
         * @param listener 传入监听者组件
         * @return 传回解密后的文字
         */
        public String decrypt(final String str, final Crypt.CryptListener listener) {
            try {
                final byte[] data = decrypt(Base64.getDecoder().decode(str), listener);
                return new String(data, DEFAULT_CHARSET);
            } catch (final Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }

        /**
         * 解密文本。
         *
         * @param data 传入要解密的文本
         * @return 传回解密后的文本
         */
        public byte[] decrypt(final byte[] data) {
            try {
                return decrypt(data, null);
            } catch (final Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }

        /**
         * 解密文本。
         *
         * @param data 传入要解密的数据
         * @param listener 传入监听者组件
         * @return 传回解密后的文本
         */
        public byte[] decrypt(final byte[] data, final Crypt.CryptListener listener) {
            try {
                return getCrypt().decrypt(data, listener);
            } catch (final Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }

        /**
         * 解密文本。
         *
         * @param inputFile 传入解密的档案
         * @param outputFile 传入已解密完成的档案
         * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
         */
        public void decrypt(final File inputFile, final File outputFile) throws IOException {
            decrypt(inputFile, outputFile, null);
        }

        /**
         * 解密文本。
         *
         * @param inputFile 传入解密的档案
         * @param outputFile 传入已解密完成的档案
         * @param listener 传入监听者组件
         * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
         */
        public void decrypt(final File inputFile, final File outputFile, final Crypt.CryptListener listener) throws IOException {
            try (final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile)); final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile))) {
                decrypt(bis, bos, listener);
                bos.flush();
            }
        }

        /**
         * 解密文本。
         *
         * @param inputData 传入要解密的数据流
         * @param outputData 传入已解密的数据流
         * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
         */
        public void decrypt(final InputStream inputData, final OutputStream outputData) throws IOException {
            getCrypt().decrypt(inputData, outputData, null);
        }

        /**
         * 解密文本。
         *
         * @param inputData 传入要解密的数据流
         * @param outputData 传入已解密的数据流
         * @param listener 传入监听者组件
         * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
         */
        public void decrypt(final InputStream inputData, final OutputStream outputData, final Crypt.CryptListener listener) throws IOException {
            getCrypt().decrypt(inputData, outputData, listener);
        }

    }

    // -----类別常亮-----
    private static final long POLY64REV = 0x42F0E1EBA9EA3693L;
    private static final long[] LOOKUPTABLE = new long[256];
    static {
        final long mask1 = 1L << 63;
        long mask2 = 1;
        for (int i = 1; i < 64; ++i) {
            mask2 = (mask2 << 1) + 1;
        }
        for (int i = 0; i < 256; ++i) {
            long v = i;
            for (int j = 0; j < 64; ++j) {
                if ((v & mask1) == 0) {
                    v = v << 1;
                } else {
                    v = v << 1;
                    v = v ^ POLY64REV;
                }
            }
            LOOKUPTABLE[i] = v & mask2;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(10);
        list.add(17);
        System.out.println(list.stream().filter(item -> item > 4).map(item -> "" + item).collect(Collectors.toList()));
    }


    /**
     * 获取文本的Hash值。
     *
     * @param algorithm 传入哈希算法名称
     * @param data 传入要哈希的文本
     * @return 传回哈希后的内容
     */
    private static byte[] getHash(final String algorithm, final String data) {
        return getHash(algorithm, data.getBytes(DEFAULT_CHARSET));
    }

    /**
     * 获取文本的Hash值。
     *
     * @param algorithm 传入哈希算法名称
     * @param data 传入要哈希的文本
     * @return 传回哈希后的内容
     */
    private static byte[] getHash(final String algorithm, final byte[] data) {
        try {
            if(data == null){
                return null;
            }
            switch (algorithm.toUpperCase()) {
                case "CRC64":
                    return getCRC64Hash(data);
                case "TIGER":
                    return new AwaruaTiger().computeHash(data);
                default:
                    final MessageDigest digest = MessageDigest.getInstance(algorithm);
                    digest.update(data);
                    return digest.digest();
            }

        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    /**
     * 取得数据的CRC64哈希值。
     *
     * @param data 传入要哈希的数据
     * @return 传回哈希后数据內容
     */
    public static byte[] getSha256Hash(final String data) {
        return getHash(SHA_256,data);
    }


    /**
     * 取得数据的CRC64哈希值。
     *
     * @param data 传入要哈希的数据
     * @return 传回哈希后数据內容
     */
    public static byte[] getCRC64Hash(final byte[] data) {
        if (data == null) {
            return null;
        }
        long sum = ~0;
        for (final byte b : data) {
            final int lookupidx = (int) (((sum >>> 56) ^ b) & 0xff);
            sum = (sum << 8) ^ LOOKUPTABLE[lookupidx];
        }
        sum = sum ^ ~0;
        final byte[] crc64 = new byte[8];
        crc64[0] = (byte) (sum >>> 56);
        crc64[1] = (byte) ((sum << 8) >>> 56);
        crc64[2] = (byte) ((sum << 16) >>> 56);
        crc64[3] = (byte) ((sum << 24) >>> 56);
        crc64[4] = (byte) ((sum << 32) >>> 56);
        crc64[5] = (byte) ((sum << 40) >>> 56);
        crc64[6] = (byte) ((sum << 48) >>> 56);
        crc64[7] = (byte) ((sum << 56) >>> 56);
        return crc64;
    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }

        for (int i=0; i < src.length; i++) {
            stringBuilder.append(Integer.toString( ( src[i] & 0xff ) + 0x100, 16).substring( 1 ));
        }
        return stringBuilder.toString();
    }
    /**
     * 将byte数组转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * sha56_Hmac加密
     * @param message
     * @param secret
     * @return
     */
    public static String sha256_HMAC(String message, String secret){
        byte[] bs = new byte[0];
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), HMAC_SHA256);
            mac.init(keySpec);
            bs = mac.doFinal(message.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return byte2Hex(bs);
    }


public static class RSA{
    private static Charset charset = DEFAULT_CHARSET;

     public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
         final int keySize = 1024;
         KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
         keyPairGenerator.initialize(keySize);
         return keyPairGenerator.genKeyPair();
     }

     //公钥加密
     public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
         Cipher cipher = Cipher.getInstance(RSA);//java默认"RSA"="RSA/ECB/PKCS1Padding"
         cipher.init(Cipher.ENCRYPT_MODE, publicKey);
         return cipher.doFinal(content);
     }

     //私钥解密
     public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
         Cipher cipher = Cipher.getInstance(RSA);
         cipher.init(Cipher.DECRYPT_MODE, privateKey);
         return cipher.doFinal(content);
     }

     public static String encrypt(String content, String publicKey){
        String encodeString = "";
         try {
             byte[] encrypt = encrypt(content.getBytes(), getPublicKey(publicKey));
             encodeString =  new String(encrypt);
         } catch (Exception e) {
             e.printStackTrace();
         }

         return encodeString;
     }

    public static String decrypt(String content, String privateKey){
        String decodeString = "";
        try {
            byte[] decrypt = decrypt(content.getBytes(), getPrivateKey(privateKey));
            decodeString = new String(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeString;
    }


    /**
     * 将base64编码后的公钥字符串转成PublicKey实例
     * @param publicKey  公钥字符串
     * @return 公钥实例
     */
     public static PublicKey getPublicKey(String publicKey){
         try {
             byte[] keyBytes= Base64Coder.decode(publicKey);
             X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
             KeyFactory keyFactory=KeyFactory.getInstance(RSA);
             return keyFactory.generatePublic(keySpec);
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         } catch (InvalidKeySpecException e) {
             e.printStackTrace();
         }
         return null;
     }


    /**
     * 将base64编码后的私钥字符串转成PrivateKey实例
     * @param privateKey 私钥字符串
     * @return 私钥实例
     */
     public static PrivateKey getPrivateKey(String privateKey) {
         try {
             byte[] keyBytes = Base64Coder.decode(privateKey);
             PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
             KeyFactory keyFactory=KeyFactory.getInstance(RSA);
             return keyFactory.generatePrivate(keySpec);
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         } catch (InvalidKeySpecException e) {
             e.printStackTrace();
         }
         return null;
     }
 }


}
