package per.cby.collect.orbcomm.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 发送消息状态
 * 
 * @author chenboyang
 * @since 2020年2月21日
 *
 */
@Getter
@RequiredArgsConstructor
public enum SendMsgStatus {

    SUCCESS(1, "成功"),
    NO_DATA(0, "暂无数据"),
    NAME_ERROR(-1, "账号错误"),
    SIGN_ERROR(-2, "签名错误"),
    NO_IMEI(-3, "无模组编号"),
    NO_PAYLOAD(-4, "无载荷数据");

    private final int code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static SendMsgStatus value(int code) {
        for (SendMsgStatus value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
