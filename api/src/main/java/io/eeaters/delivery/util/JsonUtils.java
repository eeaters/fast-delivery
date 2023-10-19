package io.eeaters.delivery.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public interface JsonUtils {

    ObjectMapper objectMapper = new ObjectMapper(){
        {
            registerModule(new JavaTimeModule());
        }
    };

    static String writeValueAsString(Object obj) {
        return NonExSupplier.exec(() -> objectMapper.writeValueAsString(obj));
    }

    static <T> T readValue(String text, Class<T> tClass) {
        return NonExSupplier.exec(() -> objectMapper.readValue(text, tClass));
    }
}
