package io.eeaters.delivery.mock.job;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AutoDeliveryFlowProperty.USER_PREFIX)
@Data
public class AutoDeliveryFlowProperty {

    public static final String USER_PREFIX = "delivery.mock.delivery-auto-flow";

    private Boolean enabled;

}
