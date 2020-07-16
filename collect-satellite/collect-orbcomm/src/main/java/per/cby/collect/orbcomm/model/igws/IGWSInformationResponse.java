package per.cby.collect.orbcomm.model.igws;

import java.time.LocalDateTime;
import java.util.List;

import per.cby.collect.orbcomm.model.igws.common.IgwsResult;
import per.cby.frame.common.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 网关信息响应
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class IGWSInformationResponse extends IgwsResult {

    /** 时间 */
    @JsonProperty("UTC")
    @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
    private LocalDateTime utc;

    /** 版本 */
    @JsonProperty("Version")
    private String version;

    /** 错误信息列表 */
    @JsonProperty("ErrorCodes")
    private List<ErrorInfo> errorCodes;

    /**
     * 错误信息
     * 
     * @author chenboyang
     * @since 2020年4月20日
     *
     */
    @Data
    @Accessors(chain = true)
    public static class ErrorInfo {

        /** 编号 */
        @JsonProperty("ID")
        private Long id;

        /** 名称 */
        @JsonProperty("Name")
        private String name;

        /** 描述 */
        @JsonProperty("Description")
        private String description;

    }

}
