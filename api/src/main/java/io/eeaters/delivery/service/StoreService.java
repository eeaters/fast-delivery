package io.eeaters.delivery.service;

import io.eeaters.delivery.converter.base.PageResponseConverter;
import io.eeaters.delivery.converter.model.StoreConverter;
import io.eeaters.delivery.converter.vo.store.resp.StoreBaseInfoConverter;
import io.eeaters.delivery.converter.vo.store.resp.StoreRespConverter;
import io.eeaters.delivery.entity.base.PageResponse;
import io.eeaters.delivery.entity.model.Plan;
import io.eeaters.delivery.entity.model.Region;
import io.eeaters.delivery.entity.model.Store;
import io.eeaters.delivery.entity.vo.store.req.AddStoreReq;
import io.eeaters.delivery.entity.vo.store.req.ListStoreReq;
import io.eeaters.delivery.entity.vo.store.resp.StoreBaseInfo;
import io.eeaters.delivery.entity.vo.store.resp.StoreResp;
import io.eeaters.delivery.mapper.PlanRepository;
import io.eeaters.delivery.mapper.RegionRepository;
import io.eeaters.delivery.mapper.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private PlanRepository planRepository;

    public Long addStore(AddStoreReq addStoreReq) {
        Store store = StoreConverter.convert(addStoreReq);
        store = storeRepository.save(store);
        return store.getId();
    }

    public PageResponse<StoreResp> pageStore(ListStoreReq storeReq) {
        Page<Store> stores = storeRepository.pageByStoreNameAndStoreCode(storeReq.getStoreName(), storeReq.getStoreCode(), storeReq.getPageQuery().toPageRequest());

        if (CollectionUtils.isEmpty(stores.getContent())) {
            return PageResponseConverter.emptyPage(stores);
        }

        Set<Integer> allRegionIds = new HashSet<>();
        Set<Long> planIds = new HashSet<>();
        stores.getContent().forEach(store-> {
            Optional.ofNullable(store.getCityId()).ifPresent(allRegionIds::add);
            Optional.ofNullable(store.getProvinceId()).ifPresent(allRegionIds::add);
            Optional.ofNullable(store.getDistrictId()).ifPresent(allRegionIds::add);
            Optional.ofNullable(store.getPlanId()).ifPresent(planIds::add);
        });

        Map<Integer, String> regionIdToNameMap = regionRepository.findAllById(allRegionIds)
                .stream()
                .collect(Collectors.toMap(Region::getId, Region::getName, (existing, replacement) -> existing));

        Map<Long, String> planIdToNameMap = planRepository.findAllById(planIds)
                .stream()
                .collect(Collectors.toMap(Plan::getId, Plan::getPlanName));
        return PageResponseConverter.convert(stores, store -> StoreRespConverter.convert(store, regionIdToNameMap, planIdToNameMap));
    }

    public List<StoreBaseInfo> listAll() {
        List<Store> storeList = storeRepository.findAll();
        return StoreBaseInfoConverter.convert(storeList);
    }

}
