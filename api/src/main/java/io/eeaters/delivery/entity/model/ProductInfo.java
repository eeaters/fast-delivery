package io.eeaters.delivery.entity.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


/**
* 商品明细
* @TableName product_info
*/
@Data
@Entity(name = "t_product_info")
public class ProductInfo implements Serializable {

    /**
    * 主键id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
    * 订单code
    */
    @Column(name = "order_code")
    private String orderCode;
    /**
    * 商品名称
    */
    @Column(name = "product_name")
    private String productName;
    /**
    * 商品数量
    */
    @Column(name = "product_num")
    private Integer productNum;
    /**
    * 商品描述
    */
    @Column(name = "product_remark")
    private String productRemark;

}
