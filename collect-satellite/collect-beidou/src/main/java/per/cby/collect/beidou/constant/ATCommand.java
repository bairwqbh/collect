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
public enum ATCommand implements BeidouConstant {

    AT_SEND("AT+SEND=", "AT发送指令");

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
    public static ATCommand value(String code) {
        for (ATCommand value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
