package io.eeaters.delivery.mock.access;

import lombok.Data;

@Data
public class LoginResp {
    private String nickName;
    private String token;
}
