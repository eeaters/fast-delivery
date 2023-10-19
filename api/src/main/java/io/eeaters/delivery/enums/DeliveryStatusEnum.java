package io.eeaters.delivery.enums;

import lombok.Getter;

@Getter
public enum DeliveryStatusEnum {

    CREATE(1, "订单已创建"),


    PUSH(9, "已推送三方"),
    RECEIVED (10,"三方已接单"),

    RIDER_RECEIVED(11,"骑手已接单"),
    ARRIVE_STORE(12,"骑手已到店"),
    DELIVERING(13,"骑手配送中"),

    ERROR(18,"异常订单"),

    COMPLETED(20,"配送完成"),
    CANCEL(21,"取消"),
    RETURN(22, "拒收退回门店"),
    ;

    private Integer status;
    private String desc;
    DeliveryStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
