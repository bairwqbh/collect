package per.cby.collect.common.event;

import per.cby.collect.common.model.TerminalMessage;

/**
 * FOTA上报事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public class FotaReportEvent extends ReportEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public FotaReportEvent(TerminalMessage message) {
        super(message);
    }

}
