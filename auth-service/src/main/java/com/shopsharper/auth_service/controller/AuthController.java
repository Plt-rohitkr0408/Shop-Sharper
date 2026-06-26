package com.shopsharper.auth_service.controller;

import com.shopsharper.auth_service.dto.RegisterRequest;
import com.shopsharper.auth_service.dto.RegisterResponse;
import com.shopsharper.auth_service.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/register")
public class AuthController {
    private final AuthServiceImpl authServiceImpl;
    @Autowired
    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @GetMapping
    public ResponseEntity<String> Reponse(){
        System.out.println("register ko check kro.......");
        return ResponseEntity.ok("Auth service is running");
    }

    @PostMapping
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        System.out.println("Register.........");
       RegisterResponse registerResponse = authServiceImpl.register(registerRequest);
        return ResponseEntity.ok(registerResponse);
    }
}
