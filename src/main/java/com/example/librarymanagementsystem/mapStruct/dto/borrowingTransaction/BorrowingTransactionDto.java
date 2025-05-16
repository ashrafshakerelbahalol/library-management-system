package com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction;


import com.example.librarymanagementsystem.mapStruct.dto.book.BookDtoForTransaction;
import com.example.librarymanagementsystem.mapStruct.dto.member.MemberDtoForTransaction;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowingTransactionDto {

    private Long id;

    private MemberDtoForTransaction member;

    private BookDtoForTransaction book;

    private String createdBy;

    private LocalDateTime checkoutDate;

    private String modifiedBy;

    private LocalDateTime modifiedDate;

    private LocalDateTime returnDate;

    private LocalDateTime dueDate;

    private Integer FineAmount;


}
