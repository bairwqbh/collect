package per.cby.collect.socket.redis;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.frame.redis.annotation.RedisStorage;
import per.cby.frame.redis.storage.list.FlexRedisListStorageImpl;

/**
 * 套接字下发队列缓存
 * 
 * @author chenboyang
 * @since 2020年3月19日
 *
 */
@RedisStorage("collect:socket:issued:list")
public class SocketIssuedList extends FlexRedisListStorageImpl<TerminalMessage> {

}
