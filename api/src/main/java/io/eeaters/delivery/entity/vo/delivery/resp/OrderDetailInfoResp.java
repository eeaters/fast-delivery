package io.eeaters.delivery.entity.vo.delivery.resp;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailInfoResp {

    OrderInfoResp orderInfo;

    List<ProductInfoResp> productInfos;

}
