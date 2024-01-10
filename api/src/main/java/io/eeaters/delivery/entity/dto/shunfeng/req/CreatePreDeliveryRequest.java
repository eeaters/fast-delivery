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

    @JsonProperty("return_flag")
    private Integer returnFlag;
}
