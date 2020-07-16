package per.cby.collect.mqtt.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;

import per.cby.collect.common.service.ReportService;
import per.cby.frame.mqtt.config.properties.SubscriberProperties;

/**
 * MQTT终端数据上报订阅者
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class MqttReportSubscriber extends AbstractMqttReportSubscriber {

    /** 任务前缀 */
    private final String TASK_PREFIX = MqttReportSubscriber.class.getName() + ".";

    @Autowired(required = false)
    private ReportService reportService;

    public MqttReportSubscriber(String projectId) {
        super(new SubscriberProperties().setClientId(TERMINAL_REPORT_TOPIC + projectId)
                .setTopic(new String[] { TERMINAL_REPORT_TOPIC + projectId + ALL_TOPIC }));
    }

    @Override
    protected void handleMessage(MessageHeaders headers, byte[] payload) {
        handleMessage(headers, payload, reportService::report);
    }

    @Override
    protected String taskPrefix() {
        return TASK_PREFIX;
    }

}
