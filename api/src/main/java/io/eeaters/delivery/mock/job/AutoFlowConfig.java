package io.eeaters.delivery.mock.job;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = AutoDeliveryFlowProperty.USER_PREFIX, name = "enabled", matchIfMissing = false)
@EnableConfigurationProperties(value = AutoDeliveryFlowProperty.class)
public class AutoFlowConfig {


    @Bean
    public AutoFlowJob autoFlowJob() {
        return new AutoFlowJob();
    }

}
