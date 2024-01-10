package io.eeaters.delivery.third;

import io.eeaters.delivery.config.third.ThirdConfig;
import io.eeaters.delivery.entity.dto.shunfeng.req.CreatePreDeliveryRequest;
import io.eeaters.delivery.entity.dto.shunfeng.resp.CreatePreDeliveryResponse;
import io.eeaters.delivery.entity.dto.shunfeng.resp.SfBaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@EnableFeignClients(clients = ShunFengClient.class)
public class ShunFengClientTest {

    @Test
    public void test() throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(
                HttpMessageConvertersAutoConfiguration.class,
                FeignAutoConfiguration.class,
                this.getClass(),
                ThirdConfig.class
        );
        applicationContext.setEnvironment(createEnv());
        applicationContext.refresh();


        CreatePreDeliveryRequest preOrderReq = new CreatePreDeliveryRequest();
        preOrderReq.setDevId(1668137074);
        preOrderReq.setShopId("99999");
        preOrderReq.setShopType(2);
        preOrderReq.setProductType(10);
        preOrderReq.setPushTime(Math.toIntExact(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))));
        preOrderReq.setWeight(0);
        preOrderReq.setUserLat("40.030613");
        preOrderReq.setUserLng("116.354787");
        preOrderReq.setUserAddress("海淀区清河龙岗路51号清润家园小区 永辉");
        preOrderReq.setReturnFlag(511);

        ShunFengClient bean = applicationContext.getBean(ShunFengClient.class);
        SfBaseResponse<CreatePreDeliveryResponse> response = bean.preCreateOrder(preOrderReq);
        System.out.println("response.getResult() = " + response.getResult());

        applicationContext.close();
    }


    public static ConfigurableEnvironment createEnv() throws IOException {
        ConfigurableEnvironment configurableEnvironment = new StandardEnvironment();
        YamlPropertySourceLoader propertySourceLoader = new YamlPropertySourceLoader();
        String name = "application";
        ClassPathResource classPathResource = new ClassPathResource("config/dev/third.yml");
        EncodedResource encodedResource = new EncodedResource(classPathResource);
        List<PropertySource<?>> load = propertySourceLoader.load(name, encodedResource.getResource());

        for (PropertySource<?> propertySource : load) {
            configurableEnvironment.getPropertySources().addFirst(propertySource);
        }
        return configurableEnvironment;
    }
}
