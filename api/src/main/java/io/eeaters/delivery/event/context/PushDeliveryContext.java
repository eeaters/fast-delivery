package io.eeaters.delivery.event.context;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.eeaters.delivery.converter.dto.deilvery.WeightInfoDtoConverter;
import io.eeaters.delivery.entity.dto.delivery.WeightInfoDTO;
import io.eeaters.delivery.entity.model.*;
import io.eeaters.delivery.enums.WeightTypeEnum;
import io.eeaters.delivery.mapper.*;
import io.eeaters.delivery.util.JsonUtils;
import io.eeaters.delivery.util.NonExSupplier;
import io.eeaters.delivery.util.SpringUtils;

import java.util.List;
import java.util.Optional;


/**
 * 运单推送的上下文
 */
public class PushDeliveryContext {

    //减少null值序列化,降低存储空间
    public static ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private final Order order;

    private Store store;

    private Plan plan;

    List<PlanChannelMapping> mappings;

    public PushDeliveryContext(String orderCode) {
        this.order = SpringUtils.getBean(OrderRepository.class).findByOrderCode(orderCode);
    }

    public PushDeliveryContext(Long orderId) {
        this.order = SpringUtils.getBean(OrderRepository.class).findById(orderId).orElseThrow();
    }

    public Order getOrder() {
        return this.order;
    }

    public Boolean isMock() {
        return getOrder().getIsMock();
    }

    /**
     * 推送渠道迭代器
     *
     * @return
     */
    public Iterable<WeightInfoDTO.ChannelWeightInfo> channelIterable() {
        Order currentOrder = getOrder();

        WeightInfoDTO weightInfoDto = Optional.ofNullable(currentOrder.getWeightInfo())
                .map(info -> JsonUtils.readValue(info, WeightInfoDTO.class))
                .orElse(initWeightInfo());


        //已经推送过的渠道
        List<String> pushChannels = SpringUtils.getBean(DeliveryRepository.class).findByOrderCode(currentOrder.getOrderCode())
                .stream()
                .map(Delivery::getChannel)
                .toList();

        return weightInfoDto.getInfoList()
                .stream()
                .filter(info -> !pushChannels.contains(info.getChannel()))
                .toList();

    }

    private WeightInfoDTO initWeightInfo() {
        Plan currentPlan = getPlan();

        Integer type = currentPlan.getType();
        if (!WeightTypeEnum.DEFAULT.getType().equals(type)) {
            throw new RuntimeException("暂不支持该排序规则");
        }
        //todo 价格优先/时间优先依赖三方接口调用,暂不支持
        WeightInfoDTO weightInfoDto = WeightInfoDtoConverter.convertDefault(getMappings());

        SpringUtils.getBean(OrderRepository.class).updateWeightInfoByOrderId(getOrder().getId(),
                NonExSupplier.exec(() -> objectMapper.writeValueAsString(weightInfoDto)));

        return weightInfoDto;
    }


    private Store getStore() {
        if (this.store == null) {
            this.store = SpringUtils.getBean(StoreRepository.class).findByStoreCode(getOrder().getStoreCode());
        }
        if (this.store == null) {
            throw new DeliveryCreateException("订单无对应门店");
        }
        return this.store;
    }

    private Plan getPlan() {
        if (this.plan == null) {
            this.plan = SpringUtils.getBean(PlanRepository.class).findById(getStore().getPlanId()).orElseThrow();
        }
        if (this.plan == null) {
            throw new DeliveryCreateException("门店无对应配送方案");
        }
        return this.plan;
    }

    private List<PlanChannelMapping> getMappings() {
        if (this.mappings == null) {
            Plan currentPlan = getPlan();
            this.mappings = SpringUtils.getBean(PlanChannelMappingRepository.class)
                    .findAllByPlanIdIn(List.of(currentPlan.getId()));
        }
        return this.mappings;
    }
}
