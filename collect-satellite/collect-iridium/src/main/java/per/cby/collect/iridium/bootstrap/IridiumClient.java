package per.cby.collect.iridium.bootstrap;

import per.cby.collect.common.socket.AbstractSocketClient;
import per.cby.collect.common.socket.CommonHandler;
import per.cby.collect.iridium.handler.MTConfirmHandler;
import per.cby.collect.iridium.handler.MTDecoder;
import per.cby.collect.iridium.handler.MTEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 铱星数据下发客户端
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
public class IridiumClient extends AbstractSocketClient {

    /**
     * 构建铱星数据下发客户端
     * 
     * @param host 服务器地址
     * @param port 服务器端口
     */
    public IridiumClient(String host, int port) {
        super(host, port);
    }

    @Override
    protected void pipeline(ChannelPipeline pipeline) {
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new MTDecoder());
        pipeline.addLast(new MTConfirmHandler());
        pipeline.addLast(new MTEncoder());
        pipeline.addLast(new CommonHandler());
    }

    @Override
    protected void bootstrap(Bootstrap bootstrap) {
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
    }

}
