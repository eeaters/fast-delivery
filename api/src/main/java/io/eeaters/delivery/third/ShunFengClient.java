package io.eeaters.delivery.third;

import io.eeaters.delivery.entity.dto.shunfeng.req.CreateDeliveryRequest;
import io.eeaters.delivery.entity.dto.shunfeng.req.CreatePreDeliveryRequest;
import io.eeaters.delivery.entity.dto.shunfeng.resp.CreateDeliveryResponse;
import io.eeaters.delivery.entity.dto.shunfeng.resp.CreatePreDeliveryResponse;
import io.eeaters.delivery.entity.dto.shunfeng.resp.SfBaseResponse;
import io.eeaters.delivery.third.config.SfConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "sf-service", url = "https://openic.sf-express.com",configuration = SfConfig.class)
public interface ShunFengClient {

    @PostMapping(value = "/open/api/external/createorder")
    SfBaseResponse<CreateDeliveryResponse> createOrder(@RequestBody CreateDeliveryRequest request);

    @PostMapping(value = "/open/api/external/precreateorder")
    SfBaseResponse<CreatePreDeliveryResponse> preCreateOrder(@RequestBody CreatePreDeliveryRequest request);

}
