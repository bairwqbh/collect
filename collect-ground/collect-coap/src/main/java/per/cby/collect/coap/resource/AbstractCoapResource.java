package per.cby.collect.coap.resource;

import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.coap.constant.CoapCollectConstant;
import per.cby.collect.common.constant.GatewayType;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.ground.common.constant.Protocol;
import per.cby.collect.ground.common.service.SupportService;

import lombok.extern.slf4j.Slf4j;

/**
 * CoAP抽象服务资源
 * 
 * @author chenboyang
 * @since 2019年11月1日
 *
 */
@Slf4j
public abstract class AbstractCoapResource extends CoapResource implements CoapCollectConstant {

    @Autowired(required = false)
    private SupportService supportService;

    public AbstractCoapResource(String name) {
        super(name);
    }

    /**
     * CoAP交换处理
     * 
     * @param exchange 交换信息
     * @param consumer 消息消费
     */
    protected void handle(CoapExchange exchange, Predicate<TerminalMessage> predicate) {
        try {
            log.info("收到CoAP上报数据：{} - {}", exchange, Hex.encodeHexString(exchange.getRequestPayload()));
            TerminalMessage message = TerminalMessage.create();
            String sn = exchange.getQueryParameter(SN);
            message.setTerminalId(sn);
            message.setChannel(supportService.getChannelByProtocol(Protocol.COAP));
            message.setPayload(exchange.getRequestPayload());
            message.getHeader().put(GATEWAY, GatewayType.COAP.getCode());
            Optional.ofNullable(predicate).ifPresent(t -> {
                if (predicate.test(message)) {
                    exchange.respond(ResponseCode._UNKNOWN_SUCCESS_CODE);
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            exchange.respond(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

}
