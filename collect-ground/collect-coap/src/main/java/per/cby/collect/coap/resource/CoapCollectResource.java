package per.cby.collect.coap.resource;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.coap.redis.CoapIssuedHash;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.ReportService;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据上报CoAP服务资源
 * 
 * @author chenboyang
 * @since 2019年11月1日
 *
 */
@Slf4j
public class CoapCollectResource extends AbstractCoapResource {

    @Autowired(required = false)
    private ReportService reportService;

    @Autowired(required = false)
    private CoapIssuedHash coapIssuedHash;

    public CoapCollectResource() {
        super(REPORT_RESOURCE);
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        handle(exchange, message -> {
            reportService.report(message);
            String terminalId = message.getTerminalId();
            if (coapIssuedHash != null && coapIssuedHash.has(terminalId)) {
                TerminalMessage issued = coapIssuedHash.get(terminalId);
                if (issued != null && ArrayUtils.isNotEmpty(issued.getPayload())) {
                    exchange.respond(ResponseCode.CONTENT, issued.getPayload());
                    coapIssuedHash.delete(terminalId);
                    log.info("发送CoAP下发数据：{}", Hex.encodeHexString(issued.getPayload()));
                    return false;
                }
            }
            return true;
        });
    }

}
