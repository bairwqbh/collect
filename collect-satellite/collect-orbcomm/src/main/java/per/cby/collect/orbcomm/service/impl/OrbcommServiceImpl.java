package per.cby.collect.orbcomm.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.ReportService;
import per.cby.collect.common.util.CollectUtil;
import per.cby.collect.orbcomm.client.Igws2Client;
import per.cby.collect.orbcomm.config.properties.OrbcommProperties;
import per.cby.collect.orbcomm.constant.ErrorCode;
import per.cby.collect.orbcomm.constant.OrbcommConstant;
import per.cby.collect.orbcomm.model.igws.ForwardSubmissionRequest;
import per.cby.collect.orbcomm.model.igws.GetReturnMessagesResult;
import per.cby.collect.orbcomm.model.igws.GetReturnMessagesResult.ReturnMessage;
import per.cby.collect.orbcomm.model.igws.SubmitMessagesResult;
import per.cby.collect.orbcomm.model.igws.common.ForwardMessage;
import per.cby.collect.orbcomm.model.igws.common.IgwsResult;
import per.cby.collect.orbcomm.service.OrbcommService;
import per.cby.collect.satellite.common.constant.SatelliteChannel;
import per.cby.frame.common.util.BaseUtil;
import per.cby.frame.common.util.DateUtil;
import per.cby.frame.common.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * OGI原厂接口服务实现
 * 
 * @author chenboyang
 * @since 2020年2月12日
 *
 */
@Slf4j
public class OrbcommServiceImpl implements OrbcommService, OrbcommConstant {

    @Autowired(required = false)
    private OrbcommProperties properties;

    @Autowired(required = false)
    private Igws2Client igws2Client;

    @Autowired(required = false)
    private ReportService reportService;

    @Override
    public void pollMessage(LocalDateTime startTime) {
        report(getMessage(startTime));
    }

    @Override
    public List<TerminalMessage> getMessage(LocalDateTime startTime) {
        List<TerminalMessage> list = BaseUtil.newArrayList();
        String startUtc = DateUtil.format(startTime.minusHours(8));
        GetReturnMessagesResult result = igws2Client.getReturnMessages(properties.getAccessId(),
                properties.getPassword(), startUtc, true, null, null);
        if (validate(result) && CollectionUtils.isNotEmpty(result.getMessages())) {
            log.info("收到OGI设备数据：{}", JsonUtil.toJson(result));
            result.getMessages().forEach(t -> {
                TerminalMessage message = reprotConvert(t);
                if (message != null) {
                    list.add(message);
                }
            });
        }
        return list;
    }

    @Override
    public SubmitMessagesResult sendMessage(TerminalMessage message) {
        log.info("发送OGI终端数据：{}", JsonUtil.toJson(message));
        ForwardSubmissionRequest request = new ForwardSubmissionRequest();
        request.setAccessId(properties.getAccessId());
        request.setPassword(properties.getPassword());
        request.setMessages(BaseUtil.newArrayList(issuedConvert(message)));
        SubmitMessagesResult result = igws2Client.submitMessages(request);
        if (CollectionUtils.isNotEmpty(result.getSubmissions())) {
            result.getSubmissions().forEach(t -> {
                if (t.getScheduledSendUtc() != null) {
                    t.setScheduledSendUtc(t.getScheduledSendUtc().plusHours(8));
                }
            });
        }
        if (validate(result)) {
            log.info("发送OGI终端数据成功：{}", JsonUtil.toJson(result));
        }
        return result;
    }

    /**
     * 校验返回结果
     * 
     * @param result 返回结果
     */
    private boolean validate(IgwsResult result) {
        if (result.getErrorId() == null) {
            return false;
        }
        ErrorCode error = ErrorCode.value(result.getErrorId());
        if (error != null && ErrorCode.NO_ERRORS.equals(error)) {
            return true;
        }
        String msg = error != null ? error.getDesc() : "OGI接口未知异常";
        log.warn(msg);
        return false;
    }

    /**
     * 上报消息转换
     * 
     * @param rm 上报消息
     * @return 终端消息
     */
    private TerminalMessage reprotConvert(ReturnMessage rm) {
        if (rm.getPayload() != null && "modemRegistration".equals(rm.getPayload().getName())) {
            return null;
        }
        if (ArrayUtils.isEmpty(rm.getRawPayload())) {
            return null;
        }
        TerminalMessage tm = TerminalMessage.create();
        tm.setImei(rm.getMobileId());
        tm.setChannel(SatelliteChannel.ORBCOMM.getCode());
        tm.setPayload(rm.getRawPayload());
        tm.setTimestamp(rm.getMessageUtc().plusHours(8));
        return tm;
    }

    /**
     * 下发消息转换
     * 
     * @param tm 终端消息
     * @return 下发消息
     */
    private ForwardMessage issuedConvert(TerminalMessage tm) {
        ForwardMessage fm = new ForwardMessage();
        fm.setDestinationId(tm.getImei());
        fm.setRawPayload(tm.getPayload());
        return fm;
    }

    /**
     * 上报消息
     * 
     * @param list 消息列表
     */
    private void report(List<TerminalMessage> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(message -> {
            if (CollectUtil.isFotaMsg(message.getPayload())) {
                reportService.fotaReport(message);
            } else {
                reportService.report(message);
            }
        });
    }

}
