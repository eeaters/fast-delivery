package io.eeaters.delivery.entity.vo.plan.req;

import io.eeaters.delivery.entity.base.OperateUser;
import lombok.Data;

import java.util.List;

@Data
public class AddPlanReq {

    private String planName;

    private String desc;

    private Integer timePeriod;

    private List<PlanChannelInfo> mappingList;

    private OperateUser operateUser;
}
