package per.cby.collect.beidou.redis;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.frame.redis.annotation.RedisStorage;
import per.cby.frame.redis.storage.list.DefaultRedisListStorage;

/**
 * 北斗下发消息队列
 * 
 * @author chenboyang
 * @since 2020年2月28日
 *
 */
@RedisStorage("collect:beidou:issued:message:list")
public class BeidouIssuedMessageList extends DefaultRedisListStorage<TerminalMessage> {

}
