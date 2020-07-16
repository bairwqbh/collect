package per.cby.collect.orbcomm.model.igws.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 下发消息
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
public class ForwardMessage {

    /** 目的地编号 */
    @JsonProperty("DestinationID")
    private String destinationId;

    /** 消息编号 */
    @JsonProperty("UserMessageID")
    private Long userMessageId;

    /** 原始载荷 */
    @JsonProperty("RawPayload")
    private byte[] rawPayload;

    /** 封装载荷 */
    @JsonProperty("Payload")
    private CommonMessage payload;

    /** 传输类型 */
    @JsonProperty("TransportType")
    private String transportType;

    /** 发送操作 */
    @JsonProperty("SendOptions")
    private DelayedSend sendOptions;

}
