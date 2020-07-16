package per.cby.collect.orbcomm.constant;

import per.cby.collect.satellite.common.constant.SatelliteConstant;

/**
 * OGI采集常量
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface OrbcommConstant extends SatelliteConstant {

    /** 查询时间范围限制 */
    long TIME_RANGE_LIMIT = 60 * 60;

    /** 获取终端消息接口 */
    String GET_MESSAGE = "/datas.html";

    /** 发送终端消息接口 */
    String SEND_MESSAGE = "/cooperation_send.html";

    /** 帐户名称参数名 */
    String NAME = "name";

    /** 签名参数名 */
    String SIGN = "sign";

    /** 开始时间参数名 */
    String START_TIME = "start_time";

    /** 结束时间参数名 */
    String END_TIME = "end_time";

    /** 设备编号参数名 */
    String DEVICE_ID = "device_id";

    /** 设备消息参数史 */
    String MSG = "msg";

    /** 供应商标识 */
    String VENDOR = "CIMC_TEST_ACCT";

}
