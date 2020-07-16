package per.cby.collect.ecsat.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import per.cby.collect.ecsat.publisher.EcsatIssuedPublisher;
import per.cby.collect.satellite.common.event.EcsatIssuedEvent;

/**
 * 卫星平台终端数据下发监听器
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class EcsatIssuedListener {

    @Autowired(required = false)
    private EcsatIssuedPublisher ecsatIssuedPublisher;

    @Async
    @EventListener(EcsatIssuedEvent.class)
    public void onEvent(EcsatIssuedEvent event) {
        ecsatIssuedPublisher.publish(event.getSource());
    }

}
