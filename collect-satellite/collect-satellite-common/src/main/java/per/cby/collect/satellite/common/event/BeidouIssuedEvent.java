package per.cby.collect.satellite.common.event;

import per.cby.collect.common.event.DataIssuedEvent;
import per.cby.collect.common.model.TerminalMessage;

/**
 * 北斗数据下发事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public class BeidouIssuedEvent extends DataIssuedEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public BeidouIssuedEvent(TerminalMessage message) {
        super(message);
    }

}
