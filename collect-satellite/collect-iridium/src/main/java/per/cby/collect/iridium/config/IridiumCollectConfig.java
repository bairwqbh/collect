package per.cby.collect.iridium.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.iridium.bootstrap.IridiumClient;
import per.cby.collect.iridium.bootstrap.IridiumServer;
import per.cby.collect.iridium.config.properties.IridiumProperties;
import per.cby.collect.iridium.consumer.IridiumIssuedConsumer;
import per.cby.collect.iridium.handler.IridiumReportHandler;
import per.cby.collect.iridium.listener.IridiumIssuedListener;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 铱星采集配置
 * 
 * @since 2020年2月11日
 * 
 * @author chenboyang
 *
 */
@Configuration("__IRIDIUM_COLLECT_CONFIG__")
public class IridiumCollectConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties("sys.business.iridium")
    public IridiumProperties iridiumProperties() {
        return new IridiumProperties();
    }

    @Bean
    @ConditionalOnMissingBean(value = IridiumServer.class, name = "iridiumServer")
    public IridiumServer iridiumServer() {
        return new IridiumServer(iridiumProperties().getReceivePort());
    }

    @Bean
    @ConditionalOnMissingBean(value = IridiumReportHandler.class, name = "iridiumReportHandler")
    public IridiumReportHandler iridiumReportHandler() {
        return new IridiumReportHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = IridiumClient.class, name = "iridiumClient")
    public IridiumClient iridiumClient() {
        return new IridiumClient(iridiumProperties().getHost(), iridiumProperties().getPort());
    }

    @Bean
    @ConditionalOnBean(ConnectionFactory.class)
    @ConditionalOnMissingBean(value = IridiumIssuedConsumer.class, name = "iridiumIssuedConsumer")
    public IridiumIssuedConsumer iridiumIssuedConsumer() {
        return new IridiumIssuedConsumer();
    }

    @Bean
    @ConditionalOnMissingBean(value = IridiumIssuedListener.class, name = "iridiumIssuedListener")
    public IridiumIssuedListener iridiumIssuedListener() {
        return new IridiumIssuedListener();
    }

}
