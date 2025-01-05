package com.prapp.examplesecurityjwtbasic.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "test_jwt")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "username", length = 100)
    private String username;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "password", length = 255)
    private String password;
    @Column(name = "is_active")
    private Boolean isActive;

}