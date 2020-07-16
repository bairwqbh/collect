package per.cby.collect.socket.handler;

import java.util.List;
import java.util.Optional;

import per.cby.collect.common.constant.GatewayType;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.ground.common.constant.Protocol;
import per.cby.collect.ground.common.service.SupportService;
import per.cby.collect.socket.constant.SocketCollectConstant;
import per.cby.collect.socket.util.SocketSupport;
import per.cby.frame.common.util.SpringContextUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 套接字业务解码器
 * 
 * @author chenboyang
 * @since 2020年3月23日
 *
 */
public class SocketDecoder extends ByteToMessageDecoder implements SocketCollectConstant {

    /** 采集模块辅助服务 */
    private SupportService supportService;

    /**
     * 采集模块辅助服务
     * 
     * @return 辅助服务
     */
    private SupportService supportService() {
        return Optional.ofNullable(supportService)
                .orElseGet(() -> supportService = SpringContextUtil.getBean(SupportService.class));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String channelId = SocketSupport.channelId(ctx);
        String terminalId = SocketSupport.CHANNEL_MAP.get(channelId);
        byte[] payload = new byte[in.readableBytes()];
        in.readBytes(payload);
        TerminalMessage message = TerminalMessage.create();
        message.setTerminalId(terminalId);
        message.setChannel(supportService().getChannelByProtocol(Protocol.SOCKET));
        message.setPayload(payload);
        message.getHeader().put(GATEWAY, GatewayType.SOCKET.getCode());
        out.add(message);
    }

}
