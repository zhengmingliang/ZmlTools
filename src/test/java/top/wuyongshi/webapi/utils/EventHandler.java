/*
 * Created by 郑明亮 on 2021/5/30 7:46.
 */

//

package top.wuyongshi.webapi.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2021/5/30 7:46
 * @email mpro@vip.qq.com
 */
public interface EventHandler<T> {

     void notice(T message);
}
