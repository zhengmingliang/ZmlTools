package top.wys.utils.crypto;
/**
 * Created by 郑明亮 on 2021/12/2 15:01.
 */


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * <ol>
 *  2021/12/2 15:01 <br>
 *DES CBC 64位元之加密/解密，使用PKCS5填充方式。
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 */

public class DESCrypt extends CipherCrpyt {

    // -----建构子-----
    /**
     * 建构子。
     *
     * @param key 传入8位元组(64位元)的密钥
     * @param iv 传入8位元组(64位元)的初始化向量
     */
    public DESCrypt(final byte[] key, final byte[] iv) {
        if (key == null || iv == null) {
            throw new RuntimeException("Need a key and an initialization vector to construct an DESCrypt object!");
        }

        final int keyLength = key.length;
        if (keyLength != 8) {
            throw new RuntimeException("The DES key must be 8 bytes(64 bits)!");
        }

        final int ivLength = iv.length;
        if (ivLength != 8) {
            throw new RuntimeException("The IV must be 8 bytes(64 bits)!");
        }

        this.key = new SecretKeySpec(key, "DES");
        this.iv = new IvParameterSpec(iv);

        init();
    }

    // -----组件方法-----
    /**
     * 初始化。
     */
    private void init() {
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
