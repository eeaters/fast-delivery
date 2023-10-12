package io.eeaters.delivery.util;

import io.eeaters.delivery.config.exception.AssertException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public interface Asserts {

    static void isNull(Object obj, String message) {
        if (obj == null) {
            throw new AssertException(message);
        }
    }
    static void isNotNull(Object obj, String message) {
        if (obj != null) {
            throw new AssertException(message);
        }
    }

    static void isTrue(Boolean flag, String message) {
        if (flag) {
            throw new AssertException(message);
        }
    }

    static <T> void isEmpty(Collection<T> c,String message) {
        if (CollectionUtils.isEmpty(c)) {
            throw new AssertException(message);
        }
    }

}

