package per.cby.collect.common.event;

import per.cby.collect.common.model.TerminalMessage;

/**
 * 终端注册事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public class RegistEvent extends ReportEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public RegistEvent(TerminalMessage message) {
        super(message);
    }

}
