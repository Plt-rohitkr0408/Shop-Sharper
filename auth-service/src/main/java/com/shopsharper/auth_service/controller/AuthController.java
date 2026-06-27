package com.shopsharper.auth_service.controller;

import com.shopsharper.auth_service.dto.request.LoginRequest;
import com.shopsharper.auth_service.dto.response.LoginResponse;
import com.shopsharper.auth_service.dto.request.RegisterRequest;
import com.shopsharper.auth_service.dto.response.RegisterResponse;
import com.shopsharper.auth_service.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServiceImpl authServiceImpl;
    @Autowired
    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
       RegisterResponse registerResponse = authServiceImpl.register(registerRequest);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authServiceImpl.login(loginRequest));
    }
}
