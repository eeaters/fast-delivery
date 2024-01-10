package io.eeaters.delivery.event.listener;

import io.eeaters.delivery.entity.dto.delivery.WeightInfoDTO;
import io.eeaters.delivery.entity.model.Delivery;
import io.eeaters.delivery.enums.DeliveryStatusEnum;
import io.eeaters.delivery.event.context.PushDeliveryContext;
import io.eeaters.delivery.event.DeliveryCreateEvent;
import io.eeaters.delivery.mapper.DeliveryRepository;
import io.eeaters.delivery.util.AsyncTraceSupport;
import io.eeaters.delivery.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Iterator;

@Component
@Slf4j
public class DeliveryPushListener implements ApplicationListener<DeliveryCreateEvent> {


    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    @Async
    public void onApplicationEvent(DeliveryCreateEvent event) {
        AsyncTraceSupport.execute(event.getTraceId(), this.getClass().getSimpleName(), () -> pushDelivery(event.getOrderId()));
    }

    /**
     * 如果是mock运单, 则取第一个配送取单, 默认推送成功
     * @param orderId
     */
    private void pushDelivery(Long orderId) {
        PushDeliveryContext pushDeliveryContext = new PushDeliveryContext(orderId);
        if (pushDeliveryContext.isMock()) {
            mockDelivery(pushDeliveryContext);
        }else{

        }
    }


    private void mockDelivery(PushDeliveryContext pushDeliveryContext) {
        Iterator<WeightInfoDTO.ChannelWeightInfo> infoIterator = pushDeliveryContext
                .channelIterable()
                .iterator();

        if (infoIterator.hasNext()) {
            WeightInfoDTO.ChannelWeightInfo weightInfo = infoIterator.next();
            String channel = weightInfo.getChannel();
            Delivery delivery = new Delivery();
            delivery.setChannel(channel);
            delivery.setStatus(DeliveryStatusEnum.RECEIVED.getStatus());
            delivery.setPushTime(LocalDateTime.now());
            delivery.setChannelReceiveTime(LocalDateTime.now());
            delivery.setExceptTime(LocalDateTime.now().minusMinutes(30));
            delivery.setChannelPrice(1200);
            delivery.setChannelOrderId(RandomUtils.random());
            delivery.setOrderCode(pushDeliveryContext.getOrder().getOrderCode());
            delivery.setOrderId(pushDeliveryContext.getOrder().getId());
            deliveryRepository.save(delivery);
        }
    }

}
