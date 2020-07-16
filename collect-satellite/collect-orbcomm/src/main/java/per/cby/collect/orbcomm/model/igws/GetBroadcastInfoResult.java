package per.cby.collect.orbcomm.model.igws;

import java.util.List;

import per.cby.collect.orbcomm.model.igws.common.IgwsResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 获取广播信息结果
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class GetBroadcastInfoResult extends IgwsResult {

    /** 广播信息列表 */
    @JsonProperty("BroadcastInfos")
    private List<BroadcastInfo> broadcastInfos;

    /**
     * 广播信息
     * 
     * @author chenboyang
     * @since 2020年4月20日
     *
     */
    @Data
    @Accessors(chain = true)
    public static class BroadcastInfo {

        /** 编号 */
        @JsonProperty("ID")
        private Long id;

        /** 描述 */
        @JsonProperty("Description")
        private String description;

    }
}
