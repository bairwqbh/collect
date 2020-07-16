package per.cby.collect.ground.common.event;

import per.cby.collect.common.event.DataIssuedEvent;
import per.cby.collect.common.model.TerminalMessage;

/**
 * CoAP下发事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public class SocketIssuedEvent extends DataIssuedEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public SocketIssuedEvent(TerminalMessage message) {
        super(message);
    }

}
