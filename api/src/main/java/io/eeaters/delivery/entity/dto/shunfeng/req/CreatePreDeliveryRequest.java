package io.eeaters.delivery.entity.dto.shunfeng.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreatePreDeliveryRequest {

    @JsonProperty("dev_id")
    private Integer devId;

    @JsonProperty("shop_id")
    private String shopId;

    @JsonProperty("shop_type")
    private Integer shopType;

    @JsonProperty("user_lng")
    private String userLng;

    @JsonProperty("user_lat")
    private String userLat;

    @JsonProperty("user_address")
    private String userAddress;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("product_type")
    private Integer productType;

    @JsonProperty("push_time")
    private Integer pushTime;
    //1:商品总价格，2:配送距离，4:物品重量，8:起送时间，16:期望送达时间，32:支付费用，64:实际支持金额，128:优惠券总金额，256:结算方式
    //例如全部返回为填入511
    @JsonProperty("return_flag")
    private Integer returnFlag;
}
