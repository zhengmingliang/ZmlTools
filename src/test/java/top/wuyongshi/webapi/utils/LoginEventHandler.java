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
public  class LoginEventHandler implements EventHandler<String>{
    @Override
    public void notice(String message) {
        System.out.println(message+"登陆了");
    }
}
