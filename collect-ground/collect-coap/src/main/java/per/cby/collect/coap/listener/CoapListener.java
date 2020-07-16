package per.cby.collect.coap.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import per.cby.collect.coap.redis.CoapIssuedHash;
import per.cby.collect.ground.common.event.CoapIssuedEvent;

/**
 * CoAP下发监听器
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class CoapListener {

    @Autowired(required = false)
    private CoapIssuedHash coapIssuedHash;

    @Async
    @EventListener(CoapIssuedEvent.class)
    public void onEvent(CoapIssuedEvent event) {
        coapIssuedHash.put(event.getSource().getTerminalId(), event.getSource());
    }

}
