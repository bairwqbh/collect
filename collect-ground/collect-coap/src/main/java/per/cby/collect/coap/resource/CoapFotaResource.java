package per.cby.collect.coap.resource;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.terminal.service.FotaProcessService;

import lombok.extern.slf4j.Slf4j;

/**
 * CoAP的FOTA服务资源
 * 
 * @author chenboyang
 * @since 2019年11月1日
 *
 */
@Slf4j
public class CoapFotaResource extends AbstractCoapResource {

    @Autowired(required = false)
    private FotaProcessService fotaProcessService;

    public CoapFotaResource() {
        super(FOTA_RESOURCE);
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        handle(exchange, message -> {
            TerminalMessage issued = fotaProcessService.exchange(message);
            if (issued != null && ArrayUtils.isNotEmpty(issued.getPayload())) {
                exchange.respond(ResponseCode.CONTENT, issued.getPayload());
                log.info("发送CoAP的FOTA下发数据：{}", Hex.encodeHexString(issued.getPayload()));
                return false;
            }
            return true;
        });
    }

}
