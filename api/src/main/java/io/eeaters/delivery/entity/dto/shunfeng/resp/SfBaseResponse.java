package io.eeaters.delivery.entity.dto.shunfeng.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SfBaseResponse<T> {

    @JsonProperty("error_code")
    private Integer errorCode;

    @JsonProperty("error_msg")
    private String errorMsg;

    @JsonProperty("error_data")
    private Object errorData;

    @JsonProperty("result")
    private T result;

}

