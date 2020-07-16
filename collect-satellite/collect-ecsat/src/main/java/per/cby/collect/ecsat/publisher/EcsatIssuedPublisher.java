package per.cby.collect.ecsat.publisher;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.ecsat.constant.EcsatCollectConstant;
import per.cby.frame.common.util.JsonUtil;
import per.cby.frame.mqtt.MqttPublisherAdapter;
import per.cby.frame.mqtt.util.MqttUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 卫星平台终端数据下发发布者
 * 
 * @author chenboyang
 * @since 2019年12月03日
 *
 */
@Slf4j
@RequiredArgsConstructor
public class EcsatIssuedPublisher implements EcsatCollectConstant {

    /** 项目编号 */
    private final String projectId;

    /** 卫星平台消息发布者适配器 */
    private final MqttPublisherAdapter mqttPublisher;

    /**
     * 卫星平台终端消息发布
     * 
     * @param message 下发消息
     */
    public void publish(TerminalMessage message) {
        MqttUtil.publish(mqttPublisher, ISSUED_TOPIC + projectId, message, message.getHeader());
        log.info("收到卫星平台下发数据：{}", JsonUtil.toJson(message));
    }

}
