package io.eeaters.delivery.mock.access;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = UserProperty.USER_PREFIX)
@Data
public class UserProperty {

    public static final String USER_PREFIX = "delivery.mock.user";

    private Boolean enabled;

    private String userName;

    private String phone;

    private String password;

    private String token;
}
