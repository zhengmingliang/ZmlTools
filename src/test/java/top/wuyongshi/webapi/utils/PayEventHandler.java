/*
 * Created by 郑明亮 on 2021/5/30 7:46.
 */

//

package top.wuyongshi.webapi.utils;

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
public  class PayEventHandler implements EventHandler<Number>{
    @Override
    public void notice(Number message) {
        System.out.printf("消费了%s元",message);
    }
}
