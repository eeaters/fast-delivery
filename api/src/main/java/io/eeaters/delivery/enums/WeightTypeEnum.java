package io.eeaters.delivery.enums;

import lombok.Getter;

@Getter
public enum WeightTypeEnum {

    DEFAULT(1, "默认排序"),

    DISTANCE(2, "时间排序"),

    PRICE(3, "价格排序"),
    ;

    private Integer type;
    private String desc;

    WeightTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
