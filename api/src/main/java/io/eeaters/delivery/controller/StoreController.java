package io.eeaters.delivery.controller;

import io.eeaters.delivery.entity.base.PageResponse;
import io.eeaters.delivery.entity.vo.store.req.AddStoreReq;
import io.eeaters.delivery.entity.vo.store.req.ListStoreReq;
import io.eeaters.delivery.entity.vo.store.resp.StoreBaseInfo;
import io.eeaters.delivery.entity.vo.store.resp.StoreResp;
import io.eeaters.delivery.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("add")
    public Long pageStore(@Valid @RequestBody AddStoreReq req) {
        return storeService.addStore(req);
    }

    @PostMapping("page")
    public PageResponse<StoreResp> pageStore(@Valid @RequestBody ListStoreReq storeReq) {
        return storeService.pageStore(storeReq);
    }

    @GetMapping("listAll")
    public List<StoreBaseInfo> listAllStore() {
        return storeService.listAll();
    }
}
