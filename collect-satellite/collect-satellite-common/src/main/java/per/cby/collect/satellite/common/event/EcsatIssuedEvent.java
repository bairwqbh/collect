package per.cby.collect.satellite.common.event;

import per.cby.collect.common.event.DataIssuedEvent;
import per.cby.collect.common.model.TerminalMessage;

/**
 * 卫星平台下发事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public class EcsatIssuedEvent extends DataIssuedEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public EcsatIssuedEvent(TerminalMessage message) {
        super(message);
    }

}
