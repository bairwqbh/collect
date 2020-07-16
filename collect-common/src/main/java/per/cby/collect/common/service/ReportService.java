package per.cby.collect.common.service;

import per.cby.collect.common.model.TerminalMessage;

/**
 * 终端上报服务
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface ReportService {

    /**
     * 终端数据上报
     * 
     * @param message 上报消息
     */
    void report(TerminalMessage message);

    /**
     * 终端FOTA上报
     * 
     * @param message 上报消息
     */
    void fotaReport(TerminalMessage message);

    /**
     * 终端注册
     * 
     * @param message
     */
    void regist(TerminalMessage message);

}
