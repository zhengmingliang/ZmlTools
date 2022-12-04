package top.wys.utils;

import org.junit.Test;
import sun.misc.BASE64Decoder;
import top.wys.utils.convert.GenericConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * Created by 郑明亮 on 2018/10/14 17:49.
 */
public class EncryptUtilsTest {

    String str = "123456";
    String str2 = "abc";
    String secret = "abc";

    @Test
    public void string2MD5() throws Exception {
        System.out.println("md5:" + EncryptUtils.md5(str));
        System.out.println("256:" + EncryptUtils.sha256(str));
        System.out.println("128:" + EncryptUtils.sha1(str));
        System.out.println("sha256_HMAC:" + EncryptUtils.sha256_HMAC(str, secret));
    }

    @Test
    public void md5() {
        System.out.println("1:" + EncryptUtils.md5(str));
        System.out.println("2:" + EncryptUtils.md5(str2));
    }

    @Test
    public void buildKeyPair() {
        try {
            String a = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIhIs/3wz/nod7Ff/0UMzyK4gRCjPLqSfYkxxtlLn8GEn5Tg9kgKEl+CBiVad3w1afgFivaTHHI7xCC9zyulFkKQ3Q5IuouBkaY2+hKUPDzRRer3RmxUcNM4e5IUfDwG//8Hh69Q0kEHyD22lXGvo/kQnoUyhH+RjZ1UVAJAzj7lAgMBAAECgYAVh26vsggY0Yl/Asw/qztZn837w93HF3cvYiaokxLErl/LVBJz5OtsHQ09f2IaxBFedfmy5CB9R0W/aly851JxrI8WAkx2W2FNllzhha01fmlNlOSumoiRF++JcbsAjDcrcIiR8eSVNuB6ymBCrx/FqhdX3+t/VUbSAFXYT9tsgQJBALsHurnovZS1qjCTl6pkNS0V5qio88SzYP7lzgq0eYGlvfupdlLX8/MrSdi4DherMTcutUcaTzgQU20uAI0EMyECQQC6il1Kdkw8Peeb0JZMHbs+cMCsbGATiAt4pfo1b/i9/BO0QnRgDqYcjt3J9Ux22dPYbDpDtMjMRNrAKFb4BJdFAkBMrdWTZOVc88IL2mcC98SJcII5wdL3YSeyOZto7icmzUH/zLFzM5CTsLq8/HDiqVArNJ4jwZia/q6Fg6e8KO2hAkB0EK1VLF/ox7e5GkK533Hmuu8XGWN6I5bHnbYd06qYQyTbbtHMBrFSaY4UH91Qwd3u9gAWqoCZoGnfT/o03V5lAkBqq8jZd2lHifey+9cf1hsHD5WQbjJKPPIb57CK08hn7vUlX5ePJ02Q8AhdZKETaW+EsqJWpNgsu5wPqsy2UynO";
            KeyPair keyPair = EncryptUtils.RSA.buildKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            System.out.println("私钥：" + new String(Base64Coder.encode(privateKey.getEncoded())));
            System.out.println("公钥：" + new String(Base64Coder.encode(publicKey.getEncoded())));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYdmxBr0WKhGyAxBjLbF0KNSg2ae421J45eKtoPJjZ6FqmMO8dkXtNRtDCN7TCri6ecBIxJR6N936etsAQRjN+UflnI9LeaM30xnnLlRy3JaeCcBvVILtCp1Bg43/a1NOa2/LFJut8e8hkoJRPI0Nv0cN2oyMyjuq/22H7i5AaYwIDAQAB";
    public static String privateKeyString="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJh2bEGvRYqEbIDEGMtsXQo1KDZp7jbUnjl4q2g8mNnoWqYw7x2Re01G0MI3tMKuLp5wEjElHo33fp62wBBGM35R+Wcj0t5ozfTGecuVHLclp4JwG9Ugu0KnUGDjf9rU05rb8sUm63x7yGSglE8jQ2/Rw3ajIzKO6r/bYfuLkBpjAgMBAAECgYAOqSST1GTdgwpj/3da6FqyBpaNVPZhM2Tbpezlox4Xpi5BWOdFcp3mum6Mnoq1RcaZ2bEGJMmhYTE91dj2N6nvzrZYsugIWlp6yxYWrZMiDiLrmp37lejpJiJHpbCWg2AXxXivjoSiAgS9LLYiKmnALQSrTu5XS17cUUqaMX4T0QJBAPh/ROTTTEUwTitracddShOLdo9coOAQ+VhP7wcyrZtbjWJ8MKV9LV11vwOd6Z1VvOyEpcffoaly51r4516/cUUCQQCdEN2LQbvNODYuenI6io0cToJuiNf2toLeQhlORv5t7ZDx7feTDZt2HgiXOOZaoPsCS+ID5nN/a1jHn8boelOHAkBuH2B1k5oCM2UA4uoOmq4AOVbCM4drpLoLQ+kmpHPBHHJsiaK5Q979wvdyb+GegudlXWdmSxmGUVBrxVArceWNAkAZJv1DcrhfadQLZ2Jpi4yDbvMmPlXPVXTpoM4qoeXWtseSZphR/UjbBn47sUn6U3M+7+X21+3BBvDFrwmMayJ7AkEA5zHC0O1eHkaPRpqNqKPLHYEVsJRAoTt3r1jQZfyBF54NYk+i1dhwl+VNo/JNZydleLemnDyVCXOYNykzuHPIaw==";

    @Test
    public void RSA() throws Exception {
        String content = "H";
//        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK9p6r0xXI7ieu3cXJ+kp2ZoKBmuD4VTq/cQPEe5y/HVOl/kImEeLII8XQZ1Dqy4GNjW0Ci5rej7MZBDV11UFMVrQKwPXRvBpuYMujzD8v5v0KNqpDFgK6M3hOs4M4oB8CXQ680LaaGxSgoZNcAsGxeacgHwr4h36hq8FJS1a/WbAgMBAAECgYAyZv2zFQ7iY4Ms+4eFu5r6Gu2Cy3fcOHmOCRU5CtLmDv2x9exG+ZOSbGPSaiooSNGi5L51JT0qV5sbN65Pu8wnnr1mnsgvszs0R19sc+E0/HiWAYSNsQk/xCZRC93k3GX9tmwxm2FqVP35K6CWS/5gvgGX35FOrXgmTpyi/2/UwQJBAPOgJLNeqHyZFuvN0Mf/f65cEDbA+0pNnGLy7iXSwzyF5vV/XA31UJXCoLBa2Umxndju43rU/6cv3lfqnaVD6jsCQQC4UtEs8hYSs2lg9UmxyJshyuTumhHHoZL8PPqU3B++be2cMkUH6IcEqJ8Ev2olYipRQcjyt7cfIUJEcs9vEAwhAkEA6+tgnvJDZqU5NRAR5hrdohM1AFDRB5swngFx7N60gZ+JkKLMmhe8+cmHoj/Xuy1tFe4AC6rIBzarsadZEfk6kQJAfnbr55+Nf3UzNv/0eRM/RNc1bXOuAT8dXwzjjGDfMsOvhDyFWmCyyoJ64EIIrO+GQ0wUeFuOO8CQ+++d3QCwoQJANZYD6UmEkfkrz/dEwQvpL2ovYB1Nm/XxjP3f2meb8v7xkUIdluaVaPacqIl9AfM+et4E1wC+a2pKmCrrCfu9Cw==";
//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvaeq9MVyO4nrt3FyfpKdmaCgZrg+FU6v3EDxHucvx1Tpf5CJhHiyCPF0GdQ6suBjY1tAoua3o+zGQQ1ddVBTFa0CsD10bwabmDLo8w/L+b9CjaqQxYCujN4TrODOKAfAl0OvNC2mhsUoKGTXALBsXmnIB8K+Id+oavBSUtWv1mwIDAQAB";
//        EncryptUtils.RSA.getPublicKey(publicKey);
//        KeyPair keyPair = EncryptUtils.RSA.buildKeyPair();
//        PrivateKey privateKey = keyPair.getPrivate();
//        PublicKey publicKey = keyPair.getPublic();

        String encryptString =new String(EncryptUtils.RSA.encrypt(content.getBytes(), EncryptUtils.RSA.getPublicKey(publicKeyString)));
        System.out.println("encryptString:"+encryptString);
        String decryptString =new String(EncryptUtils.RSA.decrypt(encryptString.getBytes(), EncryptUtils.RSA.getPrivateKey(privateKeyString)));
        System.out.println("decryptString:"+decryptString);
    }

    @Test
    public void aes() {
        EncryptUtils.AES aes = EncryptUtils.AES.newInstance("alianga",128);

        String encrypt = aes.encrypt("https://alianga.com");
        System.out.println(encrypt);
        System.out.println(Base64.getDecoder().decode(encrypt));
        System.out.println(aes.decrypt(encrypt));

        aes = EncryptUtils.AES.newInstance("alianga",192);
        encrypt = aes.encrypt("https://alianga.com");
        System.out.println(encrypt);
        System.out.println(Base64.getDecoder().decode(encrypt));
        System.out.println(aes.decrypt(encrypt));

        aes = EncryptUtils.AES.newInstance("alianga",256);
        encrypt = aes.encrypt("https://alianga.com");
        System.out.println(encrypt);
        System.out.println(Base64.getDecoder().decode(encrypt));
        System.out.println(aes.decrypt(encrypt));
    }
    @Test
    public void des() {
        EncryptUtils.DES des = EncryptUtils.DES.newInstance("alianga");

        String encrypt = des.encrypt("https://alianga.com");
        System.out.println(encrypt);
        System.out.println(Base64.getDecoder().decode(encrypt));
        System.out.println(des.decrypt(encrypt));

    }

    @Test
    public void base64Decode() {
//        String base64 = FileUtils.readTxtFile(new File("D:\\data\\JetBrains\\IntelliJIdea\\2020.3.4\\config\\scratches\\1.txt"));
        String base64 = EncryptUtils.base64EncodeWithPrefix(new File("C:\\Users\\zml\\Pictures\\icon\\excel\\p50.svg"));
        System.out.println(base64);
        File path = EncryptUtils.base64DecodeFile(base64);
        System.out.println(path.toString());

    }


}