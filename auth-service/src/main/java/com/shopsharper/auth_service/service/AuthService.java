package com.shopsharper.auth_service.service;

import com.shopsharper.auth_service.dto.request.LoginRequest;
import com.shopsharper.auth_service.dto.response.LoginResponse;
import com.shopsharper.auth_service.dto.request.RegisterRequest;
import com.shopsharper.auth_service.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

}
