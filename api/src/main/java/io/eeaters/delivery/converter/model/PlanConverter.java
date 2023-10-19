package io.eeaters.delivery.converter.model;

import io.eeaters.delivery.entity.model.Plan;
import io.eeaters.delivery.entity.vo.plan.req.AddPlanReq;
import org.springframework.beans.BeanUtils;

public interface PlanConverter {

    static Plan convert(AddPlanReq addPlanReq) {
        Plan plan = new Plan();
        BeanUtils.copyProperties(addPlanReq, plan);
        plan.setCreateUser(addPlanReq.getOperateUser().getUserName());
        plan.setUpdateUser(plan.getCreateUser());
        return plan;
    }

}
