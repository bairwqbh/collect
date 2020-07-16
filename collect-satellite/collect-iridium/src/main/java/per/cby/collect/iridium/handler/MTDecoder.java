package per.cby.collect.iridium.handler;

import java.util.List;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.iridium.constant.MTElement;
import per.cby.collect.iridium.constant.MTStatus;
import per.cby.collect.iridium.model.MTConfirm;
import per.cby.collect.satellite.common.constant.SatelliteChannel;
import per.cby.frame.common.exception.BusinessAssert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 下发确认消息解码器
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Slf4j
public class MTDecoder extends ByteToMessageDecoder {

    /** 最小帧长度 */
    private static final int FRAME_MIN_LENGTH = 31;

    /** 协议修订版本号 */
    private static final int PROTOCOL_REVISION_NUMBER = 1;

    /** 最小数据长度 */
    private static final int DATA_MIN_LENGTH = FRAME_MIN_LENGTH - 3;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        log.info("收到铱星下发响应消息：{} - {}", channelHandlerContext.channel(), ByteBufUtil.hexDump(byteBuf));
        TerminalMessage message = TerminalMessage.create();
        message.setChannel(SatelliteChannel.IRIDIUM.getCode());
        validate(byteBuf);
        MTElement iei = MTElement.value(byteBuf.readUnsignedByte());
        int length = byteBuf.readUnsignedShort();
        ByteBuf content = byteBuf.readBytes(length);
        try {
            if (MTElement.MT_CONFIRMATION.equals(iei)) {
                list.add(resolveConfirm(content));
            }
        } finally {
            content.release();
        }
    }

    /**
     * 数据验证
     * 
     * @param byteBuf 数据
     * @return 验证后的数据
     */
    private void validate(ByteBuf byteBuf) {
        BusinessAssert.isTrue(byteBuf.readableBytes() >= FRAME_MIN_LENGTH, "报文总长小于最小帧长度！");
        short protocolRevisionNumber = byteBuf.readUnsignedByte();
        BusinessAssert.isTrue(protocolRevisionNumber == PROTOCOL_REVISION_NUMBER, "协议版本有误！");
        int overallMessageLength = byteBuf.readUnsignedShort();
        BusinessAssert.isTrue(overallMessageLength >= DATA_MIN_LENGTH, "数据长度小于最小数据长度！");
    }

    /**
     * 解析确认信息
     * 
     * @param content 内容
     */
    private MTConfirm resolveConfirm(ByteBuf content) {
        MTConfirm confirm = new MTConfirm();
        byte[] msgIdBytes = new byte[4];
        content.readBytes(msgIdBytes);
        confirm.setMsgid(new String(msgIdBytes));
        byte[] imeiBytes = new byte[15];
        content.readBytes(imeiBytes);
        confirm.setImei(new String(imeiBytes));
        confirm.setAutoId(content.readUnsignedInt());
        confirm.setStatus(MTStatus.value(content.readShort()));
        return confirm;
    }

}
