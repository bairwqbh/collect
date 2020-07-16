package per.cby.collect.ground.common.event;

import per.cby.collect.common.event.DataIssuedEvent;
import per.cby.collect.common.model.TerminalMessage;

/**
 * MQTT下发事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public class MqttIssuedEvent extends DataIssuedEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public MqttIssuedEvent(TerminalMessage message) {
        super(message);
    }

}
