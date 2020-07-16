package per.cby.collect.beidou.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import per.cby.collect.satellite.common.constant.SatelliteConstant;

/**
 * 北斗采集常量
 * 
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public interface BeidouConstant extends SatelliteConstant {

    /** 字符集编码 */
    Charset CHARSET = StandardCharsets.ISO_8859_1;

}
