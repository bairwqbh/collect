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
 * 获取上报消息信息结果
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class GetReturnMessagesResult extends IgwsResult {

    /** 下一个开始时间 */
    @JsonProperty("NextStartUTC")
    @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
    private LocalDateTime nextStartUtc;

    /** 上报消息 */
    @JsonProperty("Messages")
    private List<ReturnMessage> messages;

    /**
     * 消息信息记录
     * 
     * @author chenboyang
     * @since 2020年4月20日
     *
     */
    @Data
    @Accessors(chain = true)
    public static class ReturnMessage {

        /** 编号 */
        @JsonProperty("ID")
        private Long id;

        /** 消息时间 */
        @JsonProperty("MessageUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime messageUtc;

        /** 接收时间 */
        @JsonProperty("ReceiveUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime receiveUtc;

        /** SIN */
        @JsonProperty("SIN")
        private Long sin;

        /** 终端编号 */
        @JsonProperty("MobileID")
        private String mobileId;

        /** 原始载荷 */
        @JsonProperty("RawPayload")
        private byte[] rawPayload;

        /** 封装载荷 */
        @JsonProperty("Payload")
        private CommonMessage payload;

        /** 地区名称 */
        @JsonProperty("RegionName")
        private String regionName;

        /** 消息长度 */
        @JsonProperty("OTAMessageSize")
        private Long otaMessageSize;

        /** 客户编号 */
        @JsonProperty("CustomerID")
        private Long customerId;

        /** 传输方式 */
        @JsonProperty("Transport")
        private Long transport;

    }

}
