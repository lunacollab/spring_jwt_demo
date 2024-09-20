package com.example.spring_security_jwt.service;

import com.example.spring_security_jwt.entity.Users;


public interface UserService {
    Users findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Users saveOrUpdate(Users user);
}
