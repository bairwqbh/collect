package per.cby.collect.beidou.handler;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.beidou.constant.ATCommand;
import per.cby.collect.beidou.constant.BeidouConstant;
import per.cby.collect.beidou.constant.ReplyCommand;
import per.cby.collect.beidou.redis.BeidouIssuedMessageList;
import per.cby.collect.common.constant.GatewayType;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.ReportService;
import per.cby.collect.satellite.common.constant.SatelliteChannel;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 铱星数据上报处理器
 * 
 * @author chenboyang
 * @since 2020年2月12日
 *
 */
@Slf4j
@Sharable
public class BeidouHandler extends SimpleChannelInboundHandler<String> implements BeidouConstant {

    /** 消息发送结束符 */
    private static final String END_SYMBOL = new String(new byte[] { 26 });

    @Autowired(required = false)
    private ReportService reportService;

    @Autowired(required = false)
    private BeidouIssuedMessageList beidouIssuedMessageList;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("收到北斗上报数据：{} - {}", ctx.channel(), Hex.encodeHexString(msg.getBytes(CHARSET)));
        if (StringUtils.isNotBlank(msg) && msg.startsWith(ReplyCommand.RDSS.getCode())) {
            String[] array = msg.replace(ReplyCommand.RDSS.getCode(), "").replace("\r\n", "").split(",", 2);
            String imei = array[0];
            String payload = array[1];
            if (StringUtils.isNotBlank(imei) && StringUtils.isNotEmpty(payload)) {
                TerminalMessage message = TerminalMessage.create();
                message.setChannel(SatelliteChannel.BEIDOU.getCode());
                message.setImei(imei);
                message.setPayload(payload.getBytes(CHARSET));
                message.getHeader().put(GATEWAY, GatewayType.BEIDOU.getCode());
                reportService.report(message);
            }
        }
        issuedMessage(ctx);
    }

    /**
     * 下发消息
     * 
     * @param ctx 通道处理器上下文
     */
    private void issuedMessage(ChannelHandlerContext ctx) {
        TerminalMessage message = null;
        StringBuilder sb = new StringBuilder();
        while (beidouIssuedMessageList.size() > 0) {
            message = beidouIssuedMessageList.rightPop();
            if (message == null || StringUtils.isBlank(message.getImei()) || ArrayUtils.isEmpty(message.getPayload())) {
                continue;
            }
            sb.delete(0, sb.length());
            sb.append(ATCommand.AT_SEND.getCode());
            sb.append(message.getImei()).append(",\"");
            sb.append(new String(message.getPayload(), CHARSET));
            sb.append(message.getImei()).append("\"");
            sb.append(END_SYMBOL);
            ctx.channel().writeAndFlush(sb.toString());
            log.info("收到北斗下发数据：{}", Hex.encodeHexString(message.getPayload()));
        }
    }

}
