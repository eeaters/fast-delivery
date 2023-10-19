package io.eeaters.delivery.mock.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@ConditionalOnProperty(prefix = UserProperty.USER_PREFIX, name = "enabled", matchIfMissing = false)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(value = UserProperty.class)
public class AccessConfig {


    @Autowired
    private UserProperty userProperty;

    @Bean
    public HandlerInterceptor addInterceptors() {
        return new AccessInterceptor(userProperty);
    }


    @RestController
    @RequestMapping("/user")
    public class UserController {

        @PostMapping("login")
        public LoginResp login(@RequestBody LoginReq userInfo) {
            if (Objects.equals(userInfo.getPhone(), userProperty.getPhone())
                    && Objects.equals(userInfo.getPassword(), userProperty.getPassword())) {
                LoginResp resp = new LoginResp();
                resp.setToken(userProperty.getToken());
                resp.setNickName(userProperty.getUserName());
                return resp;
            }
            throw new LoginException("用户登录失败");
        }
    }



}
