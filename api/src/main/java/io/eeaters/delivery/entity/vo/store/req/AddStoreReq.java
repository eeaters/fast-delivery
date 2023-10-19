package io.eeaters.delivery.entity.vo.store.req;

import io.eeaters.delivery.entity.base.OperateUser;
import lombok.Data;

@Data
public class AddStoreReq {

    private Long id;

    private String storeName;

    private String storeCode;

    private Integer provinceId;

    private Integer cityId;

    private Integer districtId;

    private String address;

    private String latitude;

    private String longitude;

    private String storeContract;

    private String storeContractPhone;

    private Long planId;

    private OperateUser operateUser;

}
