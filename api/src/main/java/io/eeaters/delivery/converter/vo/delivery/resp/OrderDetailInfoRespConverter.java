package io.eeaters.delivery.converter.vo.delivery.resp;

import io.eeaters.delivery.entity.model.Order;
import io.eeaters.delivery.entity.model.ProductInfo;
import io.eeaters.delivery.entity.vo.delivery.resp.OrderDetailInfoResp;

import java.util.List;

public interface OrderDetailInfoRespConverter {

    static OrderDetailInfoResp convert(Order order, List<ProductInfo> productInfoList) {
        OrderDetailInfoResp resp = new OrderDetailInfoResp();
        resp.setOrderInfo(OrderInfoRespConverter.convert(order));
        resp.setProductInfos(ProductInfoRespConverter.converter(productInfoList));
        return resp;
    }
}
