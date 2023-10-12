package io.eeaters.delivery.converter.vo.delivery.resp;

import io.eeaters.delivery.entity.model.Order;
import io.eeaters.delivery.entity.vo.delivery.resp.OrderInfoResp;
import org.springframework.beans.BeanUtils;

public interface OrderInfoRespConverter {

    static OrderInfoResp convert(Order order) {
        OrderInfoResp orderInfoResp = new OrderInfoResp();
        BeanUtils.copyProperties(order, orderInfoResp);
        return orderInfoResp;
    }
}
