package per.cby.collect.common.service;

import per.cby.collect.common.model.TerminalMessage;

/**
 * FOTA升级消息同步交换器
 * 
 * @author chenboyang
 * @since 2020年7月16日
 *
 */
public interface FotaExchanger {

    /**
     * FOTA升级消息同步交换
     * 
     * @param message 请求消息
     * @return 响应消息
     */
    TerminalMessage exchange(TerminalMessage message);

}
