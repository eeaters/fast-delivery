package io.eeaters.delivery.enums;

import lombok.Getter;

import java.util.Objects;


@Getter
public enum ChannelEnum {


    MEITUAN("美团","meituan"),
    FENGNIAO("蜂鸟即配", "fengniao"),
    DADA("达达", "dada"),
    SHUNFENT("顺丰同城","shunfeng"),
    SHANSONG("闪送", "shansong"),
    UU_PAOTUI("UU跑腿","uu")
    ;

    private String name;

    private String code;

    ChannelEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static String codeToName(String code) {
        for (ChannelEnum value : values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value.getName();
            }
        }
        return "非法渠道";
    }

}
