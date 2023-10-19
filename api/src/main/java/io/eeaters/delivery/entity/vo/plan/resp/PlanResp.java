package io.eeaters.delivery.entity.vo.plan.resp;

import lombok.Data;

import java.util.List;

@Data
public class PlanResp {

    private Long id;

    private String planName;

    private String desc;

    private Integer timePeriod;

    private List<PlanChannelMappingResp> mappingList;

}
