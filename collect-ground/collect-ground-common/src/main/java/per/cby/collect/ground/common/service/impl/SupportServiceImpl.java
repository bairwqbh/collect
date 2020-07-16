package per.cby.collect.ground.common.service.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.ground.common.constant.Protocol;
import per.cby.collect.ground.common.model.NetworkMapper;
import per.cby.collect.ground.common.service.SupportService;

/**
 * 采集模块辅助服务
 * 
 * @author chenboyang
 * @since 2019年12月10日
 *
 */
public class SupportServiceImpl implements SupportService {

    @Autowired(required = false)
    private NetworkMapper networkMapper;

    @Override
    public String getChannelByProtocol(Protocol protocol) {
        if (protocol == null) {
            return null;
        }
        if (MapUtils.isEmpty(networkMapper) || networkMapper.get(protocol) == null) {
            return protocol.getCode();
        }
        return networkMapper.get(protocol).getCode();
    }

}
