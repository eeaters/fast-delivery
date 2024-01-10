package io.eeaters.delivery.converter.dto.shunfeng.req;

import io.eeaters.delivery.config.third.ThirdConfig;
import io.eeaters.delivery.entity.dto.shunfeng.req.CreatePreDeliveryRequest;
import io.eeaters.delivery.entity.dto.third.CreateDeliveryDTO;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public interface CreatePreDeliveryRequestConverter {

    static CreatePreDeliveryRequest convert(CreateDeliveryDTO createDeliveryDTO,
                                            ThirdConfig.SfProperty sfConfig) {
        CreatePreDeliveryRequest request = new CreatePreDeliveryRequest();
        request.setDevId(sfConfig.getAppId());
        request.setShopId(createDeliveryDTO.getStoreCode());
        request.setShopType(sfConfig.getShopType());
        request.setProductType(sfConfig.getProductType());
        request.setPushTime(Math.toIntExact(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))));
        request.setWeight(createDeliveryDTO.getWeightGram());
        request.setUserLat(String.valueOf(createDeliveryDTO.getUserInformation().getLatitude()));
        request.setUserLng(String.valueOf(createDeliveryDTO.getUserInformation().getLongitude()));
        request.setUserAddress(createDeliveryDTO.getUserInformation().getAddress());
        request.setReturnFlag(sfConfig.getReturnFlag());
        return request;
    }
}
