package top.wys.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 郑明亮 on 2020/9/9 12:55.
 */

//

public class StringUtilsTest {

    @Test
    public void unicodeToString() {
        String str = StringUtils.unicodeToString("我是\\u8c03\\u5ea6\\u4efb\\u52a1\\u8ba1\\u5212");

        System.out.println(str);
    }
}