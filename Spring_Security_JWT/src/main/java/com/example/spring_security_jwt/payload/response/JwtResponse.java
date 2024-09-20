package com.example.spring_security_jwt.payload.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {
    String token;
    String type = "Bearer";
    int userId;
    String userName;
    String email;
    List<String> listRoles;

    public JwtResponse(String token, String userName, String email, List<String> listRoles) {
        this.token = token;
        this.userName = userName;
        this.email = email;
        this.listRoles = listRoles;
    }

}
