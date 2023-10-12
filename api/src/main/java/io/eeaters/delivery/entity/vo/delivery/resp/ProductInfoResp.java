package io.eeaters.delivery.entity.vo.delivery.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductInfoResp {

    @Schema(name = "商品名称", example = "九转大肠")
    private String productName;

    @Schema(name = "商品数量", example = "1")
    private Integer productNum;

    @Schema(name = "商品描述", example = "原汁原味、回味无穷")
    private String productRemark;
}
