package com.example.spring_security_jwt.payload.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {
    String userName;
    String password;
    String email;
    String phone;
    String address;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date created = new Date();
    boolean userStatus;
    Set<String> listRoles;

}
