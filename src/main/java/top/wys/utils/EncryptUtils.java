package top.wys.utils;/**
 * Created by 郑明亮 on 2018/10/4 20:44.
 */


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

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
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String UTF_8 = DEFAULT_CHARSET;
    private static final String RSA = "RSA";
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


static class RSA{
    private static Charset charset = Charset.forName(DEFAULT_CHARSET);

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
