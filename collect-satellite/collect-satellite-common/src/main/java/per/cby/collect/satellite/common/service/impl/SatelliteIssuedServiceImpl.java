package per.cby.collect.satellite.common.service.impl;

import per.cby.collect.common.model.TerminalMessage;
import per.cby.collect.common.service.impl.AbstractSendService;
import per.cby.collect.satellite.common.constant.SatelliteConstant;
import per.cby.collect.satellite.common.event.BeidouIssuedEvent;
import per.cby.collect.satellite.common.event.EcsatIssuedEvent;
import per.cby.collect.satellite.common.event.IridiumIssuedEvent;
import per.cby.collect.satellite.common.event.OrbcommIssuedEvent;
import per.cby.collect.satellite.common.event.TiantongIssuedEvent;
import per.cby.collect.satellite.common.service.SatelliteIssuedService;
import per.cby.frame.mqtt.constant.MqttConstant;

/**
 * 终端数据卫星网下发服务实现
 *
 * @author chenboyang
 * @since 2019年10月29日
 *
 */
public class SatelliteIssuedServiceImpl extends AbstractSendService
        implements SatelliteIssuedService, SatelliteConstant, MqttConstant {

    @Override
    public void ecsatIssued(TerminalMessage message) {
        send(message, TERMINAL_ISSUED_ECSAT, () -> new EcsatIssuedEvent(message));
    }

    @Override
    public void ecsatIssued(TerminalMessage message, int qos) {
        message.getHeader().put(QOS, qos);
        ecsatIssued(message);
    }

    @Override
    public void iridiumIssued(TerminalMessage message) {
        send(message, TERMINAL_ISSUED_IRIDIUM, () -> new IridiumIssuedEvent(message));
    }

    @Override
    public void orbcommIssued(TerminalMessage message) {
        send(message, TERMINAL_ISSUED_ORBCOMM, () -> new OrbcommIssuedEvent(message));
    }

    @Override
    public void beidouIssued(TerminalMessage message) {
        send(message, TERMINAL_ISSUED_BEIDOU, () -> new BeidouIssuedEvent(message));
    }

    @Override
    public void tiantongIssued(TerminalMessage message) {
        send(message, TERMINAL_ISSUED_TIANTONG, () -> new TiantongIssuedEvent(message));
    }

}
