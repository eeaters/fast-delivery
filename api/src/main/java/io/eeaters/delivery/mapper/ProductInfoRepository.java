package io.eeaters.delivery.mapper;

import io.eeaters.delivery.entity.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,Long> {

    List<ProductInfo> findByOrderCode(String orderCode);
}
