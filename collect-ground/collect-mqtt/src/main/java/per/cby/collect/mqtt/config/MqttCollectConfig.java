package per.cby.collect.mqtt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.mqtt.consumer.MqttIssuedConsumer;
import per.cby.collect.mqtt.listener.MqttIssuedListener;
import per.cby.collect.mqtt.publisher.MqttIssuedPublisher;
import per.cby.collect.mqtt.subscriber.FotaMqttReportSubscriber;
import per.cby.collect.mqtt.subscriber.MqttReportSubscriber;
import per.cby.frame.mqtt.MqttPublisherAdapter;
import com.rabbitmq.client.ConnectionFactory;

/**
 * MQTT采集配置
 * 
 * @author chenboyang
 *
 */
@Configuration("__MQTT_COLLECT_CONFIG__")
@ConditionalOnProperty("spring.mqtt.serverURIs")
public class MqttCollectConfig {

    @Value("${sys.business.project.id:$}")
    private String projectId;

    @Autowired(required = false)
    private MqttPublisherAdapter mqttPublisher;

    @Bean
    @ConditionalOnBean(ConnectionFactory.class)
    @ConditionalOnMissingBean(value = MqttIssuedConsumer.class, name = "mqttIssuedConsumer")
    public MqttIssuedConsumer mqttIssuedConsumer() {
        return new MqttIssuedConsumer();
    }

    @Bean
    @ConditionalOnMissingBean(value = MqttIssuedListener.class, name = "mqttIssuedListener")
    public MqttIssuedListener mqttIssuedListener() {
        return new MqttIssuedListener();
    }

    @Bean
    @ConditionalOnMissingBean(value = MqttIssuedPublisher.class, name = "mqttIssuedPublisher")
    public MqttIssuedPublisher mqttIssuedPublisher() {
        return new MqttIssuedPublisher(projectId, mqttPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(value = MqttReportSubscriber.class, name = "mqttReportSubscriber")
    public MqttReportSubscriber mqttReportSubscriber() {
        return new MqttReportSubscriber(projectId);
    }

    @Bean
    @ConditionalOnMissingBean(value = FotaMqttReportSubscriber.class, name = "fotaMqttReportSubscriber")
    public FotaMqttReportSubscriber fotaMqttReportSubscriber() {
        return new FotaMqttReportSubscriber(projectId);
    }

}
