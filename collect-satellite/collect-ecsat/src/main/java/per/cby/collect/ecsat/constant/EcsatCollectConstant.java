package per.cby.collect.ecsat.constant;

import per.cby.collect.satellite.common.constant.SatelliteConstant;

/**
 * MQTT采集常量
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface EcsatCollectConstant extends SatelliteConstant {

    /** 上报主题 */
    String REPORT_TOPIC = "ecsat/report/internal/";

    /** 下发主题 */
    String ISSUED_TOPIC = "ecsat/issued/internal/";

}
