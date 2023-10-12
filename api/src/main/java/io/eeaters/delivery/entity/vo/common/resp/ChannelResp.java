package io.eeaters.delivery.entity.vo.common.resp;

import io.eeaters.delivery.enums.ChannelEnum;
import lombok.Data;

@Data
public class ChannelResp {

    private String channelName;

    private String channel;

    public static ChannelResp of(ChannelEnum channelEnum) {
        ChannelResp resp = new ChannelResp();
        resp.setChannelName(channelEnum.getName());
        resp.setChannel(channelEnum.getCode());
        return resp;
    }
}
