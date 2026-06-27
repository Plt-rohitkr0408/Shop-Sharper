package com.shopsharper.auth_service.service.impl;

import com.shopsharper.auth_service.custom.DuplicateResourceException;
import com.shopsharper.auth_service.custom.ResourceNotFoundException;
import com.shopsharper.auth_service.dto.request.LoginRequest;
import com.shopsharper.auth_service.dto.response.LoginResponse;
import com.shopsharper.auth_service.dto.request.RegisterRequest;
import com.shopsharper.auth_service.dto.response.RegisterResponse;
import com.shopsharper.auth_service.entity.Role;
import com.shopsharper.auth_service.entity.User;
import com.shopsharper.auth_service.enums.RoleName;
import com.shopsharper.auth_service.repository.RoleRepository;
import com.shopsharper.auth_service.repository.UserRepository;
import com.shopsharper.auth_service.security.JwtService;
import com.shopsharper.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        Role role = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));

        User user = User.builder().
                firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .contact(registerRequest.getContact())
                .build();

        user.getRoles().add(role);


        User saveUser =userRepository.save(user);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(saveUser.getId());
        registerResponse.setMessage("User registered successfully");
        return registerResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        System.out.println(loginRequest.getEmail()+".............................");
       Authentication auth = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       loginRequest.getEmail(),
                       loginRequest.getPassword())
       );

       if(auth == null) {
           throw new BadCredentialsException("Bad Credentials");
       }

       User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new BadCredentialsException("Bad Credentials"));

       auth.getPrincipal();
       String accessToken = jwtService.generateToken(user.getEmail());

       return new LoginResponse(accessToken);
    }
}
