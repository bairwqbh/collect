package per.cby.collect.orbcomm.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 获取终端消息状态
 * 
 * @author chenboyang
 * @since 2020年2月21日
 *
 */
@Getter
@RequiredArgsConstructor
public enum GetMsgStatus {

    SUCCESS(1, "成功"),
    NO_DATA(0, "暂无数据"),
    NAME_ERROR(-1, "账号错误"),
    SIGN_ERROR(-2, "签名错误"),
    NO_START_TIME(-3, "无开始时间"),
    NO_END_TIME(-4, "无结束时间"),
    QUERY_OUT_RANGE(-5, "查找超出范围，大于1小时");

    private final int code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static GetMsgStatus value(int code) {
        for (GetMsgStatus value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
