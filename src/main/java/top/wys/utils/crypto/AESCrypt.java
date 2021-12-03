package top.wys.utils.crypto;
/**
 * Created by 郑明亮 on 2021/11/28 14:58.
 */

/**
 * <ol>
 *  2021/11/28 14:58 <br>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 */
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES CBC 128/192/256位元之加密/解密，使用PKCS5填充方式。
 *
 */
public class AESCrypt extends CipherCrpyt {

    // -----建构子-----
    /**
     * 建构子。
     *
     * @param key 传入16位元组(128位元)、24位元组(192位元)或是32位元组(256位元)的密钥
     * @param iv 传入16位元组(128位元)的初始化向量
     */
    public AESCrypt(final byte[] key, final byte[] iv) {
        if (key == null || iv == null) {
            throw new RuntimeException("Need a key and an initialization vector to construct an AESCrypt object!");
        }

        final int keyLength = key.length;
        if (keyLength != 16 && keyLength != 24 && keyLength != 32) {
            throw new RuntimeException("The AES key must be 16 bytes(128 bits), 24 bytes(192 bits) or 32 bytes(256 bits)!");
        }

        final int ivLength = iv.length;
        if (ivLength != 16) {
            throw new RuntimeException("The IV must be 16 bytes(128 bits)!");
        }

        this.key = new SecretKeySpec(key, "AES");
        this.iv = new IvParameterSpec(iv);

        init();
    }

    // -----组件方法-----
    /**
     * 初始化。
     */
    private void init() {
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}