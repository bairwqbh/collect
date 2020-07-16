package per.cby.collect.socket.redis;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.frame.redis.annotation.RedisStorage;
import per.cby.frame.redis.storage.hash.FlexRedisHashStorageImpl;

/**
 * 套接字下发哈希缓存
 * 
 * @author chenboyang
 * @since 2020年3月19日
 *
 */
@RedisStorage("collect:socket:issued:map")
public class SocketIssuedMap extends FlexRedisHashStorageImpl<String, TerminalMessage> {

}
