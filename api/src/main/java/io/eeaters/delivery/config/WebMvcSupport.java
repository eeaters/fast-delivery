package io.eeaters.delivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Configuration
public class WebMvcSupport  implements WebMvcConfigurer {

    @Autowired
    private Map<String, HandlerInterceptor> allInterceptor;


    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>(new CorsFilter(source));
        registrationBean.addUrlPatterns("/*");
        //order default is zero,
        //Set the order is -1 to ensure CorsFilter is executed before LogFilter
        registrationBean.setOrder(-1);
        return registrationBean;
    }

    /**
     *  handler get log
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (!allInterceptor.isEmpty()) {
            for (HandlerInterceptor interceptor : allInterceptor.values()) {
                registry.addInterceptor(interceptor);
            }
        }

    }
}
