/*
 * Created by 郑明亮 on 2021/5/30 7:46.
 */

//

package top.wuyongshi.webapi.utils;

import java.math.BigDecimal;

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
public  class BalanceEventHandler implements EventHandler<Number>{

    private BigDecimal balance;

    public BalanceEventHandler(Double balance) {
        this.balance = new BigDecimal(balance + "");
    }

    @Override
    public void notice(Number message) {
        balance = balance.subtract(new BigDecimal(message+""));
        System.out.printf(",还有余额%s元\r\n",new BigDecimal(""+balance).toPlainString());
    }
}
