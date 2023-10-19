package io.eeaters.delivery.mapper;


import io.eeaters.delivery.entity.model.Store;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    default Page<Store> pageByStoreNameAndStoreCode(String storeName, String storeCode, PageRequest pageRequest) {
        Store store = new Store();
        if (StringUtils.isNotBlank(storeCode)) {
            store.setStoreCode(storeCode);
        }
        if (StringUtils.isNotBlank(storeName)) {
            store.setStoreName(storeName);
        }
        Example<Store> example = Example.of(store);
        return findAll(example, pageRequest);
    }

    Store findByStoreCode(String storeCode);
}
