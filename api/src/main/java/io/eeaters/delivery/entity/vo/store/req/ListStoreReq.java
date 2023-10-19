package io.eeaters.delivery.entity.vo.store.req;

import io.eeaters.delivery.entity.base.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ListStoreReq {

    @Valid
    @Schema(name = "分页参数")
    private PageQuery pageQuery;

    @Schema(name = "门店名称")
    private String storeName;

    @Schema(name = "门店编号")
    private String storeCode;
}
