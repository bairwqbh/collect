package per.cby.collect.mqtt.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;

import per.cby.collect.common.service.ReportService;
import per.cby.frame.mqtt.config.properties.SubscriberProperties;

/**
 * 终端FOTA升级MQTT上报订阅者
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class FotaMqttReportSubscriber extends AbstractMqttReportSubscriber {

    /** 任务前缀 */
    private final String TASK_PREFIX = FotaMqttReportSubscriber.class.getName() + ".";

    @Autowired(required = false)
    private ReportService reportService;

    public FotaMqttReportSubscriber(String projectId) {
        super(new SubscriberProperties().setClientId(FOTA_ISSUED_TOPIC + projectId)
                .setTopic(new String[] { FOTA_ISSUED_TOPIC + projectId + ALL_TOPIC }));
    }

    @Override
    protected void handleMessage(MessageHeaders headers, byte[] payload) {
        handleMessage(headers, payload, reportService::fotaReport);
    }

    @Override
    protected String taskPrefix() {
        return TASK_PREFIX;
    }

}
