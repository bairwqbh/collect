package per.cby.collect.common.event;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.frame.common.event.AbstractEvent;

/**
 * 终端上报事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public abstract class ReportEvent extends AbstractEvent<TerminalMessage> {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public ReportEvent(TerminalMessage message) {
        super(message);
    }

}
