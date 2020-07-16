package per.cby.collect.ground.common.event;

import per.cby.collect.common.event.FotaIssuedEvent;
import per.cby.collect.common.model.TerminalMessage;

/**
 * FOTA下发事件
 * 
 * @author chenboyang
 * @since 2019年12月9日
 *
 */
public class MqttFotaIssuedEvent extends FotaIssuedEvent {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public MqttFotaIssuedEvent(TerminalMessage message) {
        super(message);
    }

}
