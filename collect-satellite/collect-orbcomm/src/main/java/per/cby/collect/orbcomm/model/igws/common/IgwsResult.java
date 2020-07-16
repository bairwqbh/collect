package per.cby.collect.orbcomm.model.igws.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * IGWS网关返回基础对象
 * 
 * @author chenboyang
 * @since 2020年4月21日
 *
 */
@Data
@Accessors(chain = true)
public abstract class IgwsResult {

    /** 错误代码 */
    @JsonProperty("ErrorID")
    protected Integer errorId;

}
