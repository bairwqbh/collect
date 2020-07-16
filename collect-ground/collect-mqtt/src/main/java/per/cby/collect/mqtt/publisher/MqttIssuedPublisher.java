package per.cby.collect.mqtt.publisher;

import org.apache.commons.codec.binary.Hex;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.mqtt.constant.MqttCollectConstant;
import per.cby.frame.mqtt.MqttPublisherAdapter;
import per.cby.frame.mqtt.util.MqttUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * MQTT终端数据下发发布者
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
@Slf4j
@RequiredArgsConstructor
public class MqttIssuedPublisher implements MqttCollectConstant {

    /** 项目编号 */
    private final String projectId;

    /** 卫星平台消息发布者适配器 */
    private final MqttPublisherAdapter mqttPublisher;

    /**
     * 终端消息发布
     * 
     * @param message 下发消息
     */
    public void publish(TerminalMessage message) {
        MqttUtil.publish(mqttPublisher, TERMINAL_ISSUED_TOPIC + projectId + "/" + message.getTerminalId(),
                message.getPayload(), message.getHeader());
        log.info("发送MQTT下发数据：{}", Hex.encodeHexString(message.getPayload()));
    }

    /**
     * FOTA消息发布
     * 
     * @param message 下发消息
     */
    public void fotaPublish(TerminalMessage message) {
        MqttUtil.publish(mqttPublisher, FOTA_ISSUED_TOPIC + projectId + "/" + message.getTerminalId(),
                message.getPayload(), message.getHeader());
        log.info("发送MQTT的FOTA下发数据：{}", Hex.encodeHexString(message.getPayload()));
    }

}
