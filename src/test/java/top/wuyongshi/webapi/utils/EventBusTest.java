/*
 * Created by 郑明亮 on 2021/5/30 8:10.
 */

//

package top.wuyongshi.webapi.utils;

import org.junit.Test;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2021/5/30 8:10
 * @email mpro@vip.qq.com
 */
public class EventBusTest {
    @Test
    public void test() {
        EventBus eventBus = new EventBus();
        LoginEventHandler loginEventHandler = new LoginEventHandler();
        PayEventHandler payEventHandler = new PayEventHandler();
        BalanceEventHandler balanceEventHandler = new BalanceEventHandler(100D);
        eventBus.register("login",loginEventHandler);
        eventBus.register("pay",payEventHandler);
        eventBus.register("pay",balanceEventHandler);

        eventBus.notice("login","张三");
        eventBus.notice("pay",10.256412456);
    }
}
