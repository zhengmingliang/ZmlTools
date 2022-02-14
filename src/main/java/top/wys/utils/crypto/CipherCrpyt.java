package top.wys.utils.crypto;
/**
 * Created by 郑明亮 on 2021/11/28 14:48.
 */

/**
 * <ol>
 *  2021/11/28 14:48 <br>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;

/**
 * 透过Cipher来实现加解密。
 *
 * @author Magic Len
 */
public abstract class CipherCrpyt extends Crypt {

    // -----内容变量-----
    /**
     * 加解密的密钥。
     */
    protected Key key;
    /**
     * 初始化向量(IV, Initialization Vector)。
     */
    protected IvParameterSpec iv;
    /**
     * Cipher 组件。
     */
    protected Cipher cipher;

    // -----组件方法-----
    /**
     * 加/解密资料。
     *
     * @param inputData 传入输入的资料流
     * @param outputData 传入输出的资料流
     * @param listener 传入监听者组件
     * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
     */
    protected void crypt(final InputStream inputData, final OutputStream outputData, final CryptListener listener) throws IOException {
        final int totalBytes = inputData.available();

        if (listener != null) {
            listener.onStarted(totalBytes);
        }

        long sum = 0;
        try (final CipherOutputStream cos = new CipherOutputStream(outputData, cipher)) {
            int c;
            final byte[] buffer = new byte[BUFFER_SIZE];
            while ((c = inputData.read(buffer)) >= 0) {
                cos.write(buffer, 0, c);
                if (c > 0) {
                    sum += c;
                }
                if (listener != null) {
                    if (!listener.onRunning(sum, (sum > totalBytes) ? -1 : totalBytes)) {
                        break;
                    }
                }
            }
            inputData.close();
            cos.flush();
        }
        if (listener != null) {
            listener.onFinished(sum, totalBytes);
        }
    }

    /**
     * 加密资料。
     *
     * @param inputData 传入要加密的资料流
     * @param outputData 传入已加密的资料流
     * @param listener 传入监听者组件
     * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
     */
    @Override
    public void encrypt(final InputStream inputData, final OutputStream outputData, final CryptListener listener) throws IOException {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            crypt(inputData, outputData, listener);
        } catch (final IOException ex) {
            throw ex;
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 解密資料。
     *
     * @param inputData 傳入要解密的資料流
     * @param outputData 傳入已解密的資料流
     * @param listener 傳入監聽者组件
     * @throws IOException 当输入输出处理时发生问题，会抛出这个异常
     */
    @Override
    public void decrypt(final InputStream inputData, final OutputStream outputData, final CryptListener listener) throws IOException {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            crypt(inputData, outputData, listener);
        } catch (final IOException ex) {
            throw ex;
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}