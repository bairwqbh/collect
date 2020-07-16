package per.cby.collect.iridium.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 下发消息状态
 * 
 * @author chenboyang
 * @since 2020年2月121日
 *
 */
@Getter
@RequiredArgsConstructor
public enum MTStatus {

    MT_SUCCESS(50, "成功，MT消息队列中的消息顺序"),
    MT_NO_PAYLOAD(0, "成功，消息中没有有效载荷"),
    MT_INVALID_IMEI(-1, "无效的IMEI –字符太少，非数字字符"),
    MT_UNKNOWN_IMEI(-2, "未知的IMEI –在Iridium网关上未配置"),
    MT_PAYLOAD_OVERLOAD(-3, "有效负载大小超出了允许的最大值"),
    MT_PAYLOAD_NO_RECEIVED(-4, "有效负载预期，但未收到"),
    MT_QUEUE_FULL(-5, "MT邮件队列已满（最多50个）"),
    MT_RESOURCES_UNAVAILABLE(-6, "MT资源不可用"),
    MT_PROTOCOL_ERROR(-7, "违反MT DirectIP协议错误"),
    MT_IMEI_DISABLED(-8, "到给定IMEI的铃声警报已禁用"),
    MT_IMEI_NO_RECEIVE(-9, "未附加给定的IMEI（未设置为接收响铃警报）"),
    MT_REJECTED_ADDRESS(-10, "MT过滤器拒绝源IP地址"),
    MT_MTMSN_OUT_RANGE(-11, "MTMSN值超出范围（有效范围是1 – 65,535）");

    private final int code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static MTStatus value(int code) {
        if (code > MT_NO_PAYLOAD.getCode() && code <= MT_SUCCESS.getCode()) {
            return MT_SUCCESS;
        }
        for (MTStatus value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
