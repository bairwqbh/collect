package per.cby.collect.orbcomm.model.igws.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 延迟发送
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
public class DelayedSend {

    /** 卫星发送接收 */
    @JsonProperty("SatelliteSendOnReceive")
    private Boolean satelliteSendOnReceive;

    /** 消息过期时间 */
    @JsonProperty("MessageExpireUTC")
    private String messageExpireUtc;

}
