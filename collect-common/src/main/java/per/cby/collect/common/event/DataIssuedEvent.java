package per.cby.collect.common.event;

import per.cby.collect.common.model.TerminalMessage;

/**
 * 数据下发事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public abstract class DataIssuedEvent extends IssuedEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public DataIssuedEvent(TerminalMessage message) {
        super(message);
    }

}
