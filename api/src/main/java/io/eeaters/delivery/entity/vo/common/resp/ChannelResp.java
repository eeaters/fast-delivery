package io.eeaters.delivery.entity.vo.common.resp;

import io.eeaters.delivery.enums.ChannelEnum;
import lombok.Data;

@Data
public class ChannelResp {

    private String name;

    private String code;

    public static ChannelResp of(ChannelEnum channelEnum) {
        ChannelResp resp = new ChannelResp();
        resp.setName(channelEnum.getName());
        resp.setCode(channelEnum.getCode());
        return resp;
    }
}
