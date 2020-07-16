package per.cby.collect.beidou.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 北斗AT指令集
 * 
 * @author chenboyang
 * @since 2020年2月28日
 *
 */
@Getter
@RequiredArgsConstructor
public enum ReplyCommand implements BeidouConstant {

    SEND_OK("SEND OK\r\n", "发送成功"),
    SEND_ERROR("SEND ERROR\r\n", "发送失败"),
    RDSS("RDSS,+", "接收数据");

    private final String code;
    private final String desc;

    /**
     * 获取指令字节数组
     * 
     * @return 字节数组
     */
    public byte[] getBytes() {
        return code.getBytes(CHARSET);
    }

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static ReplyCommand value(String code) {
        for (ReplyCommand value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
