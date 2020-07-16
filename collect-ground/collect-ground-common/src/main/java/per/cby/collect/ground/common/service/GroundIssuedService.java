package per.cby.collect.ground.common.service;

import per.cby.collect.common.model.TerminalMessage;

/**
 * 终端数据地面网下发服务
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface GroundIssuedService {

    /**
     * MQTT数据下发
     * 
     * @param message 下发消息
     */
    void mqttIssued(TerminalMessage message);

    /**
     * MQTT数据下发
     * 
     * @param message 下发消息
     * @param qos     服务质量
     */
    void mqttIssued(TerminalMessage message, int qos);

    /**
     * CoAP下发
     * 
     * @param message 下发消息
     */
    void coapIssued(TerminalMessage message);

    /**
     * 套接字下发
     * 
     * @param message 下发消息
     */
    void socketIssued(TerminalMessage message);

    /**
     * FOTA升级MQTT下发
     * 
     * @param message 下发消息
     */
    void fotaMqttIssued(TerminalMessage message);

    /**
     * FOTA升级MQTT下发
     * 
     * @param message 下发消息
     * @param qos     服务质量
     */
    void fotaMqttIssued(TerminalMessage message, int qos);

}
