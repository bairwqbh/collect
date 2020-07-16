package per.cby.collect.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 采集网关类型
 * 
 * @author chenboyang
 * @since 2020年3月24日
 *
 */
@Getter
@RequiredArgsConstructor
public enum GatewayType {

    MQTT("mqtt", "MQTT"),
    COAP("coap", "CoAP"),
    SOCKET("socket", "Socket"),
    ECSAT("ecsat", "卫星平台"),
    IRIDIUM("iridium", "铱星"),
    ORBCOMM("orbcomm", "海事"),
    BEIDOU("beidou", "北斗"),
    TIANTONG("tiantong", "天通");

    private final String code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static GatewayType value(String code) {
        for (GatewayType value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
