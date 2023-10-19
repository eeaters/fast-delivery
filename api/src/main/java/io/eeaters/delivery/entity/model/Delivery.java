package io.eeaters.delivery.entity.model;


import java.io.Serializable;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

/**
* 运单
* @TableName delivery
*/
@Data
@Entity(name = "t_delivery")
public class Delivery implements Serializable {

    /**
    * 
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
    * 订单id
    */
    private Long orderId;
    /**
    * 订单code
    */
    private String orderCode;
    /**
    * 三方渠道订单编号
    */
    private String channelOrderId;
    /**
    * 三方配送费
    */
    private Integer channelPrice;
    /**
    * 配送状态
    */
    private Integer status;
    /**
     * 配送渠道
     */
    private String channel;
    /**
    * 推送时间
    */
    private LocalDateTime pushTime;
    /**
    * 骑手接单时间
    */
    private LocalDateTime riderReceiveTime;
    /**
    * 骑手到店时间
    */
    private LocalDateTime arriveStoreTime;
    /**
    * 开始配送时间
    */
    private LocalDateTime deliveringTime;
    /**
     * 配送完成时间
     */
    private LocalDateTime completeTime;
    /**
    * 订单异常时间
    */
    private LocalDateTime errorTime;
    /**
    * 异常时三方异常码
    */
    private String errorCode;
    /**
    * 异常时三方异常原因
    */
    private String errorMessage;
    /**
    * 取消时间
    */
    private LocalDateTime cancelTime;
    /**
    * 取消时,取消code
    */
    private String cancelCode;
    /**
    * 取消原因
    */
    private String cancelMessage;
    /**
    * 骑手姓名
    */
    private String riderName;
    /**
    * 骑手联系电话
    */
    private String riderPhone;
    /**
    * 创建时间
    */
    @Column(insertable = false)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(insertable = false, updatable = false)
    private LocalDateTime updateTime;

}
