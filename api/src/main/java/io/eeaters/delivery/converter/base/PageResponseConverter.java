package io.eeaters.delivery.converter.base;

import io.eeaters.delivery.entity.base.PageResponse;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.function.Function;

public interface PageResponseConverter {

    static <T, R> PageResponse<R> emptyPage(Page<T> page) {
        return new PageResponse<>((int) page.getTotalElements(), Collections.emptyList());
    }

    static <T,R> PageResponse<R> convert(Page<T> page, Function<T,R> itemConvert) {
        if (!page.hasContent()) {
            return emptyPage(page);
        }
        return new PageResponse<>((int) page.getTotalElements(),
                page.getContent().stream().map(itemConvert).toList());
    }

}
