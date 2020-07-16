package per.cby.collect.iridium.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.iridium.bootstrap.IridiumClient;
import per.cby.collect.satellite.common.constant.SatelliteConstant;
import per.cby.frame.common.constant.FlagString;
import per.cby.frame.rabbitmq.RabbitMQExchangeNames;

import lombok.extern.slf4j.Slf4j;

/**
 * 铱星消息下发消费者
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
@Slf4j
public class IridiumIssuedConsumer implements SatelliteConstant {

    @Autowired(required = false)
    private IridiumClient iridiumClient;

    /**
     * 终端数据下发消费
     * 
     * @param message 下发消息
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = TERMINAL_ISSUED_IRIDIUM, durable = FlagString.TRUE),
        exchange = @Exchange(value = RabbitMQExchangeNames.TOPIC, type = ExchangeTypes.TOPIC, durable = FlagString.TRUE),
        key = TERMINAL_ISSUED_IRIDIUM)
    )
    public void consume(TerminalMessage message) {
        try {
            iridiumClient.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
