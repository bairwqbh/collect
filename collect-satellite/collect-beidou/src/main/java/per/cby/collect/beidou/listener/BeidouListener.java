package per.cby.collect.beidou.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import per.cby.collect.beidou.redis.BeidouIssuedMessageList;
import per.cby.collect.satellite.common.event.BeidouIssuedEvent;

/**
 * 北斗数据下发监听器
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class BeidouListener {

    @Autowired(required = false)
    private BeidouIssuedMessageList beidouIssuedMessageList;

    @Async
    @EventListener(BeidouIssuedEvent.class)
    public void onEvent(BeidouIssuedEvent event) {
        beidouIssuedMessageList.leftPush(event.getSource());
    }

}
