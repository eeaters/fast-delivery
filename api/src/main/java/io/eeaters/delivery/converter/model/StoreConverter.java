package io.eeaters.delivery.converter.model;

import io.eeaters.delivery.entity.model.Store;
import io.eeaters.delivery.entity.vo.store.req.AddStoreReq;
import org.springframework.beans.BeanUtils;

public interface StoreConverter {

    static Store convert(AddStoreReq storeReq) {
        Store store = new Store();
        BeanUtils.copyProperties(storeReq, store);
        store.setCreateUser(storeReq.getOperateUser().getUserName());
        store.setUpdateUser(store.getCreateUser());
        return store;
    }
}
