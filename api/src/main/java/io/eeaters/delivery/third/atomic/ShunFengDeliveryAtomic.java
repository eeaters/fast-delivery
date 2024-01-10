package io.eeaters.delivery.third.atomic;

import io.eeaters.delivery.converter.dto.shunfeng.req.CreatePreDeliveryRequestConverter;
import io.eeaters.delivery.entity.dto.shunfeng.req.CreatePreDeliveryRequest;
import io.eeaters.delivery.entity.dto.shunfeng.resp.CreatePreDeliveryResponse;
import io.eeaters.delivery.entity.dto.shunfeng.resp.SfBaseResponse;
import io.eeaters.delivery.entity.dto.third.CreateDeliveryDTO;
import io.eeaters.delivery.entity.dto.third.CreatePreDeliveryDTO;
import io.eeaters.delivery.enums.ChannelEnum;
import io.eeaters.delivery.event.context.DeliveryCreateException;
import io.eeaters.delivery.third.client.ShunFengClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

@Component
@Slf4j
public class ShunFengDeliveryAtomic extends AbstractDeliveryAtomic {

    @Autowired
    ShunFengClient shunFengClient;


    @Override
    public ChannelEnum supportChannel() {
        return  ChannelEnum.SHUNFENT;
    }


    @Override
    protected CreatePreDeliveryDTO createPreDeliveryInternal(CreateDeliveryDTO createDeliveryDTO) {
        CreatePreDeliveryRequest deliveryRequest = CreatePreDeliveryRequestConverter.convert(createDeliveryDTO, getThirdConfig().getDelivery().getShunfeng());
        SfBaseResponse<CreatePreDeliveryResponse> preCreateOrder = shunFengClient.preCreateOrder(deliveryRequest);
        if (preCreateOrder.getErrorCode() != 0) {
            throw new DeliveryCreateException(preCreateOrder.getErrorMsg());
        }
        CreatePreDeliveryDTO createPreDeliveryDTO = new CreatePreDeliveryDTO();
        createPreDeliveryDTO.setTotalPrice(preCreateOrder.getResult().getTotalPrice());
        createPreDeliveryDTO.setExpectTime(Instant.ofEpochSecond(preCreateOrder.getResult().getExpectTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
        return createPreDeliveryDTO;
    }

    @Override
    protected void createDeliveryInternal(CreateDeliveryDTO createDeliveryDTO) {


    }
}
