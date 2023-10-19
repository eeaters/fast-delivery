package io.eeaters.delivery.entity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "t_store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_code")
    private String storeCode;

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "store_contract")
    private String storeContract;

    @Column(name = "store_contract_phone")
    private String storeContractPhone;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time", insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time",insertable = false,updatable = false)
    private LocalDateTime updateTime;

}
