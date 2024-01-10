package io.eeaters.delivery.entity.dto.third;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePreDeliveryDTO {

    //预计配送的金额
    private Integer totalPrice;

    //预计送达时间
    private LocalDateTime expectTime;

}
