package com.shopsharper.auth_service.entity;

import com.shopsharper.auth_service.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="roles")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName  name;

}
