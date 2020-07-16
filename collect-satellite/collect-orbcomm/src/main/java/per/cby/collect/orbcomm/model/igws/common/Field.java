package per.cby.collect.orbcomm.model.igws.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字段
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
public class Field {

    /** 元素列表 */
    @JsonProperty("Elements")
    private List<Element> elements;

    /** 封装消息 */
    @JsonProperty("Message")
    private CommonMessage message;

    /** 名称 */
    @JsonProperty("Name")
    private String name;

    /** 值 */
    @JsonProperty("Value")
    private String value;

    /** 数据类型 */
    @JsonProperty("Type")
    private String type;

}
