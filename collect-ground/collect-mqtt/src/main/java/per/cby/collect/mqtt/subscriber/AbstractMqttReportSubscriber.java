package per.cby.collect.mqtt.subscriber;

import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageHeaders;

import per.cby.collect.common.constant.GatewayType;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.ground.common.constant.Protocol;
import per.cby.collect.ground.common.service.SupportService;
import per.cby.collect.mqtt.constant.MqttCollectConstant;
import per.cby.frame.mqtt.ByteArrayMqttSubscriber;
import per.cby.frame.mqtt.config.properties.SubscriberProperties;
import per.cby.frame.task.scheduler.DistributeTaskScheduler;

import lombok.extern.slf4j.Slf4j;

/**
 * MQTT终端上报抽象订阅者
 * 
 * @author chenboyang
 * @since 2020年3月23日
 *
 */
@Slf4j
public abstract class AbstractMqttReportSubscriber extends ByteArrayMqttSubscriber implements MqttCollectConstant {

    @Autowired(required = false)
    protected DistributeTaskScheduler distributeTaskScheduler;

    @Autowired(required = false)
    private SupportService supportService;

    public AbstractMqttReportSubscriber(SubscriberProperties properties) {
        super(properties);
    }

    protected void handleMessage(MessageHeaders headers, byte[] payload, Consumer<TerminalMessage> consumer) {
        try {
            log.info("收到MQTT上报数据：{} - {}", headers, Hex.encodeHexString(payload));
            String topic = headers.get(MqttHeaders.RECEIVED_TOPIC).toString();
            String[] paths = topic.split("/");
            String sn = paths[paths.length - 1];
            distributeTaskScheduler.schedule(taskName(sn, payload), () -> {
                TerminalMessage message = TerminalMessage.create();
                message.setTerminalId(sn);
                message.setChannel(supportService.getChannelByProtocol(Protocol.MQTT));
                message.setPayload(payload);
                message.getHeader().put(GATEWAY, GatewayType.MQTT.getCode());
                Optional.ofNullable(consumer).ifPresent(t -> t.accept(message));
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /** 任务前缀 */
    protected abstract String taskPrefix();

    /**
     * 获取任务名称
     * 
     * @param payload 消息载荷
     * @return 任务名称
     */
    private String taskName(String sn, byte[] payload) {
        return taskPrefix() + sn + "." + DigestUtils.md5Hex(payload);
    }

}
