package per.cby.collect.coap.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.coap.constant.CoapCollectConstant;
import per.cby.collect.coap.redis.CoapIssuedHash;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.frame.common.constant.FlagString;
import per.cby.frame.rabbitmq.RabbitMQExchangeNames;

import lombok.extern.slf4j.Slf4j;

/**
 * CoAP下发消费者
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
@Slf4j
public class CoapConsumer implements CoapCollectConstant {

    @Autowired(required = false)
    private CoapIssuedHash coapIssuedHash;

    /**
     * 终端数据下发消费
     * 
     * @param message 下发消息
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = TERMINAL_ISSUED_COAP, durable = FlagString.TRUE),
        exchange = @Exchange(value = RabbitMQExchangeNames.TOPIC, type = ExchangeTypes.TOPIC, durable = FlagString.TRUE),
        key = TERMINAL_ISSUED_COAP
    ))
    public void consume(TerminalMessage message) {
        try {
            coapIssuedHash.put(message.getTerminalId(), message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
