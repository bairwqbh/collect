package per.cby.collect.iridium.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import per.cby.collect.iridium.bootstrap.IridiumClient;
import per.cby.collect.satellite.common.event.IridiumIssuedEvent;

/**
 * 铱星数据下发监听器
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class IridiumIssuedListener {

    @Autowired(required = false)
    private IridiumClient iridiumClient;

    @Async
    @EventListener(IridiumIssuedEvent.class)
    public void onEvent(IridiumIssuedEvent event) {
        iridiumClient.send(event.getSource());
    }

}
