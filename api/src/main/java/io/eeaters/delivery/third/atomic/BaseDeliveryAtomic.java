package io.eeaters.delivery.third.atomic;

import io.eeaters.delivery.entity.dto.third.CreateDeliveryDTO;
import io.eeaters.delivery.entity.dto.third.CreatePreDeliveryDTO;
import io.eeaters.delivery.enums.ChannelEnum;

public interface BaseDeliveryAtomic {
    //支持的渠道
    ChannelEnum supportChannel();

    //创建预订单
    CreatePreDeliveryDTO createPreOrder(CreateDeliveryDTO createDeliveryDTO);

    //创建订单
    void createDelivery(CreateDeliveryDTO createDeliveryDTO);
}
