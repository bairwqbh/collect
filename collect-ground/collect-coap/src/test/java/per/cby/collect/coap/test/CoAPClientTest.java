package per.cby.collect.coap.test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

/**
 * coap客户端测试
 * 
 * @author chenboyang
 * @since 2019年10月30日
 *
 */
public class CoAPClientTest {

//    public static void main(String[] args) throws URISyntaxException {
//        URI uri = null;
//        uri = new URI("localhost:5683/hello"); // 创建一个资源请求hello资源，注意默认端口为5683
//        CoapClient client = new CoapClient(uri);
//        CoapResponse response = client.get();
//        if (response != null) {
//            System.out.println(response.getCode()); // 打印请求状态码
//            System.out.println(response.getOptions()); // 选项参数
//            System.out.println(response.getResponseText()); // 获取内容文本信息
//            System.out.println("\nAdvanced\n"); //
//            System.out.println(Utils.prettyPrint(response)); // 打印格式良好的输出
//        }
//    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String url = "localhost:5683/hello?sn=abc123"; // 创建一个资源请求hello资源，注意默认端口为5683
        CoapClient client = new CoapClient(url);
        byte[] data = new byte[] { 0x01, 0x02, 0x03 };
//        CoapResponse response = client.post(data, MediaTypeRegistry.APPLICATION_OCTET_STREAM);
        client.post(new CoapHandler() {
            
            @Override
            public void onLoad(CoapResponse response) {
                System.out.println(response.getCode()); // 打印请求状态码
                System.out.println(Utils.prettyPrint(response)); // 打印格式良好的输出
            }
            
            @Override
            public void onError() {
                System.err.println("错误");
            }
        }, data, MediaTypeRegistry.APPLICATION_OCTET_STREAM);
        
//        if (response != null) {
//            System.out.println(response.getCode()); // 打印请求状态码
//            System.out.println(response.getOptions()); // 选项参数
//            System.out.println(response.getResponseText()); // 获取内容文本信息
//            System.out.println("\nAdvanced\n"); //
//            System.out.println(Utils.prettyPrint(response)); // 打印格式良好的输出
//        }
        
        System.in.read();
    }

}
