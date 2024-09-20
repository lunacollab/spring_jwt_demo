package com.example.spring_security_jwt.serviceImpl;

import com.example.spring_security_jwt.entity.ERole;
import com.example.spring_security_jwt.entity.Roles;
import com.example.spring_security_jwt.repository.RoleRepository;
import com.example.spring_security_jwt.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    @Override
    public Optional<Roles> findByRoleName(ERole roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
