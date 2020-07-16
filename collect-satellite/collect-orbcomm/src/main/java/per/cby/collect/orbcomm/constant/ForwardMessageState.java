package per.cby.collect.orbcomm.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 传输消息状态
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Getter
@RequiredArgsConstructor
public enum ForwardMessageState {

    SUCCESS(0, "网关已接受并将消息排队。"),
    NO_DATA(1, "网关收到终端已收到消息的确认。"),
    NAME_ERROR(2, "消息由于错误而关闭。错误编号指定原因。"),
    SIGN_ERROR(3, "网关无法将消息传递到终端。错误编号指定原因。"),
    NO_START_TIME(4, "邮件无法在120分钟内传递。"),
    NO_END_TIME(5, "消息已被客户端应用程序成功取消。"),
    QUERY_OUT_RANGE(6, "已发送带有延迟选项的消息，并且在满足条件时将排队等待发送"),
    QUERY_OUT_RANGE7(7, "不适用"),
    QUERY_OUT_RANGE8(8, "发送广播消息。");

    private final int code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static ForwardMessageState value(int code) {
        for (ForwardMessageState value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
