package per.cby.collect.orbcomm.config.properties;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OGI天佑配置属性
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Data
@Accessors(chain = true)
public class TianyouProperties {

    /** OGI服务器接口地址 */
    private String url = "http://123.58.32.88/starchat/index.php/Business/Coop";

    /** OGI服务器账户名称 */
    private String name = "jizhi";

    /** OGI服务器密钥 */
    private String key = "82e72454d22ce82781ae9e30ea0d4315";

}
