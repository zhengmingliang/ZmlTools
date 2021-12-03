package top.wys.utils.crypto;
/**
 * Created by 郑明亮 on 2021/12/2 14:36.
 */

/**
 * <ol>
 *  2021/12/2 14:36 <br>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 加/解密抽象类别，可监听加解密过程
 *
 * @author Magic Len
 */
public abstract class Crypt {

    // -----类别介面-----
    public static interface CryptListener {

        /**
         * 开始进行加/解密。
         *
         * @param totalBytes 要处理的内容大小
         */
        public void onStarted(final long totalBytes);

        /**
         * 正在进行加/解密。
         *
         * @param currentBytes 目前已处理的内容大小
         * @param totalBytes 全部的内容大小
         * @return 传回是否要继续处理内容
         */
        public boolean onRunning(final long currentBytes, final long totalBytes);

        /**
         * 加/解密結束。
         *
         * @param finishedBytes 已处理的内容大小
         * @param totalBytes 全部的内容大小
         */
        public void onFinished(final long finishedBytes, final long totalBytes);
    }

    // -----类别常数-----
    /**
     *缓冲空间大小。
     */
    public static final int BUFFER_SIZE = 4096;

    // -----物件变数-----
    // -----物件方法-----
    /**
     * 加密内容。
     *
     * @param data 传入要加密的内容
     * @return 传回加密后的内容
     */
    public byte[] encrypt(final byte[] data) {
        return encrypt(data, null);
    }

    /**
     * 加密資料。
     *
     * @param data 传入要加密的内容
     * @param listener 传入监听者
     * @return 传回加密后的内容
     */
    public byte[] encrypt(final byte[] data, final CryptListener listener) {
        try (final ByteArrayInputStream bais = new ByteArrayInputStream(data); final ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            encrypt(bais, baos, listener);
            return baos.toByteArray();
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 解密内容。
     *
     * @param data 传入要解密的内容
     * @return 传回解密后的内容
     */
    public byte[] decrypt(final byte[] data) {
        return decrypt(data, null);
    }

    /**
     * 解密内容。
     *
     * @param data 传入要解密的内容
     * @param listener 传入监听者物件
     * @return 传回加密后的内容
     */
    public byte[] decrypt(final byte[] data, final CryptListener listener) {
        try (final ByteArrayInputStream bais = new ByteArrayInputStream(data); final ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            decrypt(bais, baos, listener);
            return baos.toByteArray();
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 加密内容。
     *
     * @param inputData 传入要加密的内容流
     * @param outputData 传入已加密的内容流
     * @throws java.io.IOException 当输入输出处理时发生问题，会抛出这个异常
     */
    public void encrypt(final InputStream inputData, final OutputStream outputData) throws IOException {
        encrypt(inputData, outputData, null);
    }

    /**
     * 解密内容。
     *
     * @param inputData 传入要解密的内容流
     * @param outputData 传入已解密的内容流
     * @throws java.io.IOException  当输入输出处理时发生问题，会抛出这个异常
     */
    public void decrypt(final InputStream inputData, final OutputStream outputData) throws IOException {
        decrypt(inputData, outputData, null);
    }

    // -----抽象方法-----
    /**
     * 加密内容。
     *
     * @param inputData 传入要加密的内容流
     * @param outputData 传入已加密的内容流
     * @param listener 传入监听者物件
     * @throws java.io.IOException 当输入输出处理时发生问题，会抛出这个异常
     */
    public abstract void encrypt(final InputStream inputData, final OutputStream outputData, final CryptListener listener) throws IOException;

    /**
     * 解密内容。
     *
     * @param inputData 传入要解密的内容流
     * @param outputData 传入已解密的内容流
     * @param listener 传入监听者
     * @throws java.io.IOException 当输入输出处理时发生问题，会抛出这个异常
     */
    public abstract void decrypt(final InputStream inputData, final OutputStream outputData, final CryptListener listener) throws IOException;
}
