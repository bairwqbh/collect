package per.cby.collect.iridium.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.socket.AbstractSocketServer;
import per.cby.collect.common.socket.CommonHandler;
import per.cby.collect.iridium.handler.IridiumReportHandler;
import per.cby.collect.iridium.handler.MODecoder;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 铱星数据上报服务器
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
public class IridiumServer extends AbstractSocketServer {

    /**
     * 构建铱星数据上报服务器
     * 
     * @param port 服务器端口
     */
    public IridiumServer(int port) {
        super(port);
    }

    /** 业务处理器 */
    @Autowired(required = false)
    private IridiumReportHandler iridiumReportHandler;

    @Override
    protected void pipeline(ChannelPipeline pipeline) {
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new MODecoder());
        pipeline.addLast(iridiumReportHandler);
        pipeline.addLast(new CommonHandler());
    }

}
