package per.cby.collect.satellite.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.satellite.common.service.SatelliteIssuedService;
import per.cby.collect.satellite.common.service.impl.SatelliteIssuedServiceImpl;

/**
 * 地面网采集配置
 * 
 * @author chenboyang
 *
 */
@Configuration("__SATELLITE_COLLECT_CONFIG__")
public class SatelliteCollectConfig {

    @Bean
    @ConditionalOnMissingBean(value = SatelliteIssuedService.class, name = "satelliteIssuedService")
    public SatelliteIssuedService satelliteIssuedService() {
        return new SatelliteIssuedServiceImpl();
    }

}
