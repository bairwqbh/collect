package per.cby.collect.iridium.handler;

import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.ReportService;
import per.cby.collect.common.util.CollectUtil;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 铱星数据上报处理器
 * 
 * @author chenboyang
 * @since 2020年2月12日
 *
 */
@Sharable
public class IridiumReportHandler extends SimpleChannelInboundHandler<TerminalMessage> {

    @Autowired(required = false)
    private ReportService reportService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TerminalMessage msg) throws Exception {
        ctx.close();
        if (CollectUtil.isFotaMsg(msg.getPayload())) {
            reportService.fotaReport(msg);
        } else {
            reportService.report(msg);
        }
    }

}
