package per.cby.collect.socket.handler;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.FotaExchanger;
import per.cby.collect.socket.constant.SocketCollectConstant;

/**
 * 套接字FOTA处理器
 * 
 * @author chenboyang
 * @since 2020年3月19日
 *
 */
@Slf4j
@Sharable
public class SocketFotaHandler extends SimpleChannelInboundHandler<TerminalMessage> implements SocketCollectConstant {

    @Autowired(required = false)
    private FotaExchanger fotaExchanger;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TerminalMessage msg) throws Exception {
        TerminalMessage message = fotaExchanger.exchange(msg);
        if (message != null && ArrayUtils.isNotEmpty(message.getPayload())) {
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(message.getPayload()));
            log.info("发送Socket的FOTA下发数据：{}", Hex.encodeHexString(message.getPayload()));
        } else {
//            SocketSupport.responseSuccess(ctx);
        }
    }

}
