package per.cby.collect.mqtt.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import per.cby.collect.ground.common.event.MqttFotaIssuedEvent;
import per.cby.collect.ground.common.event.MqttIssuedEvent;
import per.cby.collect.mqtt.publisher.MqttIssuedPublisher;

/**
 * 终端MQTT下发监听器
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class MqttIssuedListener {

    @Autowired(required = false)
    private MqttIssuedPublisher mqttIssuedPublisher;

    @Async
    @EventListener(MqttIssuedEvent.class)
    public void issued(MqttIssuedEvent event) {
        mqttIssuedPublisher.publish(event.getSource());
    }

    @Async
    @EventListener(MqttFotaIssuedEvent.class)
    public void fotaIssued(MqttFotaIssuedEvent event) {
        mqttIssuedPublisher.fotaPublish(event.getSource());
    }

}
