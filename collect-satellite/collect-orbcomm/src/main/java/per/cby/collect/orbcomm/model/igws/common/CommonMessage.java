package per.cby.collect.orbcomm.model.igws.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 公共消息
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
public class CommonMessage {

    /** 字段列表 */
    @JsonProperty("Fields")
    private List<Field> fields;

    /** 名称 */
    @JsonProperty("Name")
    private String name;

    /** SIN */
    @JsonProperty("SIN")
    private Long sin;

    /** MIN */
    @JsonProperty("MIN")
    private Long min;

    /** 是否发送 */
    @JsonProperty("IsForward")
    private String isForward;

}
