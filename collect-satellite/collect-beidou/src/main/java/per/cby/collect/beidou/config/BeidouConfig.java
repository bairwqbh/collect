package per.cby.collect.beidou.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.beidou.bootstrap.BeidouServer;
import per.cby.collect.beidou.consumer.BeidouConsumer;
import per.cby.collect.beidou.handler.BeidouHandler;
import per.cby.collect.beidou.listener.BeidouListener;
import per.cby.collect.beidou.redis.BeidouIssuedMessageList;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 北斗采集配置
 * 
 * @since 2020年2月11日
 * 
 * @author chenboyang
 *
 */
@Configuration("__BEIDOU_CONFIG__")
public class BeidouConfig {

    @Value("${sys.business.beidou.port:20102}")
    public int port;

    @Bean
    @ConditionalOnMissingBean(value = BeidouServer.class, name = "beidouServer")
    public BeidouServer beidouServer() {
        return new BeidouServer(port);
    }

    @Bean
    @ConditionalOnMissingBean(value = BeidouHandler.class, name = "beidouHandler")
    public BeidouHandler beidouHandler() {
        return new BeidouHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = BeidouIssuedMessageList.class, name = "beidouIssuedMessageList")
    public BeidouIssuedMessageList beidouIssuedMessageList() {
        return new BeidouIssuedMessageList();
    }

    @Bean
    @ConditionalOnBean(ConnectionFactory.class)
    @ConditionalOnMissingBean(value = BeidouConsumer.class, name = "beidouConsumer")
    public BeidouConsumer beidouConsumer() {
        return new BeidouConsumer();
    }

    @Bean
    @ConditionalOnMissingBean(value = BeidouListener.class, name = "beidouListener")
    public BeidouListener beidouListener() {
        return new BeidouListener();
    }

}
