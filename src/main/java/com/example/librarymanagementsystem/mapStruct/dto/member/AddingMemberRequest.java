package com.example.librarymanagementsystem.mapStruct.dto.member;

import lombok.Data;

@Data
public class AddingMemberRequest {
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private int maxBorrowLimit;
}
