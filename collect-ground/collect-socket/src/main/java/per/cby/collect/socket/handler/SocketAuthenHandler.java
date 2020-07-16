package per.cby.collect.socket.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.socket.util.SocketSupport;
import per.cby.frame.common.exception.BusinessAssert;
import per.cby.terminal.redis.TerminalAuthenHash;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 套接字认证处理器
 * 
 * @author chenboyang
 * @since 2020年3月19日
 *
 */
@Slf4j
@Sharable
public class SocketAuthenHandler extends ChannelInboundHandlerAdapter {

    @Autowired(required = false)
    private TerminalAuthenHash terminalAuthenHash;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        log.info("收到Socket上报数据：{} - {}", ctx.channel(), ByteBufUtil.hexDump(buf));
        String channelId = SocketSupport.channelId(ctx);
        if (SocketSupport.CHANNEL_MAP.containsKey(channelId)) {
            super.channelRead(ctx, msg);
        } else {
            try {
                int length = buf.readableBytes();
                BusinessAssert.isTrue(length >= 4 && length <= 32, "认证信息长度有误！");
                byte[] bytes = new byte[length];
                buf.readBytes(bytes);
                String authInfo = new String(bytes);
                BusinessAssert.isTrue(StringUtils.isAlphanumeric(authInfo), "非法的认证信息：" + authInfo);
                BusinessAssert.isTrue(terminalAuthenHash.get(authInfo), "非法的认证信息：" + authInfo);
                SocketSupport.CHANNEL_MAP.put(channelId, authInfo);
//              int length = buf.readableBytes();
//              if (length >= 4 && length <= 32) {
//                  authenResponse(ctx, SocketAuthenResult.LENGTH_ERROR, length);
//                  return;
//              }
//              byte[] bytes = new byte[length];
//              buf.readBytes(bytes);
//              String authInfo = new String(bytes);
//              if (!StringUtils.isAlphanumeric(authInfo)) {
//                  authenResponse(ctx, SocketAuthenResult.NO_ALPHANUMERIC, authInfo);
//                  return;
//              }
//              if (!terminalAuthenHash.get(authInfo)) {
//                  authenResponse(ctx, SocketAuthenResult.NOT_PASS, authInfo);
//                  return;
//              }
//              SocketSupport.CHANNEL_MAP.put(channelId, authInfo);
//              authenResponse(ctx, SocketAuthenResult.SUCCESS, authInfo);
            } finally {
                buf.release();
            }
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String channelId = SocketSupport.channelId(ctx);
        SocketSupport.CHANNEL_MAP.remove(channelId);
    }

    /**
     * 认证响应
     * 
     * @param ctx    频道上下文
     * @param result 认证结果
     * @param data   业务数据
     */
//    private void authenResponse(ChannelHandlerContext ctx, SocketAuthenResult result, Object data) {
//        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(new byte[] { (byte) result.getCode() }));
//        if (SocketAuthenResult.SUCCESS.equals(result)) {
//            log.info("{} - {}", result.getDesc(), data);
//        } else {
//            ctx.close();
//            log.warn("{} - {}", result.getDesc(), data);
//        }
//    }

}
