package per.cby.collect.orbcomm.model.igws;

import java.time.LocalDateTime;
import java.util.List;

import per.cby.collect.orbcomm.model.igws.common.IgwsResult;
import per.cby.frame.common.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 提交下发消息结果
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SubmitMessagesResult extends IgwsResult {

    /** 发送提交列表 */
    @JsonProperty("Submissions")
    private List<ForwardSubmission> submissions;

    /**
     * 发送提交信息
     * 
     * @author chenboyang
     * @since 2020年4月20日
     *
     */
    @Data
    @Accessors(chain = true)
    public static class ForwardSubmission {

        /** 下发消息编号 */
        @JsonProperty("ForwardMessageID")
        private Long forwardMessageId;

        /** 目的地编号 */
        @JsonProperty("DestinationID")
        private String destinationId;

        /** 错误代码 */
        @JsonProperty("ErrorID")
        private Long errorId;

        /** 消息编号 */
        @JsonProperty("UserMessageID")
        private Long userMessageId;

        /** 计划发送时间 */
        @JsonProperty("ScheduledSendUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime scheduledSendUtc;

        /** 终端唤醒时间 */
        @JsonProperty("TerminalWakeupPeriod")
        private Long terminalWakeupPeriod;
        
        /** 消息长度 */
        @JsonProperty("OTAMessageSize")
        private Long otaMessageSize;

    }

}
