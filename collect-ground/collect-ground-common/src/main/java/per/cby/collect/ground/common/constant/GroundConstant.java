package per.cby.collect.ground.common.constant;

import per.cby.collect.common.constant.CollectConstant;

/**
 * 地面网采集常量
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface GroundConstant extends CollectConstant {

    /** 终端MQTT下发 */
    String TERMINAL_ISSUED_MQTT = TERMINAL_ISSUED + ".mqtt";

    /** 终端CoAP下发 */
    String TERMINAL_ISSUED_COAP = TERMINAL_ISSUED + ".coap";

    /** 终端套接字下发 */
    String TERMINAL_ISSUED_SOCKET = TERMINAL_ISSUED + ".socket";

    /** FOTA下发 */
    String FOTA_ISSUED = "terminal.issued.fota";

    /** FOTA下发MQTT */
    String FOTA_ISSUED_MQTT = FOTA_ISSUED + ".mqtt";

    /** 终端唯一标识名称 */
    String SN = "sn";

    /** 下发类型字段名 */
    String ISSUED_TYPE = "issuedType";

    /**
     * 采集通道
     * 
     * @author chenboyang
     * @since 2019年10月29日
     *
     */
    public interface Channel {

        /** MQTT通道 */
        String MQTT = "mqtt";

        /** CoAP通道 */
        String COAP = "coap";

    }

}
