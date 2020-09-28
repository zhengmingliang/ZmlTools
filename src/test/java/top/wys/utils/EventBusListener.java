package top.wys.utils;
/**
 * Created by 郑明亮 on 2019/10/23 12:47.
 */

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <ol>
 *  2019/10/23 12:47 <br>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 */
public class EventBusListener {

    Logger log = LoggerFactory.getLogger(EventBusListener.class);

    @Subscribe
    public void handleMessage(String msg){
        log.info("msg:{}",msg);
        System.out.println(msg);
    }

    @Subscribe
    public void handleIntMessage(Integer num){
        log.info("num:{}",num);
        System.out.println(num);
    }

    @Subscribe
    private void handleDeadEvent(DeadEvent deadEvent){
        Object event = deadEvent.getEvent();
        System.out.println("event = " + event);
        Object source = deadEvent.getSource();
        System.out.println("source = " + source);
        String str = deadEvent.toString();
        System.out.println("str = " + str);
    }
}
