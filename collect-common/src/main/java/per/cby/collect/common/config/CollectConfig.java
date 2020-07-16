package per.cby.collect.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import per.cby.collect.common.service.ReportService;
import per.cby.collect.common.service.impl.ReportServiceImpl;
import per.cby.frame.common.constant.CommMode;

/**
 * 采集配置
 * 
 * @author chenboyang
 *
 */
@Configuration("__COLLECT_CONFIG__")
public class CollectConfig {

    @Bean
    @ConditionalOnMissingBean
    public CommMode commMode() {
        return CommMode.EVENT;
    }

    @Bean
    @ConditionalOnMissingBean(value = ReportService.class, name = "reportService")
    public ReportService reportService() {
        return new ReportServiceImpl();
    }

}
