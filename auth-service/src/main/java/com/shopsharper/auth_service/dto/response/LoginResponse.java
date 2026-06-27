package com.shopsharper.auth_service.dto.response;

public class LoginResponse {
    private String accessToken;
    private String tokenType ="Bearer";

    public LoginResponse(){}
    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public String getTokenType() {
        return tokenType;
    }
}
