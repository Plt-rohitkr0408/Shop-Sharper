package com.shopsharper.auth_service.custom;


import com.shopsharper.auth_service.entity.Role;
import com.shopsharper.auth_service.enums.RoleName;
import com.shopsharper.auth_service.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {


        return args -> {

            if (roleRepository.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
                Role role = new Role();
                role.setName(RoleName.ROLE_ADMIN);
                roleRepository.save(role);
            }

            if (roleRepository.findByName(RoleName.ROLE_CUSTOMER).isEmpty()) {
                Role role = new Role();
                role.setName(RoleName.ROLE_CUSTOMER);
                roleRepository.save(role);
            }

        };
    }
}