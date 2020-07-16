package per.cby.collect.orbcomm.model.igws;

import java.time.LocalDateTime;
import java.util.List;

import per.cby.collect.orbcomm.model.igws.common.CommonMessage;
import per.cby.collect.orbcomm.model.igws.common.IgwsResult;
import per.cby.frame.common.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 获取下发消息信息结果
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class GetForwardMessagesResult extends IgwsResult {

    /** 广播信息列表 */
    @JsonProperty("Messages")
    private List<ForwardMessageRecord> messages;

    /**
     * 消息信息记录
     * 
     * @author chenboyang
     * @since 2020年4月20日
     *
     */
    @Data
    @Accessors(chain = true)
    public static class ForwardMessageRecord {

        /** 编号 */
        @JsonProperty("ID")
        private Long id;

        /** 最新状态时间 */
        @JsonProperty("StateUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime stateUtc;

        /** 创建时间 */
        @JsonProperty("CreateUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime createUtc;

        /** 消息是否关闭 */
        @JsonProperty("IsClosed")
        private Boolean isClosed;

        /** 发送消息状态 */
        @JsonProperty("State")
        private Long state;

        /** 目的地编号 */
        @JsonProperty("DestinationID")
        private String destinationId;

        /** 错误代码 */
        @JsonProperty("ErrorID")
        private Long errorId;

        /** 原始载荷 */
        @JsonProperty("RawPayload")
        private byte[] rawPayload;

        /** 封装载荷 */
        @JsonProperty("Payload")
        private CommonMessage payload;

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
