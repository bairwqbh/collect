package per.cby.collect.socket.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import per.cby.collect.socket.constant.SocketAuthenResult;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.experimental.UtilityClass;

/**
 * 套接字业务辅助
 * 
 * @author chenboyang
 * @since 2020年3月19日
 *
 */
@UtilityClass
public class SocketSupport {

    /** 频道业务映射 */
    public final Map<String, String> CHANNEL_MAP = new ConcurrentHashMap<String, String>();

    /**
     * 获取频道编号
     * 
     * @param ctx 频道上下文处理器
     * @return 频道编号
     */
    public String channelId(ChannelHandlerContext ctx) {
        return ctx.channel().id().asShortText();
    }

    /**
     * 响应成功
     * 
     * @param ctx 频道上下文处理器
     */
    public void responseSuccess(ChannelHandlerContext ctx) {
        ByteBuf msg = Unpooled.copiedBuffer(new byte[] { (byte) SocketAuthenResult.SUCCESS.getCode() });
        ctx.channel().writeAndFlush(msg);
    }

}
