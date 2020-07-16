package per.cby.collect.mqtt.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.mqtt.constant.MqttCollectConstant;
import per.cby.collect.mqtt.publisher.MqttIssuedPublisher;
import per.cby.frame.common.constant.FlagString;
import per.cby.frame.rabbitmq.RabbitMQExchangeNames;

import lombok.extern.slf4j.Slf4j;

/**
 * MQTT终端消息下发消费者
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
@Slf4j
public class MqttIssuedConsumer implements MqttCollectConstant {

    @Autowired(required = false)
    private MqttIssuedPublisher mqttIssuedPublisher;

    /**
     * 终端数据下发消费
     * 
     * @param message 下发消息
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = TERMINAL_ISSUED_MQTT, durable = FlagString.TRUE),
        exchange = @Exchange(value = RabbitMQExchangeNames.TOPIC, type = ExchangeTypes.TOPIC, durable = FlagString.TRUE),
        key = TERMINAL_ISSUED_MQTT)
    )
    public void consume(TerminalMessage message) {
        try {
            mqttIssuedPublisher.publish(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 终端FOTA下发消费
     * 
     * @param message 下发消息
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = FOTA_ISSUED_MQTT, durable = FlagString.TRUE),
        exchange = @Exchange(value = RabbitMQExchangeNames.TOPIC, type = ExchangeTypes.TOPIC, durable = FlagString.TRUE),
        key = FOTA_ISSUED_MQTT)
    )
    public void fotaConsume(TerminalMessage message) {
        try {
            mqttIssuedPublisher.fotaPublish(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
