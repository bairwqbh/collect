package per.cby.collect.common.socket;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.RequiredArgsConstructor;

/**
 * Socket抽象服务端
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@RequiredArgsConstructor
public abstract class AbstractSocketServer {

    /** 连接线程组 */
    private EventLoopGroup bossGroup;

    /** 业务处理线程组 */
    private EventLoopGroup workerGroup;

    /** 服务器端口 */
    private final int port;

    /**
     * 启动服务器
     * 
     * @throws InterruptedException 异常
     */
    @PostConstruct
    public void start() throws InterruptedException {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            public void initChannel(SocketChannel channel) throws Exception {
                pipeline(channel.pipeline());
            }
        });
        bootstrap(bootstrap);
        bootstrap.bind(port).sync();
    }

    /**
     * 设置处理管道
     * 
     * @param pipeline 管道
     */
    protected abstract void pipeline(ChannelPipeline pipeline);

    /**
     * 设置引导程序
     * 
     * @param bootstrap 引导程序
     */
    protected void bootstrap(ServerBootstrap bootstrap) {

    }

    /**
     * 服务器停止
     */
    @PreDestroy
    public void stop() {
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
    }

}
