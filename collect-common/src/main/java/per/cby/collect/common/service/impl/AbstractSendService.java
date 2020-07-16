package per.cby.collect.common.service.impl;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.frame.common.constant.CommMode;
import per.cby.frame.common.event.AbstractEvent;
import per.cby.frame.common.event.EventPublisher;
import per.cby.frame.rabbitmq.exchange.TopicExchange;

/**
 * 数据发送基础服务
 *
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public abstract class AbstractSendService {

    /** 内部通信模式 */
    @Autowired(required = false)
    protected CommMode commMode;

    @Autowired(required = false)
    protected TopicExchange topicExchange;

    @Autowired(required = false)
    protected EventPublisher eventPublisher;

    /**
     * 发送消息
     * 
     * @param message  终端消息
     * @param key      路由键
     * @param supplier 事件提供者
     */
    protected void send(TerminalMessage message, String key, Supplier<AbstractEvent<TerminalMessage>> supplier) {
        switch (commMode) {
            case MQ:
                topicExchange.send(key, message);
                break;
            case EVENT:
                eventPublisher.publish(supplier.get());
                break;
            default:
                break;
        }
    }

}
