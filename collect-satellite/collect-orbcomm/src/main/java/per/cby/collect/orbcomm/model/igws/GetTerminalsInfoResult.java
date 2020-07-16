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
 * 获取终端信息结果
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class GetTerminalsInfoResult extends IgwsResult {

    /** 终端信息列表 */
    @JsonProperty("Terminals")
    private List<TerminalInfo> terminals;

    /**
     * 终端信息
     * 
     * @author chenboyang
     * @since 2020年4月20日
     *
     */
    @Data
    @Accessors(chain = true)
    public static class TerminalInfo {

        /** 主键 */
        @JsonProperty("PrimeID")
        private String primeId;

        /** 描述 */
        @JsonProperty("Description")
        private String description;

        /** 最后注册时间 */
        @JsonProperty("LastRegistrationUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime lastRegistrationUtc;

        /** 地区名称 */
        @JsonProperty("RegionName")
        private String regionName;

        /** 序列号 */
        @JsonProperty("MTSN")
        private String mtsn;

        /** 模组编号 */
        @JsonProperty("IMEI")
        private String imei;

        /** 移动设备识别码 */
        @JsonProperty("MEID")
        private String meid;

        /** MAC地址 */
        @JsonProperty("MAC")
        private String mac;

        /** 配对主键 */
        @JsonProperty("PairedTerminalPrimeID")
        private String pairedTerminalPrimeId;

        /** 最后MTBP通信时间 */
        @JsonProperty("LastMTBPUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime lastMtbpUtc;

        /** 最后MTWS通信时间 */
        @JsonProperty("LastMTWSUTC")
        @JsonFormat(pattern = DateUtil.DATETIME_FORMAT)
        private LocalDateTime lastMtwsUtc;
    }

}
