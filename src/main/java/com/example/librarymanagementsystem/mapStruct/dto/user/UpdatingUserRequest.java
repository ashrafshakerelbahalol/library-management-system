package com.example.librarymanagementsystem.mapStruct.dto.user;

import com.example.librarymanagementsystem.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdatingUserRequest {

    private Integer id;
    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{11,15}$",
            message = "Phone number must contain 11-15 numeric digits (e.g., 12345678901)")
    private String phone;

    @NotNull(message = "User role must be specified")
    private Role role;

    @Size(max = 100, message = "Address cannot exceed 100 characters")
    private String address;

    @NotNull(message = "Account status (enabled/disabled) must be specified")
    private Boolean isEnabled;
}
