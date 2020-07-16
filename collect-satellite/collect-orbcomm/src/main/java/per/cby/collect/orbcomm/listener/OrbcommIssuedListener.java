package per.cby.collect.orbcomm.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import per.cby.collect.orbcomm.service.OrbcommService;
import per.cby.collect.satellite.common.event.OrbcommIssuedEvent;

/**
 * OGI数据下发监听器
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class OrbcommIssuedListener {

    @Autowired(required = false)
    private OrbcommService orbcommService;

    @Async
    @EventListener(OrbcommIssuedEvent.class)
    public void onEvent(OrbcommIssuedEvent event) {
        orbcommService.sendMessage(event.getSource());
    }

}
