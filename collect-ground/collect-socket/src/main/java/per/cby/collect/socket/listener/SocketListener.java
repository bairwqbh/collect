package per.cby.collect.socket.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import per.cby.collect.ground.common.event.SocketIssuedEvent;
import per.cby.collect.socket.handler.SocketIssuedHandler;

/**
 * 套接字下发监听器
 * 
 * @author chenboyang
 * @since 2020年3月19日
 *
 */
public class SocketListener {

    @Autowired(required = false)
    private SocketIssuedHandler socketIssuedHandler;

    @Async
    @EventListener(SocketIssuedEvent.class)
    public void onEvent(SocketIssuedEvent event) {
        socketIssuedHandler.accept(event.getSource());
    }

}
