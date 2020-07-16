package per.cby.collect.common.socket;

import java.net.InetSocketAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务器处理程序
 * 
 * @author chenboyang
 * @since 2020年2月16日
 *
 */
@Slf4j
public class CommonHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.close();
            log.warn("客户端[{}]连接空闲超时，连接已被关闭！", getIp(ctx));
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 获取客户端IP
     * 
     * @param ctx 通道处理上下文
     * @return IP地址
     */
    protected String getIp(ChannelHandlerContext ctx) {
        return ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress();
    }

}
