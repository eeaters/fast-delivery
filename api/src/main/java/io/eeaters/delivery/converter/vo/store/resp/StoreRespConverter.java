package io.eeaters.delivery.converter.vo.store.resp;

import io.eeaters.delivery.entity.model.Store;
import io.eeaters.delivery.entity.vo.store.resp.StoreResp;
import org.springframework.beans.BeanUtils;

import java.util.Map;
import java.util.Optional;

public interface StoreRespConverter {


    static StoreResp convert(Store store, Map<Integer, String> regionMap,Map<Long,String> planMap) {
        StoreResp storeResp = new StoreResp();
        BeanUtils.copyProperties(store, storeResp);
        Optional.ofNullable(store.getProvinceId()).ifPresent(id -> storeResp.setProvinceName(regionMap.get(id)));
        Optional.ofNullable(store.getCityId()).ifPresent(id -> storeResp.setCityName(regionMap.get(id)));
        Optional.ofNullable(store.getDistrictId()).ifPresent(id -> storeResp.setDistrictName(regionMap.get(id)));
        Optional.ofNullable(store.getPlanId()).ifPresent(id -> storeResp.setPlanName(planMap.get(id)));
        return storeResp;
    }
}
