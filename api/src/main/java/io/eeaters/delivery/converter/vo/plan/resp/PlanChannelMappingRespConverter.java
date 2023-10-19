package io.eeaters.delivery.converter.vo.plan.resp;

import io.eeaters.delivery.entity.model.PlanChannelMapping;
import io.eeaters.delivery.entity.vo.plan.resp.PlanChannelMappingResp;
import io.eeaters.delivery.enums.ChannelEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class PlanChannelMappingRespConverter {

    static PlanChannelMappingResp convert(PlanChannelMapping mapping) {
        PlanChannelMappingResp resp = new PlanChannelMappingResp();
        BeanUtils.copyProperties(mapping, resp);
        resp.setChannelName(ChannelEnum.codeToName(resp.getChannel()));
        return resp;
    }

    static List<PlanChannelMappingResp> convert(List<PlanChannelMapping> mappingList) {
        if (CollectionUtils.isEmpty(mappingList)) {
            return new ArrayList<>();
        }
        return mappingList.stream()
                .map(PlanChannelMappingRespConverter::convert)
                .toList();
    }
}
