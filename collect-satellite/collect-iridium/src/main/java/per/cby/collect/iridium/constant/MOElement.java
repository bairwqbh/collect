package per.cby.collect.iridium.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 上报消息元素项
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Getter
@RequiredArgsConstructor
public enum MOElement {

    MO_HEADER(0x01, "MO消息头"),
    MO_PAYLOAD(0x02, "MO载荷"),
    MO_LOCATION_INFORMATION(0x03, "MO定位信息"),
    MO_CONFIRMATION(0x05, "MO确认信息");

    private final int code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static MOElement value(int code) {
        for (MOElement value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
