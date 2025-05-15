package com.example.librarymanagementsystem.mapStruct.dto.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {
    private Long id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private String createdBy;
    private LocalDateTime creationDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private int maxBorrowLimit;
}
