package com.zapphireye.zapphireye.model.request;

import lombok.Data;

@Data
public class CreateAuthRequest {
    private String username;
    private String password;
    private String role;

    public CreateAuthRequest() {
    }
}
