package per.cby.collect.coap.test.dtls;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.network.CoapEndpoint.CoapEndpointBuilder;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.eclipse.californium.scandium.dtls.pskstore.PskStore;
import org.eclipse.californium.scandium.dtls.pskstore.StaticPskStore;

/**
 * coap客户端测试
 * 
 * @author chenboyang
 * @since 2019年10月30日
 *
 */
public class CoAPDTLSClientTest {

    public static void main(String[] args) throws URISyntaxException, IOException {
        String url = "localhost:5683/hello?sn=abc123"; // 创建一个资源请求hello资源，注意默认端口为5683
        CoapClient client = new CoapClient(url);

        PskStore pskStore = new StaticPskStore("key11", "cby666".getBytes());
        CoapEndpointBuilder ceb = new CoapEndpointBuilder();
        DtlsConnectorConfig.Builder db = new DtlsConnectorConfig.Builder();
        db.setPskStore(pskStore);
        ceb.setConnector(new DTLSConnector(db.build()));

        client.setEndpoint(ceb.build());

        byte[] data = new byte[] { 0x01, 0x02, 0x03 };
        CoapResponse response = client.post(data, MediaTypeRegistry.APPLICATION_OCTET_STREAM);
        System.out.println(response.getCode()); // 打印请求状态码
        System.out.println(Utils.prettyPrint(response)); // 打印格式良好的输出
    }

}
