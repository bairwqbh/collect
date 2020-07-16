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
 * 获取下发消息状态信息结果
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class GetForwardStatusesResult extends IgwsResult {

    /** 下一个开始时间 */
    @JsonProperty("NextStartUTC")
    @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
    private LocalDateTime nextStartUtc;

    /** 广播信息列表 */
    @JsonProperty("Statuses")
    private List<ForwardStatus> statuses;

    /**
     * 消息下发状态信息
     * 
     * @author chenboyang
     * @since 2020年4月20日
     *
     */
    @Data
    @Accessors(chain = true)
    public static class ForwardStatus {

        /** 下发消息编号 */
        @JsonProperty("ForwardMessageID")
        private Long forwardMessageId;

        /** 消息是否关闭 */
        @JsonProperty("IsClosed")
        private Boolean isClosed;

        /** 发送消息状态 */
        @JsonProperty("State")
        private Long state;

        /** 最新状态时间 */
        @JsonProperty("StateUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime stateUtc;

        /** 错误代码 */
        @JsonProperty("ErrorID")
        private Long errorId;

        /** 消息参考号 */
        @JsonProperty("ReferenceNumber")
        private Long referenceNumber;

        /** 传输方式 */
        @JsonProperty("Transport")
        private String transport;

        /** 计划发送时间 */
        @JsonProperty("ScheduledSendUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime scheduledSendUtc;

        /** 地区名称 */
        @JsonProperty("RegionName")
        private String regionName;

    }

}
