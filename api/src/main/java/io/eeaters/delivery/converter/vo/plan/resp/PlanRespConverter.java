package io.eeaters.delivery.converter.vo.plan.resp;

import io.eeaters.delivery.entity.model.Plan;
import io.eeaters.delivery.entity.model.PlanChannelMapping;
import io.eeaters.delivery.entity.vo.plan.resp.PlanResp;
import org.springframework.beans.BeanUtils;

import java.util.List;

public interface PlanRespConverter {

    static PlanResp convert(Plan plan, List<PlanChannelMapping> mappingList) {
        PlanResp planResp = new PlanResp();
        BeanUtils.copyProperties(plan, planResp);
        planResp.setMappingList(PlanChannelMappingRespConverter.convert(mappingList));
        return planResp;
    }


}
