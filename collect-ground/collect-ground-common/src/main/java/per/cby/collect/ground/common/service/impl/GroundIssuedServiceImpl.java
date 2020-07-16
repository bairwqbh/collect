package per.cby.collect.ground.common.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.impl.AbstractSendService;
import per.cby.collect.ground.common.constant.GroundConstant;
import per.cby.collect.ground.common.constant.Protocol;
import per.cby.collect.ground.common.event.CoapIssuedEvent;
import per.cby.collect.ground.common.event.MqttFotaIssuedEvent;
import per.cby.collect.ground.common.event.MqttIssuedEvent;
import per.cby.collect.ground.common.event.SocketIssuedEvent;
import per.cby.collect.ground.common.service.GroundIssuedService;
import per.cby.collect.ground.common.service.SupportService;
import per.cby.frame.mqtt.constant.MqttConstant;

/**
 * 终端数据地面网下发服务实现
 *
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class GroundIssuedServiceImpl extends AbstractSendService
        implements GroundIssuedService, GroundConstant, MqttConstant {

    @Autowired(required = false)
    private SupportService supportService;

    @Override
    public void mqttIssued(TerminalMessage message) {
        send(message, TERMINAL_ISSUED_MQTT, () -> new MqttIssuedEvent(message));
    }

    @Override
    public void coapIssued(TerminalMessage message) {
        send(message, TERMINAL_ISSUED_COAP, () -> new CoapIssuedEvent(message));
    }

    @Override
    public void socketIssued(TerminalMessage message) {
        send(message, TERMINAL_ISSUED_SOCKET, () -> new SocketIssuedEvent(message));
    }

    @Override
    public void mqttIssued(TerminalMessage message, int qos) {
        message.getHeader().put(QOS, qos);
        mqttIssued(message);
    }

    @Override
    public void fotaMqttIssued(TerminalMessage message) {
        if (StringUtils.isBlank(message.getChannel())) {
            message.setChannel(supportService.getChannelByProtocol(Protocol.MQTT));
        }
        send(message, FOTA_ISSUED_MQTT, () -> new MqttFotaIssuedEvent(message));
    }

    @Override
    public void fotaMqttIssued(TerminalMessage message, int qos) {
        message.getHeader().put(QOS, qos);
        fotaMqttIssued(message);
    }

}
