package per.cby.collect.iridium.handler;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.iridium.constant.MTElement;
import per.cby.collect.satellite.common.constant.SatelliteConstant;
import per.cby.frame.common.exception.BusinessAssert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 下发消息编码器
 * 
 * @author chenboyang
 * @since 2020年2月12日
 *
 */
@Slf4j
public class MTEncoder extends MessageToByteEncoder<TerminalMessage> {

    /** 协议修订版本号 */
    private static final int PROTOCOL_REVISION_NUMBER = 1;

    /** 消息编号长度 */
    private static final int MSGID_LENGTH = 4;

    /** 头部数据长度 */
    private static final int HEADER_LENGTH = 21;

    @Override
    protected void encode(ChannelHandlerContext ctx, TerminalMessage msg, ByteBuf out) throws Exception {
        validate(msg);
        ByteBuf byteBuf = Unpooled.buffer();
        encodeHeader(msg, byteBuf);
        encodePayload(msg, byteBuf);
        out.writeByte(PROTOCOL_REVISION_NUMBER);
        out.writeShort(byteBuf.readableBytes());
        out.writeBytes(byteBuf);
        log.info("发送铱星终端数据：{}", ByteBufUtil.hexDump(out));
    }

    /**
     * 数据验证
     * 
     * @param msg 终端消息
     */
    private void validate(TerminalMessage msg) {
        BusinessAssert.notNull(msg, "终端消息不能为空！");
        BusinessAssert.hasText(msg.getImei(), "IMEI不能为空！");
        BusinessAssert.isTrue(msg.getImei().length() == SatelliteConstant.IMEI_LENGTH, "IMEI的长度有误！");
        BusinessAssert.isTrue(ArrayUtils.isNotEmpty(msg.getPayload()), "载荷数据不能为空！");
    }

    /**
     * 进行头部信息编码
     * 
     * @param msg     终端消息
     * @param byteBuf 数据缓冲区
     */
    private void encodeHeader(TerminalMessage msg, ByteBuf byteBuf) {
        byteBuf.writeByte(MTElement.MT_HEADER.getCode());
        byteBuf.writeShort(HEADER_LENGTH);
        byteBuf.writeBytes(RandomStringUtils.randomAlphanumeric(MSGID_LENGTH).getBytes());
        byteBuf.writeBytes(msg.getImei().getBytes());
        byteBuf.writeShort(0);
    }

    /**
     * 进行载荷信息编码
     * 
     * @param msg     终端消息
     * @param byteBuf 数据缓冲区
     */
    private void encodePayload(TerminalMessage msg, ByteBuf byteBuf) {
        byteBuf.writeByte(MTElement.MT_PAYLOAD.getCode());
        byteBuf.writeShort(msg.getPayload().length);
        byteBuf.writeBytes(msg.getPayload());
    }

}
