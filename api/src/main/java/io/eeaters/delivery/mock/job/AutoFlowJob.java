package io.eeaters.delivery.mock.job;

import io.eeaters.delivery.entity.model.Delivery;
import io.eeaters.delivery.enums.DeliveryStatusEnum;
import io.eeaters.delivery.mapper.DeliveryRepository;
import io.eeaters.delivery.mock.support.NameGenerate;
import io.eeaters.delivery.mock.support.PhoneGenerate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
public class AutoFlowJob {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Scheduled(cron = "0 */10 * * * ? ")
    public void exec() {
        log.info("运单mock自动流转定时job处理开始");
        try {
            //配送中 -> 配送完成
            List<Delivery> deliveringDelivery = deliveryRepository.findByStatus(DeliveryStatusEnum.DELIVERING.getStatus());
            for (Delivery delivery : deliveringDelivery) {
                deliveryRepository.updateDeliveryCompleteTime(delivery.getId(), DeliveryStatusEnum.COMPLETED.getStatus());
            }
            //骑手到店 -> 配送中
            List<Delivery> arriveStoreDelivery = deliveryRepository.findByStatus(DeliveryStatusEnum.ARRIVE_STORE.getStatus());
            for (Delivery delivery : arriveStoreDelivery) {
                deliveryRepository.updateDelivering(delivery.getId(), DeliveryStatusEnum.DELIVERING.getStatus());
            }
            //已接单 -> 骑手到店
            List<Delivery> riderReceivedDelivery = deliveryRepository.findByStatus(DeliveryStatusEnum.RIDER_RECEIVED.getStatus());
            for (Delivery delivery : riderReceivedDelivery) {
                deliveryRepository.updateRiderArrivedStore(delivery.getId(), DeliveryStatusEnum.ARRIVE_STORE.getStatus());
            }
            //已推送 -> 已接单
            List<Delivery> receivedDelivery = deliveryRepository.findByStatus(DeliveryStatusEnum.RECEIVED.getStatus());
            for (Delivery delivery : receivedDelivery) {
                deliveryRepository.updateRiderReceived(delivery.getId(),
                        NameGenerate.getCnName(),
                        PhoneGenerate.getPhoneNumber(),
                        DeliveryStatusEnum.RIDER_RECEIVED.getStatus()
                );

            }
        } catch (Exception e) {
            log.error("运单mock自动流转定时job异常, 异常信息为: " + ExceptionUtils.getStackTrace(e));
        }

        log.info("运单mock自动流转定时job处理结束");
    }


}
