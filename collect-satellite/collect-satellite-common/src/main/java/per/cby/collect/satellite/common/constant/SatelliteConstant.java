package per.cby.collect.satellite.common.constant;

import per.cby.collect.common.constant.CollectConstant;

/**
 * 卫星采集常量
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface SatelliteConstant extends CollectConstant {

    /** IMEI数据长度 */
    int IMEI_LENGTH = 15;

    /** 终端卫星平台下发 */
    String TERMINAL_ISSUED_ECSAT = TERMINAL_ISSUED + ".ecsat";

    /** 铱星下发 */
    String TERMINAL_ISSUED_IRIDIUM = TERMINAL_ISSUED + ".iridium";

    /** OGI下发 */
    String TERMINAL_ISSUED_ORBCOMM = TERMINAL_ISSUED + ".orbcomm";

    /** 北斗下发 */
    String TERMINAL_ISSUED_BEIDOU = TERMINAL_ISSUED + ".beidou";

    /** 天通下发 */
    String TERMINAL_ISSUED_TIANTONG = TERMINAL_ISSUED + ".tiantong";

}
