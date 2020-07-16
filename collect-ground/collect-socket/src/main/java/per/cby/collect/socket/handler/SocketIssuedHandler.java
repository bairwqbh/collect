package per.cby.collect.socket.handler;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.socket.constant.SocketCollectConstant;
import per.cby.collect.socket.redis.SocketIssuedList;
import per.cby.collect.socket.redis.SocketIssuedMap;

/**
 * 套接字下发处理器
 * 
 * @author chenboyang
 * @since 2020年4月17日
 *
 */
public class SocketIssuedHandler implements Consumer<TerminalMessage>, SocketCollectConstant {

    @Autowired(required = false)
    private SocketIssuedList socketIssuedList;

    @Autowired(required = false)
    private SocketIssuedMap socketIssuedMap;

    @Override
    public void accept(TerminalMessage message) {
        String type = Optional.ofNullable(message.getHeader()).map(t -> t.get(ISSUED_TYPE)).map(t -> t.toString())
                .orElse(null);
        if (type != null) {
            socketIssuedMap.put(message.getTerminalId(), type, message);
        } else {
            socketIssuedList.leftPush(message.getTerminalId(), message);
        }
    }

}
