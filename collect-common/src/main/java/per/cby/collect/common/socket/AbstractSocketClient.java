package per.cby.collect.common.socket;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import per.cby.frame.common.exception.BusinessAssert;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Socket抽象客户端
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Slf4j
@NoArgsConstructor
public abstract class AbstractSocketClient {

    /** 连接线程组 */
    private EventLoopGroup group;

    /** 引导程序 */
    private Bootstrap bootstrap;

    /** 默认地址 */
    private String host;

    /** 默认端口 */
    private int port;

    /**
     * 构建Socket客户端
     * 
     * @param host 默认地址
     * @param port 默认端口
     */
    public AbstractSocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 启动服务器
     * 
     * @throws InterruptedException 异常
     */
    @PostConstruct
    public void start() throws InterruptedException {
        if (bootstrap != null) {
            return;
        }
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        bootstrap.group(group).channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel channel) throws Exception {
                pipeline(channel.pipeline());
            }
        });
        bootstrap(bootstrap);
    }

    /**
     * 设置处理管道
     * 
     * @param pipeline 管道
     */
    protected void pipeline(ChannelPipeline pipeline) {

    }

    /**
     * 设置引导程序
     * 
     * @param bootstrap 引导程序
     */
    protected void bootstrap(Bootstrap bootstrap) {

    }

    /**
     * 创建连接通道
     * 
     * @return 通道
     */
    public ChannelFuture connect() {
        return connect(host, port);
    }

    /**
     * 创建连接通道
     * 
     * @param host 地址
     * @param port 端口
     * @return 通道
     */
    public ChannelFuture connect(String host, int port) {
        BusinessAssert.hasText(host, "地址不能为空！");
        BusinessAssert.isTrue(port > 0, "端口号有误！");
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(host, port).sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return future;
    }

    /**
     * 发送消息
     * 
     * @param msg 消息
     * @return 操作结果
     */
    public ChannelFuture send(Object msg) {
        ChannelFuture future = connect();
        if (future != null && future.channel().isActive()) {
            return future.channel().writeAndFlush(msg);
        }
        return null;
    }

    /**
     * 服务器停止
     */
    @PreDestroy
    public void stop() {
        if (group != null) {
            group.shutdownGracefully();
        }
    }

}
