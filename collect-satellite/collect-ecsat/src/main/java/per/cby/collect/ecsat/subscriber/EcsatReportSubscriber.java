package per.cby.collect.ecsat.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;

import per.cby.collect.common.constant.GatewayType;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.ReportService;
import per.cby.collect.ecsat.constant.EcsatCollectConstant;
import per.cby.frame.common.util.JsonUtil;
import per.cby.frame.mqtt.GenericMqttSubscriber;
import per.cby.frame.mqtt.config.properties.SubscriberProperties;
import per.cby.frame.task.scheduler.DistributeTaskScheduler;

import lombok.extern.slf4j.Slf4j;

/**
 * 卫星平台终端数据上报订阅者
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
@Slf4j
public class EcsatReportSubscriber extends GenericMqttSubscriber<TerminalMessage> implements EcsatCollectConstant {

    /** 任务前缀 */
    private final String TASK_PREFIX = EcsatReportSubscriber.class.getName() + ".";

    @Autowired(required = false)
    private ReportService reportService;

    @Autowired(required = false)
    protected DistributeTaskScheduler distributeTaskScheduler;

    public EcsatReportSubscriber(String projectId) {
        super(new SubscriberProperties().setClientId(REPORT_TOPIC + projectId)
                .setTopic(new String[] { REPORT_TOPIC + projectId }));
    }

    @Override
    protected void handleMessage(MessageHeaders headers, TerminalMessage payload) {
        log.info("收到卫星平台上报数据：{}", JsonUtil.toJson(payload));
        payload.getHeader().put(GATEWAY, GatewayType.ECSAT.getCode());
        distributeTaskScheduler.schedule(taskName(payload), () -> reportService.report(payload));
    }

    /**
     * 获取任务名称
     * 
     * @param payload 消息载荷
     * @return 任务名称
     */
    private String taskName(TerminalMessage payload) {
        return TASK_PREFIX + payload.getTerminalId() + "." + payload.getTimestamp().toString();
    }

}
