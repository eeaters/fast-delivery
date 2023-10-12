package io.eeaters.delivery.converter.vo.delivery.resp;

import io.eeaters.delivery.entity.model.Delivery;
import io.eeaters.delivery.entity.vo.delivery.resp.DeliveryInfoResp;
import org.springframework.beans.BeanUtils;

public interface DeliveryInfoConverter {

    static DeliveryInfoResp convert(Delivery delivery) {
        DeliveryInfoResp info = new DeliveryInfoResp();
        BeanUtils.copyProperties(delivery, info);
        return info;
    }
}
