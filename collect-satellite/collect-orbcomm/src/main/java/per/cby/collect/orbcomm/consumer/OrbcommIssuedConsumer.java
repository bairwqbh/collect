package per.cby.collect.orbcomm.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.orbcomm.constant.OrbcommConstant;
import per.cby.collect.orbcomm.service.OrbcommService;
import per.cby.frame.common.constant.FlagString;
import per.cby.frame.rabbitmq.RabbitMQExchangeNames;

import lombok.extern.slf4j.Slf4j;

/**
 * OGI消息下发消费者
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
@Slf4j
public class OrbcommIssuedConsumer implements OrbcommConstant {

    @Autowired(required = false)
    private OrbcommService orbcommService;

    /**
     * 终端数据下发消费
     * 
     * @param message 下发消息
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = TERMINAL_ISSUED_ORBCOMM, durable = FlagString.TRUE),
        exchange = @Exchange(value = RabbitMQExchangeNames.TOPIC, type = ExchangeTypes.TOPIC, durable = FlagString.TRUE),
        key = TERMINAL_ISSUED_ORBCOMM)
    )
    public void consume(TerminalMessage message) {
        try {
            orbcommService.sendMessage(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
