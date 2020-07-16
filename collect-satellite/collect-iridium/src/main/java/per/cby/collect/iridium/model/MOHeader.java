package per.cby.collect.iridium.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上报消息的头部信息
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Data
@Accessors(chain = true)
public class MOHeader {

    /** 唯一编号 */
    private long cdr;

    /** 终端模组编号 */
    private String imei;

    /** 会话状态 */
    private short status;

    /** 上报消息序列号 */
    private int momsn;

    /** 下发消息序列号 */
    private int mtmsn;

    /** 会话时间戳 */
    private long time;

}
