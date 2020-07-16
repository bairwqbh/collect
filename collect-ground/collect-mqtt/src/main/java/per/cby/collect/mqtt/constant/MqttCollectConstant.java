package per.cby.collect.mqtt.constant;

import per.cby.collect.ground.common.constant.GroundConstant;

/**
 * MQTT采集常量
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface MqttCollectConstant extends GroundConstant {

    /** 全部主题 */
    String ALL_TOPIC = "/#";

    /** 终端上报主题 */
    String TERMINAL_REPORT_TOPIC = "terminal/report/";

    /** 终端下发主题 */
    String TERMINAL_ISSUED_TOPIC = "terminal/issued/";

    /** FOTA上报主题 */
    String FOTA_REPORT_TOPIC = "terminal/fota/report/";

    /** FOTA下发主题 */
    String FOTA_ISSUED_TOPIC = "terminal/fota/issued/";

}
