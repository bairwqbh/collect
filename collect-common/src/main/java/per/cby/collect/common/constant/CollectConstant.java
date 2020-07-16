package per.cby.collect.common.constant;

/**
 * 采集常量
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface CollectConstant {

    /** 采集网关 */
    String GATEWAY = "gateway";

    /** 终端上报 */
    String TERMINAL_REPORT = "terminal.report";

    /** 终端下发 */
    String TERMINAL_ISSUED = "terminal.issued";

    /** FOTA上报 */
    String FOTA_REPORT = "terminal.fota.report";

    /** 终端注册 */
    String TERMINAL_REGIST = "terminal.regist";

    /** FOTA标识长度 */
    int FOTA_FLAG_LENGTH = 2;

    /** FOTA报文头部标识 */
    int FOTA_HEAD = 0xFFAA;

    /** FOTA报文尾部标识 */
    int FOTA_TAIL = 0xAAFF;

}
