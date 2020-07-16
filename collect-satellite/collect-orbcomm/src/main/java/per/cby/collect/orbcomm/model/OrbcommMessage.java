package per.cby.collect.orbcomm.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OGI消息记录
 * 
 * @author chenboyang
 * @since 2020年2月12日
 *
 */
@Data
@Accessors(chain = true)
public class OrbcommMessage {

    @JsonAlias("message_id")
    private String messageId;

    @JsonAlias("conf_num")
    private String confNum;

    @JsonAlias("message_direction")
    private String messageDirection;

    @JsonAlias("smtp_msg_id")
    private String smtpMsgId;

    @JsonAlias("message_time")
    private String messageTime;

    @JsonAlias("message_from")
    private String messageFrom;

    @JsonAlias("message_to")
    private String messageTo;

    @JsonAlias("message_priority")
    private String messagePriority;

    @JsonAlias("message_encoding")
    private String messageEncoding;

    @JsonAlias("message_body")
    private String messageBody;

    @JsonAlias("message_flag")
    private String messageFlag;

    @JsonAlias("message_status")
    private String messageStatus;

    @JsonAlias("delivered_failed_time")
    private String deliveredFailedTime;

    @JsonAlias("create_time")
    private String createTime;

}
