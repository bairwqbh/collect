package per.cby.collect.satellite.common.service;

import per.cby.collect.common.model.TerminalMessage;

/**
 * 终端数据卫星网下发服务
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface SatelliteIssuedService {

    /**
     * 卫星平台数据下发
     * 
     * @param message 下发消息
     */
    void ecsatIssued(TerminalMessage message);

    /**
     * 卫星平台数据下发
     * 
     * @param message 下发消息
     * @param qos     服务质量
     */
    void ecsatIssued(TerminalMessage message, int qos);

    /**
     * 铱星数据下发
     * 
     * @param message 下发消息
     */
    void iridiumIssued(TerminalMessage message);

    /**
     * OGI数据下发
     * 
     * @param message 下发消息
     */
    void orbcommIssued(TerminalMessage message);

    /**
     * 北斗数据下发
     * 
     * @param message 下发消息
     */
    void beidouIssued(TerminalMessage message);

    /**
     * 天通数据下发
     * 
     * @param message 下发消息
     */
    void tiantongIssued(TerminalMessage message);

}
