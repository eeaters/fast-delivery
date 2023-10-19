package io.eeaters.delivery.converter.model;

import io.eeaters.delivery.entity.base.OperateUser;
import io.eeaters.delivery.entity.model.PlanChannelMapping;
import io.eeaters.delivery.entity.vo.plan.req.PlanChannelInfo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public interface PlanChannelMappingConverter {

    static PlanChannelMapping convert(Long planId, OperateUser operateUser, PlanChannelInfo planChannelInfo) {
        PlanChannelMapping planChannelMapping = new PlanChannelMapping();
        planChannelMapping.setPlanId(planId);
        planChannelMapping.setSort(planChannelInfo.getSort());
        planChannelMapping.setChannel(planChannelInfo.getChannel());
        planChannelMapping.setCreateUser(operateUser.getUserName());
        return planChannelMapping;
    }


    static List<PlanChannelMapping> convert(Long planId, OperateUser operateUser, List<PlanChannelInfo> planChannelInfos) {

        if (CollectionUtils.isEmpty(planChannelInfos)) {
            return new ArrayList<>();
        }
        return planChannelInfos.stream()
                .map(mapping -> convert(planId, operateUser, mapping))
                .toList();
    }


}
