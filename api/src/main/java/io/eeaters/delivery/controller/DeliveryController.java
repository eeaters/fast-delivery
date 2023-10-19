package io.eeaters.delivery.controller;

import io.eeaters.delivery.entity.base.PageResponse;
import io.eeaters.delivery.entity.vo.delivery.req.CreateDeliveryReq;
import io.eeaters.delivery.entity.vo.delivery.req.ListOrderReq;
import io.eeaters.delivery.entity.vo.delivery.resp.DeliveryInfo;
import io.eeaters.delivery.entity.vo.delivery.resp.OrderInfo;
import io.eeaters.delivery.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping("create")
    public Long createDelivery(@RequestHeader(value = "mock", defaultValue = "false") Boolean mock,
                                             @Valid @RequestBody CreateDeliveryReq deliveryReq) {
        return deliveryService.createDelivery(deliveryReq, mock);
    }


    @PostMapping("order/page")
    public PageResponse<OrderInfo> pageOrder(@RequestBody @Valid ListOrderReq req) {
        return deliveryService.pageOrder(req);
    }

    @GetMapping("latest")
    public DeliveryInfo getLatestDelivery(@RequestParam("orderCode") String orderCode) {
        return deliveryService.latestDelivery(orderCode);
    }


}
