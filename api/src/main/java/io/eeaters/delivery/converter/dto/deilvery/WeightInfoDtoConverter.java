package io.eeaters.delivery.converter.dto.deilvery;

import io.eeaters.delivery.entity.dto.delivery.WeightInfoDTO;
import io.eeaters.delivery.entity.model.PlanChannelMapping;
import io.eeaters.delivery.enums.WeightTypeEnum;

import java.util.List;

public interface WeightInfoDtoConverter {

    static WeightInfoDTO convertDefault(List<PlanChannelMapping> channelMappingList) {
        WeightInfoDTO weightInfoDto = new WeightInfoDTO();
        weightInfoDto.setType(WeightTypeEnum.DEFAULT.getType());

        List<WeightInfoDTO.ChannelWeightInfo> weightInfos = channelMappingList.stream()
                .map(mapping -> {
                    WeightInfoDTO.ChannelWeightInfo weightInfo = new WeightInfoDTO.ChannelWeightInfo();
                    weightInfo.setChannel(mapping.getChannel());
                    weightInfo.setSort(mapping.getSort());
                    return weightInfo;
                })
                .toList();
        weightInfoDto.setInfoList(weightInfos);
        return weightInfoDto;
    }

}
