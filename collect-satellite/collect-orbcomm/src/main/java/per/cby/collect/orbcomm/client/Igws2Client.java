package per.cby.collect.orbcomm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import per.cby.collect.orbcomm.model.igws.ForwardSubmissionRequest;
import per.cby.collect.orbcomm.model.igws.GetBroadcastInfoResult;
import per.cby.collect.orbcomm.model.igws.GetForwardMessagesResult;
import per.cby.collect.orbcomm.model.igws.GetForwardStatusesResult;
import per.cby.collect.orbcomm.model.igws.GetReturnMessagesResult;
import per.cby.collect.orbcomm.model.igws.GetSubaccountInfoResult;
import per.cby.collect.orbcomm.model.igws.GetTerminalsInfoResult;
import per.cby.collect.orbcomm.model.igws.IGWSInformationResponse;
import per.cby.collect.orbcomm.model.igws.MultiDestinationSubmissionRequest;
import per.cby.collect.orbcomm.model.igws.SubmitMessagesResult;

/**
 * 海事卫星IGWS2接口
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@FeignClient(name = "igws2Client", url = "https://isatdatapro.orbcomm.com/GLGW/2/RestMessages.svc/JSON")
public interface Igws2Client {

    /**
     * 获取网关信息
     * 
     * @param accessId      访问账号
     * @param password      密码
     * @param getErrorCodes 是否获取错误代码列表
     * @return 网关信息
     */
    @GetMapping("/igws_information")
    IGWSInformationResponse igwsInformation(@RequestParam("access_id") String accessId,
            @RequestParam("password") String password, @RequestParam("get_error_codes") Boolean getErrorCodes);

    /**
     * 获取终端信息
     * 
     * @param accessId     访问账号
     * @param password     密码
     * @param subaccountId 子账号
     * @param sinceId      起始编号
     * @param pageSize     分页大小
     * @return 终端信息
     */
    @GetMapping("/get_terminals_info")
    GetTerminalsInfoResult getTerminalsInfo(@RequestParam("access_id") String accessId,
            @RequestParam("password") String password, @RequestParam("subaccount_id") String subaccountId,
            @RequestParam("since_id") String sinceId, @RequestParam("page_size") Integer pageSize);

    /**
     * 获取广播信息
     * 
     * @param accessId     访问账号
     * @param password     密码
     * @param subaccountId 子账号
     * @return 广播信息
     */
    @GetMapping("/get_broadcast_info")
    GetBroadcastInfoResult getBroadcastInfo(@RequestParam("access_id") String accessId,
            @RequestParam("password") String password, @RequestParam("subaccount_id") String subaccountId);

    /**
     * 获取子账户信息
     * 
     * @param accessId 访问账号
     * @param password 密码
     * @return 子账户信息
     */
    @GetMapping("/get_subaccount_info")
    GetSubaccountInfoResult getSubaccountInfo(@RequestParam("access_id") String accessId,
            @RequestParam("password") String password);

    /**
     * 获取下发消息状态信息
     * 
     * @param accessId     访问账号
     * @param password     密码
     * @param startUtc     开始时间
     * @param fwIds        消息编号集(以逗号分隔)
     * @param subaccountId 子账号
     * @return 下发消息状态信息
     */
    @GetMapping("/get_forward_statuses")
    GetForwardStatusesResult getForwardStatuses(@RequestParam("access_id") String accessId,
            @RequestParam("password") String password, @RequestParam("start_utc") String startUtc,
            @RequestParam("fwIDs") String fwIds, @RequestParam("subaccount_id") String subaccountId);

    /**
     * 获取下发消息信息
     * 
     * @param accessId     访问账号
     * @param password     密码
     * @param fwIds        消息编号集(以逗号分隔)
     * @param subaccountId 子账号
     * @return 下发消息信息
     */
    @GetMapping("/get_forward_messages")
    GetForwardMessagesResult getForwardMessages(@RequestParam("access_id") String accessId,
            @RequestParam("password") String password, @RequestParam("fwIDs") String fwIds,
            @RequestParam("subaccount_id") String subaccountId);

    /**
     * 获取上报消息信息
     * 
     * @param accessId          访问账号
     * @param password          密码
     * @param startUtc          开始时间
     * @param includeRawPayload 是否包含原始载荷
     * @param subaccountId      子账号
     * @param includeType       是否包含封装载荷
     * @return 上报消息信息
     */
    @GetMapping("/get_return_messages")
    GetReturnMessagesResult getReturnMessages(@RequestParam("access_id") String accessId,
            @RequestParam("password") String password, @RequestParam("start_utc") String startUtc,
            @RequestParam("include_raw_payload") Boolean includeRawPayload,
            @RequestParam("subaccount_id") String subaccountId, @RequestParam("include_type") Boolean includeType);

    /**
     * 获取子账号上报消息信息
     * 
     * @param accessId          访问账号
     * @param password          密码
     * @param startUtc          开始时间
     * @param includeRawPayload 是否包含原始载荷
     * @param includeType       是否包含封装载荷
     * @return 上报消息信息
     */
    @GetMapping("/get_subaccounts_return_messages")
    GetReturnMessagesResult getSubaccountsReturnMessages(@RequestParam("access_id") String accessId,
            @RequestParam("password") String password, @RequestParam("start_utc") String startUtc,
            @RequestParam("include_raw_payload") Boolean includeRawPayload,
            @RequestParam("include_type") Boolean includeType);

    /**
     * 提交下发消息
     * 
     * @param request 下发消息请求
     * @return 提交下发消息结果
     */
    @PostMapping("/submit_messages")
    SubmitMessagesResult submitMessages(@RequestBody ForwardSubmissionRequest request);

    /**
     * 提交多目的地下发消息
     * 
     * @param request 多目的地下发消息请求
     * @return 多目的地下发消息结果
     */
    @PostMapping("/submit_message_to_destinations")
    SubmitMessagesResult submitMessageToDestinations(@RequestBody MultiDestinationSubmissionRequest request);

    /**
     * 取消提交下发消息
     * 
     * @param accessId 访问账号
     * @param password 密码
     * @param fwIds    消息编号集(以逗号分隔)
     * @return 提交下发消息结果
     */
    @GetMapping("/submit_cancelations")
    SubmitMessagesResult submitCancelations(@RequestParam("access_id") String accessId,
            @RequestParam("password") String password, @RequestParam("fwIDs") String fwIds);

}
