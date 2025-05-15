package com.example.librarymanagementsystem.mapStruct.dto.member;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddingMemberRequest {
    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 50, message = "Full name must be between {min} and {max} characters")
    private String fullName;

    @NotBlank(message = "Email address is required")
    @Email(message = "Please provide a valid email address (e.g. user@example.com)")
    private String email;

    @Size(max = 100, message = "Address cannot exceed {max} characters")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{11,15}$",
            message = "Phone number must contain 11-15 numeric digits")
    private String phone;

    @NotNull(message = "Borrow limit is required")
    @Positive(message = "Borrow limit must be a positive number")
    private Integer maxBorrowLimit;
}
