package io.eeaters.delivery.converter.vo.delivery.resp;

import io.eeaters.delivery.entity.model.Order;
import io.eeaters.delivery.entity.vo.delivery.resp.OrderInfo;
import org.springframework.beans.BeanUtils;

public interface OrderInfoConverter {

    static OrderInfo convert(Order order) {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(order, orderInfo);
        return orderInfo;
    }
}
