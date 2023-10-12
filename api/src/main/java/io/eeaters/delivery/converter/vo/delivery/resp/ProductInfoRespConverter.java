package io.eeaters.delivery.converter.vo.delivery.resp;

import io.eeaters.delivery.entity.model.ProductInfo;
import io.eeaters.delivery.entity.vo.delivery.resp.ProductInfoResp;
import org.springframework.beans.BeanUtils;

import java.util.List;

public interface ProductInfoRespConverter {

    static List<ProductInfoResp> converter(List<ProductInfo> productInfos) {
        return productInfos.stream()
                .map(ProductInfoRespConverter::converter)
                .toList();
    }

    static ProductInfoResp converter(ProductInfo productInfo) {
        ProductInfoResp resp = new ProductInfoResp();
        BeanUtils.copyProperties(productInfo, resp);
        return resp;
    }

}
