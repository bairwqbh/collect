package per.cby.collect.orbcomm.service;

import java.time.LocalDateTime;
import java.util.List;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.orbcomm.model.OrbcommResult;

/**
 * OGI天佑接口服务
 * 
 * @author chenboyang
 * @since 2020年2月12日
 *
 */
public interface TianyouService {

    /**
     * 轮询终端消息
     * 
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    void pollMessage(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 获取终端消息
     * 
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 消息列表
     */
    List<TerminalMessage> getMessage(long startTime, long endTime);

    /**
     * 发送消息到终端
     * 
     * @param message 消息
     * @return 发送结果
     */
    OrbcommResult sendMessage(TerminalMessage message);

}
