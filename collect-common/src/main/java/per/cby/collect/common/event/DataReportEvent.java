package per.cby.collect.common.event;

import per.cby.collect.common.model.TerminalMessage;

/**
 * 数据上报事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public class DataReportEvent extends ReportEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public DataReportEvent(TerminalMessage message) {
        super(message);
    }

}
