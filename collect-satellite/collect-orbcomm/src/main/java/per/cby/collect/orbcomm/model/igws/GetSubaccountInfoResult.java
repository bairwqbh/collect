package per.cby.collect.orbcomm.model.igws;

import java.util.List;

import per.cby.collect.orbcomm.model.igws.common.IgwsResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 获取子账户信息结果
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class GetSubaccountInfoResult extends IgwsResult {

    /** 子账户列表 */
    @JsonProperty("Subaccounts")
    private List<SubaccountInfo> subaccounts;

    /**
     * 子账户信息
     * 
     * @author chenboyang
     * @since 2020年4月20日
     *
     */
    @Data
    @Accessors(chain = true)
    public static class SubaccountInfo {

        /** 账户编号 */
        @JsonProperty("AccountID")
        private String accountId;

        /** 账户名称 */
        @JsonProperty("Name")
        private String name;

    }

}
