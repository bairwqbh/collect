package per.cby.collect.coap.test.dtls;

import java.io.IOException;
import java.time.LocalDateTime;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.network.CoapEndpoint.CoapEndpointBuilder;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.eclipse.californium.scandium.dtls.pskstore.PskStore;
import org.eclipse.californium.scandium.dtls.pskstore.StaticPskStore;

/**
 * coap服务器测试
 * 
 * @author chenboyang
 * @since 2019年10月30日
 *
 */
public class CoAPDTLSServerTest {

    public static void main(String[] args) throws IOException {

        CoapServer server = new CoapServer(5683);// 主机为localhost 端口为默认端口5683

        PskStore pskStore = new StaticPskStore("key11", "cby666".getBytes());
        CoapEndpointBuilder ceb = new CoapEndpointBuilder();
        DtlsConnectorConfig.Builder db = new DtlsConnectorConfig.Builder();
        db.setPskStore(pskStore);
        ceb.setConnector(new DTLSConnector(db.build()));

        server.addEndpoint(ceb.build());

        server.add(new CoapResource("hello") {// 创建一个资源为hello 请求格式为 主机：端口\hello
            @Override
            public void handleGET(CoapExchange exchange) { // 重写处理GET请求的方法
                exchange.respond(ResponseCode.CONTENT, "Hello CoAP!");
            }

            @Override
            public void handlePOST(CoapExchange exchange) {
                byte[] data = exchange.getRequestPayload();
                String sn = exchange.getQueryParameter("sn");
                System.out.println(data);
                System.out.println(sn);
                exchange.respond(ResponseCode.CONTENT, new byte[] { 3, 2, 1 });
            }
        });
        server.add(new CoapResource("time") { // 创建一个资源为time 请求格式为 主机：端口\time
            @Override
            public void handleGET(CoapExchange exchange) {
                exchange.respond(ResponseCode.CONTENT, LocalDateTime.now().toString());
            }
        });
        server.start();
    }

}
