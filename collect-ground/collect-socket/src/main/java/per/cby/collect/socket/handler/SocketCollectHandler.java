package per.cby.collect.socket.handler;

import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.ReportService;
import per.cby.collect.socket.constant.SocketCollectConstant;
import per.cby.collect.socket.redis.SocketIssuedList;
import per.cby.collect.socket.redis.SocketIssuedMap;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 套接字采集处理器
 * 
 * @author chenboyang
 * @since 2020年3月19日
 *
 */
@Slf4j
@Sharable
public class SocketCollectHandler extends SimpleChannelInboundHandler<TerminalMessage>
        implements SocketCollectConstant {

    @Autowired(required = false)
    private ReportService reportService;

    @Autowired(required = false)
    private SocketIssuedList socketIssuedList;

    @Autowired(required = false)
    private SocketIssuedMap socketIssuedMap;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TerminalMessage msg) throws Exception {
        reportService.report(msg);
        response(ctx, msg.getTerminalId());
    }

    /**
     * 消息响应
     * 
     * @param ctx        通道处理器上下文
     * @param terminalId 终端编号
     */
    protected void response(ChannelHandlerContext ctx, String terminalId) {
//        if (!socketIssuedList.existKey(terminalId)) {
//            SocketSupport.responseSuccess(ctx);
//            return;
//        }
        TerminalMessage message = null;
        while (socketIssuedList.existKey(terminalId)) {
            message = socketIssuedList.rightPop(terminalId);
            issued(ctx, message);
        }
        List<TerminalMessage> list = socketIssuedMap.values(terminalId);
        if (CollectionUtils.isNotEmpty(list)) {
            list.removeIf(Objects::isNull);
            list.forEach(t -> issued(ctx, t));
            socketIssuedMap.clear(terminalId);
        }
        if (message == null && CollectionUtils.isEmpty(list)) {
            noResponse(ctx, terminalId);
        }
    }

    /**
     * 执行下发
     * 
     * @param ctx     通道处理器上下文
     * @param message 下发消息
     */
    protected void issued(ChannelHandlerContext ctx, TerminalMessage message) {
        if (message != null && ArrayUtils.isNotEmpty(message.getPayload())) {
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(message.getPayload()));
            log.info("发送Socket下发数据：{}", Hex.encodeHexString(message.getPayload()));
        }
    }

    /**
     * 无任何响应信息的回调函数
     * 
     * @param ctx        通道处理器上下文
     * @param terminalId 终端编号
     */
    protected void noResponse(ChannelHandlerContext ctx, String terminalId) {

    }

}
