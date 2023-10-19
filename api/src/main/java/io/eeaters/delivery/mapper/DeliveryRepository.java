package io.eeaters.delivery.mapper;

import io.eeaters.delivery.entity.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByOrderCode(String orderCode);

    List<Delivery> findByStatus(Integer status);

    //骑手接单
    @Modifying
    @Query("update #{#entityName} u set u.status = :status , u.riderReceiveTime = now() ,u.riderName = :name,u.riderPhone = :phone where u.id =:id")
    @Transactional
    int updateRiderReceived(Long id, String name, String phone, Integer status);
   //骑手到店
    @Modifying
    @Query("update #{#entityName} u set u.status = :status , u.arriveStoreTime = now() where u.id =:id")
    @Transactional
    int updateRiderArrivedStore(Long id, Integer status);

    //配送中
    @Modifying
    @Query("update #{#entityName} u set u.status = :status , u.deliveringTime = now() where u.id =:id")
    @Transactional
    int updateDelivering(Long id, Integer status);
    //配送完成
    @Modifying
    @Query("update #{#entityName} u set u.status = :status , u.completeTime = now() where u.id =:id")
    @Transactional
    int updateDeliveryCompleteTime(Long id, Integer status);

}
