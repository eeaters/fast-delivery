package io.eeaters.delivery.converter.dto;

import io.eeaters.delivery.entity.dto.delivery.WeightInfoDto;
import io.eeaters.delivery.entity.model.PlanChannelMapping;
import io.eeaters.delivery.enums.WeightTypeEnum;

import java.util.List;

public interface WeightInfoDtoConverter {

    static WeightInfoDto convertDefault(List<PlanChannelMapping> channelMappingList) {
        WeightInfoDto weightInfoDto = new WeightInfoDto();
        weightInfoDto.setType(WeightTypeEnum.DEFAULT.getType());

        List<WeightInfoDto.ChannelWeightInfo> weightInfos = channelMappingList.stream()
                .map(mapping -> {
                    WeightInfoDto.ChannelWeightInfo weightInfo = new WeightInfoDto.ChannelWeightInfo();
                    weightInfo.setChannel(mapping.getChannel());
                    weightInfo.setSort(mapping.getSort());
                    return weightInfo;
                })
                .toList();
        weightInfoDto.setInfoList(weightInfos);
        return weightInfoDto;
    }

}
