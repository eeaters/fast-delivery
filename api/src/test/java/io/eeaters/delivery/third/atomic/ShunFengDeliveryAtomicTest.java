package io.eeaters.delivery.third.atomic;


import io.eeaters.delivery.EnvSupport;
import io.eeaters.delivery.config.third.ThirdConfig;
import io.eeaters.delivery.entity.dto.third.CreateDeliveryDTO;
import io.eeaters.delivery.entity.dto.third.CreatePreDeliveryDTO;
import io.eeaters.delivery.third.client.ShunFengClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@EnableFeignClients(clients = ShunFengClient.class)
public class ShunFengDeliveryAtomicTest {


    @Test
    public void test() throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(
                HttpMessageConvertersAutoConfiguration.class,
                FeignAutoConfiguration.class,
                this.getClass(),
                ThirdConfig.class,
                ShunFengDeliveryAtomic.class
        );
        applicationContext.setEnvironment(EnvSupport.createEnv("config/dev/third.yml"));
        applicationContext.refresh();

        CreateDeliveryDTO createDeliveryDTO = new CreateDeliveryDTO();
        createDeliveryDTO.setStoreCode("99999");
        createDeliveryDTO.setWeightGram(10);

        CreateDeliveryDTO.ContractInformation information = new CreateDeliveryDTO.ContractInformation();
        information.setLatitude(40.030613);
        information.setLongitude(116.354787);
        information.setAddress("海淀区清河龙岗路51号清润家园小区 永辉");
        createDeliveryDTO.setUserInformation(information);

        ShunFengDeliveryAtomic bean = applicationContext.getBean(ShunFengDeliveryAtomic.class);
        CreatePreDeliveryDTO preOrder = bean.createPreOrder(createDeliveryDTO);
        System.out.println("preOrder = " + preOrder);

        applicationContext.close();
    }
}
