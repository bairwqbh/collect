package per.cby.collect.socket.bootstrap;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.socket.AbstractSocketServer;
import per.cby.collect.common.socket.CommonHandler;
import per.cby.collect.socket.config.properties.SocketProperties;
import per.cby.collect.socket.handler.SocketAuthenHandler;
import per.cby.collect.socket.handler.SocketCollectHandler;
import per.cby.collect.socket.handler.SocketDecoder;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 套接字采集服务器
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
public class SocketCollectServer extends AbstractSocketServer {

    @Autowired(required = false)
    private SocketProperties socketProperties;

    @Autowired(required = false)
    private SocketAuthenHandler socketAuthenHandler;

    @Autowired(required = false)
    private SocketCollectHandler socketCollectHandler;

    /**
     * 构建套接字采集服务器
     * 
     * @param port 服务器端口
     */
    public SocketCollectServer(int port) {
        super(port);
    }

    @Override
    protected void pipeline(ChannelPipeline pipeline) {
        pipeline.addLast(new IdleStateHandler(0, 0, socketProperties.getIdleTime(), TimeUnit.SECONDS));
        pipeline.addLast(socketAuthenHandler);
        pipeline.addLast(new SocketDecoder());
        pipeline.addLast(socketCollectHandler);
        pipeline.addLast(new CommonHandler());
    }

}
