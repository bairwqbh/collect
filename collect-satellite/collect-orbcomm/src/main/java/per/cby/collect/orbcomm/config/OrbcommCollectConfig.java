package per.cby.collect.orbcomm.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.orbcomm.config.properties.OrbcommProperties;
import per.cby.collect.orbcomm.consumer.OrbcommIssuedConsumer;
import per.cby.collect.orbcomm.listener.OrbcommIssuedListener;
import per.cby.collect.orbcomm.service.OrbcommService;
import per.cby.collect.orbcomm.service.impl.OrbcommServiceImpl;
import per.cby.collect.orbcomm.task.OrbcommMessagePollJob;
import per.cby.collect.orbcomm.task.OrbcommMessagePollListener;
import com.rabbitmq.client.ConnectionFactory;

/**
 * OGI采集配置
 * 
 * @since 2020年2月11日
 * 
 * @author chenboyang
 *
 */
@Configuration("__ORBCOMM_COLLECT_CONFIG__")
public class OrbcommCollectConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties("sys.business.orbcomm")
    public OrbcommProperties OrbcommProperties() {
        return new OrbcommProperties();
    }

    @Bean
    @ConditionalOnMissingBean(value = OrbcommService.class, name = "orbcommRestService")
    public OrbcommService orbcommRestService() {
        return new OrbcommServiceImpl();
    }

    @Bean
    @ConditionalOnBean(ConnectionFactory.class)
    @ConditionalOnMissingBean(value = OrbcommIssuedConsumer.class, name = "orbcommIssuedConsumer")
    public OrbcommIssuedConsumer orbcommIssuedConsumer() {
        return new OrbcommIssuedConsumer();
    }

    @Bean
    @ConditionalOnMissingBean(value = OrbcommIssuedListener.class, name = "orbcommIssuedListener")
    public OrbcommIssuedListener orbcommIssuedListener() {
        return new OrbcommIssuedListener();
    }

    @Bean
    @ConditionalOnMissingBean(value = OrbcommMessagePollJob.class, name = "orbcommMessagePollJob")
    public OrbcommMessagePollJob orbcommMessagePollJob() {
        return new OrbcommMessagePollJob();
    }

    @Bean
    @ConditionalOnMissingBean(value = OrbcommMessagePollListener.class, name = "orbcommMessagePollListener")
    public OrbcommMessagePollListener orbcommMessagePollListener() {
        return new OrbcommMessagePollListener();
    }

}
