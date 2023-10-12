package io.eeaters.delivery.entity.vo.delivery.resp;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderInfoResp {


    private String storeCode;
    private String orderCode;
    private String receiveUserName;
    private String receiveUserPhone;
    private String receiveUserAddress;

    private Integer totalPrice;
    private LocalDateTime orderTime;
    private String weightInfo;
    private Boolean isMock;


}
