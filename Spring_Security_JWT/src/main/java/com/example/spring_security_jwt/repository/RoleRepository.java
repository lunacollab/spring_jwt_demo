package com.example.spring_security_jwt.repository;

import com.example.spring_security_jwt.entity.ERole;
import com.example.spring_security_jwt.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(ERole roleName);
}
