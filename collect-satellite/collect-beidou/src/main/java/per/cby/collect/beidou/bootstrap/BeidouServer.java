package per.cby.collect.beidou.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.beidou.constant.BeidouConstant;
import per.cby.collect.beidou.handler.BeidouHandler;
import per.cby.collect.common.socket.AbstractSocketServer;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 铱星数据上报服务器
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
public class BeidouServer extends AbstractSocketServer implements BeidouConstant {

    /**
     * 构建铱星数据上报服务器
     * 
     * @param port 服务器端口
     */
    public BeidouServer(int port) {
        super(port);
    }

    /** 业务处理器 */
    @Autowired(required = false)
    private BeidouHandler beidouHandler;

    @Override
    protected void pipeline(ChannelPipeline pipeline) {
        pipeline.addLast(new StringDecoder(CHARSET));
        pipeline.addLast(beidouHandler);
        pipeline.addLast(new StringEncoder(CHARSET));
    }

}
