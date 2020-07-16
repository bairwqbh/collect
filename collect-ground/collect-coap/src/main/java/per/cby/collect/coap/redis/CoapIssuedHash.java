package per.cby.collect.coap.redis;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.frame.redis.annotation.RedisStorage;
import per.cby.frame.redis.storage.hash.DefaultRedisHashStorage;

/**
 * CoAP下发哈希缓存
 * 
 * @author chenboyang
 * @since 2019年11月1日
 *
 */
@RedisStorage("collect:coap:issued:hash")
public class CoapIssuedHash extends DefaultRedisHashStorage<String, TerminalMessage> {

}
