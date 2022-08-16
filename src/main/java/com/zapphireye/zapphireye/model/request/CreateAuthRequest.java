package com.zapphireye.zapphireye.model.request;

import lombok.Data;

@Data
public class CreateAuthRequest {
    private String username;
    private String password;
    private String role;

    public CreateAuthRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
