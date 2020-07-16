package per.cby.collect.orbcomm.model.igws;

import per.cby.collect.orbcomm.model.igws.common.ForwardMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 多目的地下发消息请求
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
public class MultiDestinationSubmissionRequest {

    /** 访问账号 */
    @JsonProperty("access_id")
    private String accessId;

    /** 密码 */
    private String password;

    /** 目的地编号集(以逗号分隔) */
    private String destinations;

    /** 发送消息 */
    private ForwardMessage message;

}
