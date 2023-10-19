package io.eeaters.delivery.entity.vo.plan.resp;

import lombok.Data;

@Data
public class PlanChannelMappingResp {

    private Long id;

    private String channel;

    private String channelName;

    private Integer sort;
}
