/*
 * Created by 郑明亮 on 2021/5/30 7:45.
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
 * @date 2021/5/30 7:45
 * @email mpro@vip.qq.com
 */
public class EventBus {
    private Map<String, List<EventHandler>> handlers = Maps.newConcurrentMap();
    public void register(String topic,EventHandler eventHandler){
//        String name = eventHandler.getClass().getName();
        List<EventHandler> eventHandlers = handlers.get(topic);
        if(eventHandlers == null){
            eventHandlers = Lists.newLinkedList();
        }
        eventHandlers.add(eventHandler);
        handlers.put(topic,eventHandlers);

    }

    public void notice(String topic,Object message){
        List<EventHandler> eventHandlers = handlers.get(topic);
        if (eventHandlers != null) {
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.notice(message);
            }
        }
    }
}
