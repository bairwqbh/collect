package per.cby.collect.orbcomm.model.igws.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 元素
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
public class Element {

    /** 字段列表 */
    @JsonProperty("Fields")
    private List<Field> fields;

    /** 元素下标 */
    @JsonProperty("Index")
    private Long index;

}
