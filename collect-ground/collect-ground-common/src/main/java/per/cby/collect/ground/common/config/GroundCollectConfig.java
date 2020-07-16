package per.cby.collect.ground.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.ground.common.service.GroundIssuedService;
import per.cby.collect.ground.common.service.SupportService;
import per.cby.collect.ground.common.service.impl.GroundIssuedServiceImpl;
import per.cby.collect.ground.common.service.impl.SupportServiceImpl;

/**
 * 地面网采集配置
 * 
 * @author chenboyang
 *
 */
@Configuration("__GROUND_COLLECT_CONFIG__")
public class GroundCollectConfig {

    @Bean
    @ConditionalOnMissingBean(value = GroundIssuedService.class, name = "groundIssuedService")
    public GroundIssuedService groundIssuedService() {
        return new GroundIssuedServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(value = SupportService.class, name = "supportService")
    public SupportService supportService() {
        return new SupportServiceImpl();
    }

}
