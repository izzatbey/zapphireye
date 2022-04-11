package com.zapphireye.zapphireye.model.response;

public class AuthResponse {
    private String response;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
