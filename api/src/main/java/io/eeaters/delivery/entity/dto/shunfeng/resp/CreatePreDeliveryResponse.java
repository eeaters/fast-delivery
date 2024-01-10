package io.eeaters.delivery.entity.dto.shunfeng.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreatePreDeliveryResponse {

    @JsonProperty("total_price")
    private Integer totalPrice;

    @JsonProperty("expect_time")
    private Integer expectTime;
}
