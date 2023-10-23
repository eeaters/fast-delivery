package io.eeaters.delivery.controller;

import io.eeaters.delivery.entity.vo.delivery.req.CreateDeliveryReq;
import io.eeaters.delivery.mock.support.AddressGenerate;
import io.eeaters.delivery.mock.support.GeoGenerate;
import io.eeaters.delivery.mock.support.NameGenerate;
import io.eeaters.delivery.mock.support.PhoneGenerate;
import io.eeaters.delivery.util.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("mock")
public class MockController {


    @GetMapping("/delivery")
    public CreateDeliveryReq deliveryMock() {

        CreateDeliveryReq deliveryReq = new CreateDeliveryReq();
        deliveryReq.setCallbackUrl("http://xxx.com/callback");
        deliveryReq.setOrderCode(RandomUtils.random());
        deliveryReq.setReceiveUserLatitude(GeoGenerate.getCnLatitude());
        deliveryReq.setReceiveUserLongitude(GeoGenerate.getCnLongitude());
        deliveryReq.setReceiveUserName(NameGenerate.getCnName());
        deliveryReq.setReceiveUserPhone(PhoneGenerate.getPhoneNumber());
        deliveryReq.setReceiveUserAddress(AddressGenerate.getAddress());
        deliveryReq.setTotalPrice(ThreadLocalRandom.current().nextInt(1000) + 100);
        deliveryReq.setWeightGram(ThreadLocalRandom.current().nextInt(10000) + 500);
        return deliveryReq;

    }

}
