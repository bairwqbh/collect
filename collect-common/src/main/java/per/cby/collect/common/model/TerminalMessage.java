package per.cby.collect.common.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import per.cby.frame.common.util.BaseUtil;
import per.cby.frame.common.util.IDUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 终端消息
 * 
 * @author chenboyang
 * @since 2019年9月9日
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TerminalMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 消息编号 */
    private String messageId;

    /** 终端编号 */
    private String terminalId;

    /** 模组编号 */
    private String imei;

    /** 传输通道 */
    private String channel;

    /** 载荷 */
    private byte[] payload;

    /** 时间戳 */
    private LocalDateTime timestamp;

    /** 消息头部参数 */
    private Map<String, Object> header = BaseUtil.newHashMap();

    /**
     * 创建终端消息
     * 
     * @return 终端消息
     */
    public static TerminalMessage create() {
        return new TerminalMessage().setMessageId(IDUtil.createUUID32()).setTimestamp(LocalDateTime.now());
    }

}
