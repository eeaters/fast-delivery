package io.eeaters.delivery.entity.model;


import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

/**
* 
* @TableName order
*/
@Data
@Entity(name = "t_order")
public class Order implements Serializable {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
    * 门店编码
    */
    private String storeCode;
    /**
    * 订单编码
    */
    private String orderCode;
    /**
    * 回调地址
    */
    private String callbackUrl;
    /**
    * 订单总金额
    */
    private Integer totalPrice;
    /**
    * 收货联系人
    */
    private String receiveUserName;
    /**
    * 收货人联系电话
    */
    private String receiveUserPhone;
    /**
    * 收货人联系地址
    */
    private String receiveUserAddress;
    /**
     * 用户下单时间
     */
    private LocalDateTime orderTime;
    /**
     * 权重信息
     */
    @Column(name = "weight_info")
    private String weightInfo;
    /**
     * 0：正常数据 1:mock数据
     */
    private Boolean isMock;

    /**
    * 创建人
    */
    private String createUser;
    /**
     * 创建时间
     */
    @Column(name = "create_time", insertable = false)
    private LocalDateTime createTime;


}
