package per.cby.collect.socket.config.properties;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 套接字配置属性
 * 
 * @author chenboyang
 * @since 2020年5月11日
 *
 */
@Data
@Accessors(chain = true)
public class SocketProperties {

    /** 套接字采集端口 */
    private int port = 20010;

    /** 空闲时长（单位：秒） */
    private int idleTime = 10;

    /** 套接字FOTA配置属性 */
    private FotaProperties fota = new FotaProperties();

    /**
     * 套接字FOTA配置属性
     * 
     * @author chenboyang
     * @since 2020年5月11日
     *
     */
    @Data
    @Accessors(chain = true)
    public static class FotaProperties {

        /** 套接字FOTA端口 */
        private int port = 20011;

        /** FOTA空闲时长（单位：秒） */
        private int idleTime = 20;

    }

}
