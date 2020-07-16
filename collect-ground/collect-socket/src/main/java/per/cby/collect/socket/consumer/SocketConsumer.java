package per.cby.collect.socket.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.socket.constant.SocketCollectConstant;
import per.cby.collect.socket.handler.SocketIssuedHandler;
import per.cby.frame.common.constant.FlagString;
import per.cby.frame.rabbitmq.RabbitMQExchangeNames;

import lombok.extern.slf4j.Slf4j;

/**
 * 套接字下发消费者
 * 
 * @author chenboyang
 * @since 2020年3月19日
 *
 */
@Slf4j
public class SocketConsumer implements SocketCollectConstant {

    @Autowired(required = false)
    private SocketIssuedHandler socketIssuedHandler;

    /**
     * 终端数据下发消费
     * 
     * @param message 下发消息
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = TERMINAL_ISSUED_SOCKET, durable = FlagString.TRUE),
        exchange = @Exchange(value = RabbitMQExchangeNames.TOPIC, type = ExchangeTypes.TOPIC, durable = FlagString.TRUE),
        key = TERMINAL_ISSUED_SOCKET
    ))
    public void consume(TerminalMessage message) {
        try {
            socketIssuedHandler.accept(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
