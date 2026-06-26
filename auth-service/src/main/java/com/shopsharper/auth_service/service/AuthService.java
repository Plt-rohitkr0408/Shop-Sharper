package com.shopsharper.auth_service.service;

import com.shopsharper.auth_service.dto.RegisterRequest;
import com.shopsharper.auth_service.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
}
