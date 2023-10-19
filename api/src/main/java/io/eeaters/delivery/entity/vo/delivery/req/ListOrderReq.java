package io.eeaters.delivery.entity.vo.delivery.req;

import io.eeaters.delivery.entity.base.PageQuery;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ListOrderReq {

    private String orderCode;

    private String storeNo;

    private LocalDate startTime;

    private LocalDate endTime;

    private PageQuery pageQuery;
}
