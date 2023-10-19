package io.eeaters.delivery.entity.vo.store.resp;

import lombok.Data;

@Data
public class StoreResp {

    private Long id;

    private String storeName;

    private String storeCode;

    private String provinceName;

    private Integer provinceId;

    private String cityName;

    private Integer cityId;

    private String districtName;

    private Integer districtId;

    private String address;

    private String latitude;

    private String longitude;

    private String storeContract;

    private String storeContractPhone;

    private Long planId;

    private String planName;
}
