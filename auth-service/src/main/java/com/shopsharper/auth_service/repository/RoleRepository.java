package com.shopsharper.auth_service.repository;

import com.shopsharper.auth_service.entity.Role;
import com.shopsharper.auth_service.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
