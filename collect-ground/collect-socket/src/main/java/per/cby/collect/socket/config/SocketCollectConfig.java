package per.cby.collect.socket.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.socket.bootstrap.SocketCollectServer;
import per.cby.collect.socket.bootstrap.SocketFotaServer;
import per.cby.collect.socket.config.properties.SocketProperties;
import per.cby.collect.socket.consumer.SocketConsumer;
import per.cby.collect.socket.handler.SocketAuthenHandler;
import per.cby.collect.socket.handler.SocketCollectHandler;
import per.cby.collect.socket.handler.SocketFotaHandler;
import per.cby.collect.socket.handler.SocketIssuedHandler;
import per.cby.collect.socket.listener.SocketListener;
import per.cby.collect.socket.redis.SocketIssuedList;
import per.cby.collect.socket.redis.SocketIssuedMap;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 套接字采集配置
 * 
 * @author chenboyang
 * @since 2020年3月20日
 *
 */
@Configuration("__SOCKET_COLLECT_CONFIG__")
public class SocketCollectConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties("sys.business.socket")
    public SocketProperties socketProperties() {
        return new SocketProperties();
    }

    @Bean
    @ConditionalOnMissingBean(value = SocketCollectServer.class, name = "socketCollectServer")
    public SocketCollectServer socketCollectServer() {
        return new SocketCollectServer(socketProperties().getPort());
    }

    @Bean
    @ConditionalOnMissingBean(value = SocketFotaServer.class, name = "socketFotaServer")
    public SocketFotaServer socketFotaServer() {
        return new SocketFotaServer(socketProperties().getFota().getPort());
    }

    @Bean
    @ConditionalOnBean(ConnectionFactory.class)
    @ConditionalOnMissingBean(value = SocketConsumer.class, name = "socketConsumer")
    public SocketConsumer socketConsumer() {
        return new SocketConsumer();
    }

    @Bean
    @ConditionalOnMissingBean(value = SocketAuthenHandler.class, name = "socketAuthenHandler")
    public SocketAuthenHandler socketAuthenHandler() {
        return new SocketAuthenHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = SocketCollectHandler.class, name = "socketCollectHandler")
    public SocketCollectHandler socketCollectHandler() {
        return new SocketCollectHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = SocketFotaHandler.class, name = "socketFotaHandler")
    public SocketFotaHandler socketFotaHandler() {
        return new SocketFotaHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = SocketListener.class, name = "socketListener")
    public SocketListener socketListener() {
        return new SocketListener();
    }

    @Bean
    @ConditionalOnMissingBean(value = SocketIssuedList.class, name = "socketIssuedList")
    public SocketIssuedList socketIssuedList() {
        return new SocketIssuedList();
    }

    @Bean
    @ConditionalOnMissingBean(value = SocketIssuedMap.class, name = "socketIssuedMap")
    public SocketIssuedMap socketIssuedMap() {
        return new SocketIssuedMap();
    }

    @Bean
    @ConditionalOnMissingBean(value = SocketIssuedHandler.class, name = "socketIssuedHandler")
    public SocketIssuedHandler socketIssuedHandler() {
        return new SocketIssuedHandler();
    }

}
