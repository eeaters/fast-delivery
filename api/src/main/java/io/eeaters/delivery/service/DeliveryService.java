package io.eeaters.delivery.service;

import io.eeaters.delivery.converter.base.PageResponseConverter;
import io.eeaters.delivery.converter.model.OrderConverter;
import io.eeaters.delivery.converter.model.ProductInfoConverter;
import io.eeaters.delivery.converter.vo.delivery.resp.DeliveryInfoConverter;
import io.eeaters.delivery.converter.vo.delivery.resp.OrderInfoConverter;
import io.eeaters.delivery.entity.base.PageResponse;
import io.eeaters.delivery.entity.model.Delivery;
import io.eeaters.delivery.entity.model.Order;
import io.eeaters.delivery.entity.model.ProductInfo;
import io.eeaters.delivery.entity.model.Store;
import io.eeaters.delivery.entity.vo.delivery.req.CreateDeliveryReq;
import io.eeaters.delivery.entity.vo.delivery.req.ListOrderReq;
import io.eeaters.delivery.entity.vo.delivery.resp.DeliveryInfo;
import io.eeaters.delivery.entity.vo.delivery.resp.OrderInfo;
import io.eeaters.delivery.event.DeliveryCreateEvent;
import io.eeaters.delivery.event.EventPublisher;
import io.eeaters.delivery.mapper.DeliveryRepository;
import io.eeaters.delivery.mapper.OrderRepository;
import io.eeaters.delivery.mapper.ProductInfoRepository;
import io.eeaters.delivery.mapper.StoreRepository;
import io.eeaters.delivery.util.Asserts;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    EventPublisher eventPublisher;

    @Transactional
    public Long createDelivery(CreateDeliveryReq req, Boolean mock) {
        Order order = orderRepository.findByOrderCode(req.getOrderCode());
        //重复推送
        if (order != null) {
            return order.getId();
        }

        Store store = storeRepository.findByStoreCode(req.getStoreCode());
        Asserts.isNull(store, "门店不存在");
        Asserts.isNull(store.getPlanId(), "门店未绑定方案");

        Order saveOrder = OrderConverter.convert(req, mock);
        List<ProductInfo> productInfos = ProductInfoConverter.convert(req.getOrderCode(), req.getDetailInfos());

        orderRepository.save(saveOrder);
        productInfoRepository.saveAll(productInfos);

        eventPublisher.deliveryCreate(new DeliveryCreateEvent(saveOrder.getId()));
        return saveOrder.getId();


    }

    public PageResponse<OrderInfo> pageOrder(ListOrderReq req) {
        PageRequest pageRequest = req.getPageQuery()
                .toPageRequest()
                .withSort(Sort.by(Sort.Direction.DESC, "orderTime"));

        Page<Order> orders = orderRepository.findAll((Specification<Order>) (root, query, criteriaBuilder) -> {
            List<Predicate> ps = new ArrayList<>();
            if (StringUtils.isNotBlank(req.getStoreNo())) {
                ps.add(criteriaBuilder.equal(root.get("storeCode"), req.getStoreNo()));
            }
            if (StringUtils.isNotBlank(req.getOrderCode())) {
                ps.add(criteriaBuilder.equal(root.get("orderCode"), req.getOrderCode()));
            }
            if (req.getStartTime() != null && req.getEndTime() != null) {
                ps.add(criteriaBuilder.between(root.get("orderTime"), req.getStartTime(), req.getEndTime()));
            }
            return query.where(ps.toArray(new Predicate[0])).getRestriction();
        }, pageRequest);
        return PageResponseConverter.convert(orders, OrderInfoConverter::convert);
    }

    public DeliveryInfo latestDelivery(String orderCode) {
        List<Delivery> deliveryList = deliveryRepository.findByOrderCode(orderCode);
        return deliveryList.stream()
                .max(Comparator.comparing(Delivery::getCreateTime))
                .map(DeliveryInfoConverter::convert)
                .orElse(null);
    }

}
