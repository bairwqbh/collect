package per.cby.collect.satellite.common.event;

import per.cby.collect.common.event.DataIssuedEvent;
import per.cby.collect.common.model.TerminalMessage;

/**
 * 天通数据下发事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public class TiantongIssuedEvent extends DataIssuedEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public TiantongIssuedEvent(TerminalMessage message) {
        super(message);
    }

}
