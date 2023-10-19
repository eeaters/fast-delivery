package io.eeaters.delivery.converter.model;

import io.eeaters.delivery.entity.model.Order;
import io.eeaters.delivery.entity.vo.delivery.req.CreateDeliveryReq;
import org.springframework.beans.BeanUtils;

public interface OrderConverter {

    static Order convert(CreateDeliveryReq createDeliveryReq,Boolean isMock) {
        Order order = new Order();
        BeanUtils.copyProperties(createDeliveryReq, order);
        order.setIsMock(isMock);
        order.setCreateUser("system");
        return order;
    }
}
