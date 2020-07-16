package per.cby.collect.orbcomm.model.igws;

import java.util.List;

import per.cby.collect.orbcomm.model.igws.common.ForwardMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 下发消息请求
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
public class ForwardSubmissionRequest {

    /** 访问账号 */
    @JsonProperty("access_id")
    private String accessId;
    
    /** 密码 */
    private String password;

    /** 发送消息列表 */
    private List<ForwardMessage> messages;

}
