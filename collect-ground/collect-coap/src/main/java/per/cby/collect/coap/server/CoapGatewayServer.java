package per.cby.collect.coap.server;

import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.commons.collections4.MapUtils;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * CoAP服务器
 * 
 * @author chenboyang
 * @since 2019年11月1日
 *
 */
public class CoapGatewayServer extends CoapServer implements ApplicationListener<ContextRefreshedEvent> {

    public CoapGatewayServer() {
        super();
    }

    public CoapGatewayServer(final int... ports) {
        super(ports);
    }

    public CoapGatewayServer(final NetworkConfig config, final int... ports) {
        super(config, ports);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, CoapResource> beanMap = event.getApplicationContext().getBeansOfType(CoapResource.class);
        if (MapUtils.isNotEmpty(beanMap)) {
            beanMap.values().forEach(this::add);
        }
    }

    @PreDestroy
    public void destroy() {
        super.destroy();
    }

}
