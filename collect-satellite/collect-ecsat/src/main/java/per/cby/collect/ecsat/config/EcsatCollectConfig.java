package per.cby.collect.ecsat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.ecsat.consumer.EcsatIssuedConsumer;
import per.cby.collect.ecsat.listener.EcsatIssuedListener;
import per.cby.collect.ecsat.publisher.EcsatIssuedPublisher;
import per.cby.collect.ecsat.subscriber.EcsatReportSubscriber;
import per.cby.frame.mqtt.MqttPublisherAdapter;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 卫星采集配置
 * 
 * @author chenboyang
 *
 */
@Configuration("__ECSAT_COLLECT_CONFIG__")
@ConditionalOnProperty("spring.mqtt.serverURIs")
public class EcsatCollectConfig {

    @Value("${sys.business.project.id:$}")
    private String projectId;

    @Autowired(required = false)
    private MqttPublisherAdapter mqttPublisher;

    @Bean
    @ConditionalOnBean(ConnectionFactory.class)
    @ConditionalOnMissingBean(value = EcsatIssuedConsumer.class, name = "ecsatIssuedConsumer")
    public EcsatIssuedConsumer ecsatIssuedConsumer() {
        return new EcsatIssuedConsumer();
    }

    @Bean
    @ConditionalOnMissingBean(value = EcsatIssuedListener.class, name = "ecsatIssuedListener")
    public EcsatIssuedListener ecsatIssuedListener() {
        return new EcsatIssuedListener();
    }

    @Bean
    @ConditionalOnMissingBean(value = EcsatIssuedPublisher.class, name = "ecsatIssuedPublisher")
    public EcsatIssuedPublisher ecsatIssuedPublisher() {
        return new EcsatIssuedPublisher(projectId, mqttPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(value = EcsatReportSubscriber.class, name = "ecsatReportSubscriber")
    public EcsatReportSubscriber ecsatReportSubscriber() {
        return new EcsatReportSubscriber(projectId);
    }

}
