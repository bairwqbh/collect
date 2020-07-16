package per.cby.collect.iridium.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 下发消息元素项
 * 
 * @author chenboyang
 * @since 2020年2月121日
 *
 */
@Getter
@RequiredArgsConstructor
public enum MTElement {

    MT_HEADER(0x41, "MT消息头"),
    MT_PAYLOAD(0x42, "MT载荷"),
    MT_CONFIRMATION(0x44, "MT确认信息"),
    MT_PRIORITY(0x46, "MT消息优先级");

    private final int code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static MTElement value(int code) {
        for (MTElement value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
