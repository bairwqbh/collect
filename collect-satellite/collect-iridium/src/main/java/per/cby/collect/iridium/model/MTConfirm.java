package per.cby.collect.iridium.model;

import per.cby.collect.iridium.constant.MTStatus;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 下发消息确认信息
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Data
@Accessors(chain = true)
public class MTConfirm {

    /** 消息编号 */
    private String msgid;

    /** 终端模组编号 */
    private String imei;

    /** 自动流水号 */
    private long autoId;

    /** 下发状态 */
    private MTStatus status;

}
