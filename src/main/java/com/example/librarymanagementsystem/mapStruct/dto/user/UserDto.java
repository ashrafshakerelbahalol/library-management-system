package com.example.librarymanagementsystem.mapStruct.dto.user;

import com.example.librarymanagementsystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private Role role;
    private String address;
    private Boolean isEnabled;

}
