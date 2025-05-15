package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="users")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer id;
    @Column(unique = true, length = 30)
    private String username;
    @Column(length = 50)
    private String password;
    @Column(name = "full_name",length = 100)
    private String fullName;
    @Column(unique = true,length = 30)
    private String email;
    @Column(length = 15)
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Role role;
    @Column(length = 100)
    private String address;
    @Column(name="is_valid",nullable = false)
    private Boolean isEnabled;


}
