package per.cby.collect.iridium.handler;

import per.cby.collect.iridium.constant.MTStatus;
import per.cby.collect.iridium.model.MTConfirm;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 下发消息确认处理器
 * 
 * @author chenboyang
 * @since 2020年2月12日
 *
 */
@Slf4j
@Sharable
public class MTConfirmHandler extends SimpleChannelInboundHandler<MTConfirm> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MTConfirm msg) throws Exception {
        ctx.close();
        MTStatus status = msg.getStatus();
        if (MTStatus.MT_SUCCESS.equals(status)) {
            log.info(status.getDesc());
        } else if (MTStatus.MT_NO_PAYLOAD.equals(status)) {
            log.warn(status.getDesc());
        } else {
            log.error(status.getDesc());
        }
    }

}
