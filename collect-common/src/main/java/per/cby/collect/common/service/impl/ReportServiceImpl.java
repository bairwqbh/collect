package per.cby.collect.common.service.impl;

import per.cby.collect.common.constant.CollectConstant;
import per.cby.collect.common.event.DataReportEvent;
import per.cby.collect.common.event.FotaReportEvent;
import per.cby.collect.common.event.RegistEvent;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.ReportService;

/**
 * 终端数据上报服务实现
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class ReportServiceImpl extends AbstractSendService implements ReportService, CollectConstant {

    @Override
    public void report(TerminalMessage message) {
        send(message, TERMINAL_REPORT, () -> new DataReportEvent(message));
    }

    @Override
    public void fotaReport(TerminalMessage message) {
        send(message, FOTA_REPORT, () -> new FotaReportEvent(message));
    }

    @Override
    public void regist(TerminalMessage message) {
        send(message, TERMINAL_REGIST, () -> new RegistEvent(message));
    }

}
