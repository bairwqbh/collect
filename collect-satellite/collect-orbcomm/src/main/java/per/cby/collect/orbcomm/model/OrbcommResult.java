package per.cby.collect.orbcomm.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OGI接口返回结果
 * 
 * @author chenboyang
 * @since 2020年2月12日
 *
 */
@Data
@Accessors(chain = true)
public class OrbcommResult {

    /** 状态 */
    private int status;

    /** 消息 */
    private Object msg;

}
