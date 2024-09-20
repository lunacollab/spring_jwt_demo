package com.example.spring_security_jwt.service;

import com.example.spring_security_jwt.entity.ERole;
import com.example.spring_security_jwt.entity.Roles;

import java.util.Optional;

public interface RoleService {
    Optional<Roles> findByRoleName(ERole roleName);
}
