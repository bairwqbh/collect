package per.cby.collect.ground.common.service;

import per.cby.collect.ground.common.constant.Protocol;

/**
 * 采集模块辅助服务
 * 
 * @author chenboyang
 * @since 2019年12月10日
 *
 */
public interface SupportService {

    /**
     * 根据传输协议获取通道编号
     * 
     * @param protocol 传输协议
     * @return 通道编号
     */
    String getChannelByProtocol(Protocol protocol);

}
