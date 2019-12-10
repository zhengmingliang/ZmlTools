package top.wys.utils;
/**
 * Created by 郑明亮 on 2019/11/12 15:57.
 */

/**
 *  2019/11/12 15:57 <br>
 *
 * 文件读取回调
 *
 * @author 郑明亮
 * @version 1.0
 */
public interface Callback {
    void apply(String data);
    void getFirstLine(String firstLine);
}

