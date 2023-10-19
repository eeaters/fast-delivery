package io.eeaters.delivery.converter.vo.plan.resp;

import io.eeaters.delivery.entity.model.Plan;
import io.eeaters.delivery.entity.vo.plan.resp.PlanBaseInfoResp;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public interface PlanBaseInfoRespConverter {

    static PlanBaseInfoResp convert(Plan plan) {
        PlanBaseInfoResp resp = new PlanBaseInfoResp();
        resp.setPlanName(plan.getPlanName());
        resp.setId(plan.getId());
        return resp;
    }

    static List<PlanBaseInfoResp> convert(List<Plan> plans) {
        if (CollectionUtils.isEmpty(plans)) {
            return new ArrayList<>();
        }
        return plans.stream().map(PlanBaseInfoRespConverter::convert).toList();
    }
}
