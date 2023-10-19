package io.eeaters.delivery.converter.vo.delivery.resp;

import io.eeaters.delivery.entity.model.Delivery;
import io.eeaters.delivery.entity.vo.delivery.resp.DeliveryInfo;
import org.springframework.beans.BeanUtils;

public interface DeliveryInfoConverter {

    static DeliveryInfo convert(Delivery delivery) {
        DeliveryInfo info = new DeliveryInfo();
        BeanUtils.copyProperties(delivery, info);
        return info;
    }
}
