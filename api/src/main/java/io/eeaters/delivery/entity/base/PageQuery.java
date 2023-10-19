package io.eeaters.delivery.entity.base;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class PageQuery {

    private Integer pageNum;

    private Integer pageSize;

    public PageRequest toPageRequest() {
        return PageRequest.of(pageNum - 1, pageSize);
    }

}
