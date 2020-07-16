package per.cby.collect.iridium.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 上报会话状态
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Getter
@RequiredArgsConstructor
public enum MOSessionStatus {

    MO_SUCCESS(0, "SBD会话成功完成。"),
    MO_QUEUED_TOO_LARGE(1, "MO消息传输（如果有）成功。 铱星网关上排队的MT消息太大，无法在单个SBD会话中传输。"),
    MO_LOCATION_DETERMINED(2, "MO消息传输（如果有）成功。 报告的位置被确定为不可接受的质量。 该值仅适用于使用SBD协议修订版1的IMEI。"),
    MO_SESSION_TIMEOUT(10, "SBD会话在会话完成之前超时。"),
    MO_MESSAGE_TOO_LARGE(12, "IMEI传输的MO消息太大，无法在单个SBD会话中传输。"),
    MO_RFLINK_LOSS(13, "在SBD会话期间发生RF链路丢失。"),
    MO_IMEI_PROTOCOL_ANOMALY(14, "SBD会话期间发生IMEI协议异常。"),
    MO_IMEI_PROHIBITED(15, "禁止IMEI访问Iridium网关。");

    private final int code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static MOSessionStatus value(int code) {
        for (MOSessionStatus value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
