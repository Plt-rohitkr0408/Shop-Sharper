package com.shopsharper.auth_service.service.impl;

import com.shopsharper.auth_service.dto.RegisterRequest;
import com.shopsharper.auth_service.dto.RegisterResponse;
import com.shopsharper.auth_service.entity.Role;
import com.shopsharper.auth_service.entity.User;
import com.shopsharper.auth_service.enums.RoleName;
import com.shopsharper.auth_service.repository.RoleRepository;
import com.shopsharper.auth_service.repository.UserRepository;
import com.shopsharper.auth_service.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
                .orElseGet(()->{
                    Role newRole = new Role();
                    newRole.setName(RoleName.ROLE_CUSTOMER);
                    return roleRepository.save(newRole);
                });

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
}
