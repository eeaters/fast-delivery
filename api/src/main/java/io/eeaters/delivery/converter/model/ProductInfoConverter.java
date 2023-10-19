package io.eeaters.delivery.converter.model;

import io.eeaters.delivery.entity.model.ProductInfo;
import io.eeaters.delivery.entity.vo.delivery.req.ProductDetailInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;

public interface ProductInfoConverter {

    static ProductInfo convert(String orderCode,ProductDetailInfo productDetailInfo) {
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productDetailInfo, productInfo);
        productInfo.setOrderCode(orderCode);
        return productInfo;
    }

    static List<ProductInfo> convert(String orderCode,List<ProductDetailInfo> productDetailInfoList) {
        return productDetailInfoList.stream()
                .map(productDetailInfo -> ProductInfoConverter.convert(orderCode, productDetailInfo))
                .toList();
    }
}
