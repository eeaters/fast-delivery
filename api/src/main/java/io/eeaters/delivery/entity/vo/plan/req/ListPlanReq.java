package io.eeaters.delivery.entity.vo.plan.req;

import io.eeaters.delivery.entity.base.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ListPlanReq {

    @Valid
    @Schema(name = "分页参数")
    private PageQuery pageQuery;

    private String planName;

}
