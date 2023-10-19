package io.eeaters.delivery.converter.vo.store.resp;

import io.eeaters.delivery.entity.model.Store;
import io.eeaters.delivery.entity.vo.store.resp.StoreBaseInfo;

import java.util.List;

public interface StoreBaseInfoConverter {
    static StoreBaseInfo convert(Store store) {
        StoreBaseInfo storeBaseInfo = new StoreBaseInfo();
        storeBaseInfo.setStoreCode(store.getStoreCode());
        storeBaseInfo.setStoreName(store.getStoreName());
        return storeBaseInfo;
    }

    static List<StoreBaseInfo> convert(List<Store> storeList) {
        return storeList.stream().map(StoreBaseInfoConverter::convert).toList();
    }
}
