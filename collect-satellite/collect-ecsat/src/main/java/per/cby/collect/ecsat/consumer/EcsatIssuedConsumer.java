package per.cby.collect.ecsat.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.ecsat.constant.EcsatCollectConstant;
import per.cby.collect.ecsat.publisher.EcsatIssuedPublisher;
import per.cby.frame.common.constant.FlagString;
import per.cby.frame.rabbitmq.RabbitMQExchangeNames;

import lombok.extern.slf4j.Slf4j;

/**
 * 卫星平台终端消息下发消费者
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
@Slf4j
public class EcsatIssuedConsumer implements EcsatCollectConstant {

    @Autowired(required = false)
    private EcsatIssuedPublisher ecsatIssuedPublisher;

    /**
     * 终端数据下发消费
     * 
     * @param message 下发消息
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = TERMINAL_ISSUED_ECSAT, durable = FlagString.TRUE),
        exchange = @Exchange(value = RabbitMQExchangeNames.TOPIC, type = ExchangeTypes.TOPIC, durable = FlagString.TRUE),
        key = TERMINAL_ISSUED_ECSAT)
    )
    public void consume(TerminalMessage message) {
        try {
            ecsatIssuedPublisher.publish(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
