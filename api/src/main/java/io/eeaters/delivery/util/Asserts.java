package io.eeaters.delivery.util;

import io.eeaters.delivery.config.exception.AssertException;

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

}

