package com.example.spring_security_jwt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserId")
    int userId;

    @Column(name="UserName",unique = true,nullable = false)
    String userName;

    @Column(name="Password",nullable = false)
    @JsonIgnore
    String password;

    @Column(name="Created")
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date created;

    @Column(name="Email", nullable = false, unique = true)
    String email;

    @Column(name="Phone")
    String phone;

    @Column(name = "Address")
    String address;

    @Column(name="UserStatus")
    boolean userStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="User_Role",joinColumns = @JoinColumn(name = "UserId"),
     inverseJoinColumns = @JoinColumn(name="RoleId"))
    Set<Roles> listRoles = new HashSet<>();


    @PrePersist
    protected void onCreate() {
        if (this.created == null) {
            this.created = new Date();
        }
        this.userStatus = true;
    }
}
