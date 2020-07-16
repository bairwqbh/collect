package per.cby.collect.coap.config;

import org.eclipse.californium.core.CoapServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.coap.resource.CoapCollectResource;
import per.cby.collect.coap.resource.CoapFotaResource;
import per.cby.collect.coap.server.CoapGatewayServer;

/**
 * CoAP采集配置
 * 
 * @author chenboyang
 *
 */
@Configuration("__COAP_COLLECT_CONFIG__")
public class CoapCollectConfig {

    @Value("${sys.business.coap.port:5683}")
    private int port;

    @Bean
    @ConditionalOnMissingBean(value = CoapServer.class, name = "coapServer")
    public CoapServer coapServer() {
        return new CoapGatewayServer(port);
    }

    @Bean
    @ConditionalOnMissingBean(value = CoapCollectResource.class, name = "coapCollectResource")
    public CoapCollectResource coapCollectResource() {
        return new CoapCollectResource();
    }

    @Bean
    @ConditionalOnMissingBean(value = CoapFotaResource.class, name = "coapFotaResource")
    public CoapFotaResource coapFotaResource() {
        return new CoapFotaResource();
    }

}
