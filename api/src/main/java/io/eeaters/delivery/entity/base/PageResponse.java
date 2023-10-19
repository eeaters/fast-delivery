package io.eeaters.delivery.entity.base;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T>{

    private Integer total;

    private List<T> list;

    public PageResponse(Integer total, List<T> list) {
        this.total = total;
        this.list = list;
    }
}
