package io.eeaters.delivery.mapper;

import io.eeaters.delivery.entity.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {


    Order findByOrderCode(String orderCode);

    @Modifying
    @Query("update #{#entityName} u set u.weightInfo = :weightInfo where u.id =:orderId")
    @Transactional
    void updateWeightInfoByOrderId(@Param("orderId") Long orderId, @Param("weightInfo") String weightInfo);

}
