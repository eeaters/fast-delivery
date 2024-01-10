package io.eeaters.delivery.config.third;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "third")
@Configuration
@Data
public class ThirdConfig {

    private DeliveryProperty delivery;


    @Data
    public static class DeliveryProperty {
        private Boolean isMock;
        private SfProperty shunfeng;
    }


    @Data
    public static class SfProperty {
        private Integer appId;
        private String appKey;
        private Integer productType;
        private Integer shopType;
        private Integer returnFlag;
        private Boolean isMock;
    }


}
