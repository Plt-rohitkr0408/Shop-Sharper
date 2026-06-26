package com.shopsharper.auth_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name="users" ,
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
    }
)
@Getter  @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String contact;
    private boolean enables = true;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    public void createUser(){
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PostPersist
    public void updateUser(){
        updateAt = LocalDateTime.now();
    }


}
