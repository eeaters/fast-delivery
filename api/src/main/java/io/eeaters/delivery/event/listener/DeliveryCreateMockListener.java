package io.eeaters.delivery.event.listener;

import io.eeaters.delivery.entity.dto.delivery.WeightInfoDto;
import io.eeaters.delivery.entity.model.Delivery;
import io.eeaters.delivery.enums.DeliveryStatusEnum;
import io.eeaters.delivery.event.context.PushDeliveryContext;
import io.eeaters.delivery.event.DeliveryCreateEvent;
import io.eeaters.delivery.mapper.DeliveryRepository;
import io.eeaters.delivery.util.AsyncTraceSupport;
import io.eeaters.delivery.util.JsonUtils;
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
public class DeliveryCreateMockListener implements ApplicationListener<DeliveryCreateEvent> {


    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    @Async
    public void onApplicationEvent(DeliveryCreateEvent event) {
        AsyncTraceSupport.execute(event.getTraceId(), this.getClass().getSimpleName(), () -> mockDelivery(event.getOrderId()));
    }

    /**
     * 如果是mock运单, 则取第一个配送取单, 默认推送成功
     * @param orderId
     */
    private void mockDelivery(Long orderId) {
        PushDeliveryContext pushDeliveryContext = new PushDeliveryContext(orderId);
        if (!pushDeliveryContext.isMock()) {
            log.info("DeliveryCreateMockListener 不处理正常订单, 订单信息: " + JsonUtils.writeValueAsString(pushDeliveryContext.getOrder()));
            return;
        }
        Iterator<WeightInfoDto.ChannelWeightInfo> infoIterator = pushDeliveryContext
                .channelIterable()
                .iterator();

        if (infoIterator.hasNext()) {
            WeightInfoDto.ChannelWeightInfo weightInfo = infoIterator.next();
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
