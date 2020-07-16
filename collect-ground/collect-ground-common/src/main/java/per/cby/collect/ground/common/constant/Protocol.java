package per.cby.collect.ground.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 通信协议
 * 
 * @author chenboyang
 * @since 2019年12月10日
 *
 */
@Getter
@RequiredArgsConstructor
public enum Protocol {

    SOCKET("socket", "TCP传输协议"),
    MQTT("mqtt", "MQTT传输协议"),
    COAP("coap", "CoAP传输协议");

    private final String code;
    private final String desc;

}
