package per.cby.collect.orbcomm.config.properties;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OGI原厂配置属性
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Data
@Accessors(chain = true)
public class OrbcommProperties {

    /** 访问账户 */
    private String accessId = "70002729";

    /** 密码 */
    private String password = "CCQCVLSO";

}
