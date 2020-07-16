package per.cby.collect.iridium.handler;

import java.util.List;

import per.cby.collect.common.constant.GatewayType;
import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.iridium.constant.MOElement;
import per.cby.collect.iridium.constant.MOSessionStatus;
import per.cby.collect.iridium.model.MOHeader;
import per.cby.collect.iridium.model.MOLocation;
import per.cby.collect.satellite.common.constant.SatelliteChannel;
import per.cby.collect.satellite.common.constant.SatelliteConstant;
import per.cby.frame.common.exception.BusinessAssert;
import per.cby.frame.common.util.NumberUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 上报消息解码器
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Slf4j
public class MODecoder extends ByteToMessageDecoder implements SatelliteConstant {

    /** 最小帧长度 */
    private static final int FRAME_MIN_LENGTH = 34;

    /** 协议修订版本号 */
    private static final int PROTOCOL_REVISION_NUMBER = 1;

    /** 最小数据长度 */
    private static final int DATA_MIN_LENGTH = FRAME_MIN_LENGTH - 3;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        log.info("收到铱星上报数据：{} - {}", channelHandlerContext.channel(), ByteBufUtil.hexDump(byteBuf));
        TerminalMessage message = TerminalMessage.create();
        message.setChannel(SatelliteChannel.IRIDIUM.getCode());
        message.getHeader().put(GATEWAY, GatewayType.IRIDIUM.getCode());
        ByteBuf buf = validate(byteBuf);
        try {
            while (buf.readerIndex() < buf.capacity()) {
                MOElement iei = MOElement.value(buf.readUnsignedByte());
                int length = buf.readUnsignedShort();
                ByteBuf content = buf.readBytes(length);
                try {
                    switch (iei) {
                        case MO_HEADER:
                            MOHeader header = resolveHeader(content);
                            MOSessionStatus status = MOSessionStatus.value(header.getStatus());
                            BusinessAssert.isTrue(MOSessionStatus.MO_SUCCESS.equals(status), status.getDesc());
                            message.setImei(header.getImei());
                            break;
                        case MO_LOCATION_INFORMATION:
                            MOLocation location = resolveLocation(content);
                            message.getHeader().put("location", location);
                            break;
                        case MO_PAYLOAD:
                            byte[] payload = resolvePayload(content, length);
                            message.setPayload(payload);
                            break;
                        case MO_CONFIRMATION:
                            break;
                        default:
                            break;
                    }
                } finally {
                    content.release();
                }
            }
            list.add(message);
        } finally {
            buf.release();
        }
    }

    /**
     * 数据验证
     * 
     * @param byteBuf 数据
     * @return 验证后的数据
     */
    private ByteBuf validate(ByteBuf byteBuf) {
        BusinessAssert.isTrue(byteBuf.readableBytes() >= FRAME_MIN_LENGTH, "报文总长小于最小帧长度！");
        short protocolRevisionNumber = byteBuf.readUnsignedByte();
        BusinessAssert.isTrue(protocolRevisionNumber == PROTOCOL_REVISION_NUMBER, "协议版本有误！");
        int overallMessageLength = byteBuf.readUnsignedShort();
        BusinessAssert.isTrue(overallMessageLength >= DATA_MIN_LENGTH, "数据长度小于最小数据长度！");
        return byteBuf.readBytes(overallMessageLength);
    }

    /**
     * 解析头部信息
     * 
     * @param content 内容
     * @return 头部信息
     */
    private MOHeader resolveHeader(ByteBuf content) {
        MOHeader header = new MOHeader();
        header.setCdr(content.readUnsignedInt());
        byte[] imeiBytes = new byte[15];
        content.readBytes(imeiBytes);
        header.setImei(new String(imeiBytes));
        header.setStatus(content.readUnsignedByte());
        header.setMomsn(content.readUnsignedShort());
        header.setMtmsn(content.readUnsignedShort());
        header.setTime(content.readUnsignedInt());
        return header;
    }

    /**
     * 解析载荷信息
     * 
     * @param content 内容
     * @param length  内容长度
     * @return 载荷数据
     */
    private byte[] resolvePayload(ByteBuf content, int length) {
        byte[] payload = new byte[length];
        content.readBytes(payload);
        return payload;
    }

    /**
     * 解析定位信息
     * 
     * @param content 内容
     * @return 定位信息
     */
    private MOLocation resolveLocation(ByteBuf content) {
        MOLocation location = new MOLocation();
        ByteBuf buf = content.readBytes(7);
        try {
            byte first = buf.readByte();
//          int reserved = (first >> 4) & 0x0F;
//          int format = (first >> 2) & 0x03;
            int nsi = (first >> 1) & 0x01;
            int ewi = first & 0x01;
            double latitudeDegrees = buf.readUnsignedByte();
            double latitudeMinute = buf.readUnsignedShort();
            double longitudeDegrees = buf.readUnsignedByte();
            double longitudeMinute = buf.readUnsignedShort();
            double latitude = NumberUtil.scale(latitudeDegrees + (latitudeMinute / 1000 / 60), 6);
            if (nsi == 1) {
                latitude = -latitude;
            }
            location.setLatitude(latitude);
            double longitude = NumberUtil.scale(longitudeDegrees + (longitudeMinute / 1000 / 60), 6);
            if (ewi == 1) {
                longitude = -longitude;
            }
            location.setLongitude(longitude);
            location.setRadius(content.readUnsignedInt());
        } finally {
            buf.release();
        }
        return location;
    }

}
