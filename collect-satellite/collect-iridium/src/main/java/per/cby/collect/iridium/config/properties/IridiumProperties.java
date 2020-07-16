package per.cby.collect.iridium.config.properties;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 铱星配置属性
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Data
@Accessors(chain = true)
public class IridiumProperties {

    /** 数据接收端口 */
    private int receivePort = 20002;

    /** 铱星服务器IP地址 */
    private String host = "directip.sbd.iridium.com";

    /** 铱星服务器端口 */
    private int port = 10800;

}
