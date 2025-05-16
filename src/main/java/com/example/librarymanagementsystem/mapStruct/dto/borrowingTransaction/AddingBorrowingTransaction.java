package com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddingBorrowingTransaction {
    @NotNull(message = "Member id is must be provided")
    private Long memberId;
    @NotNull(message = "Member id is must be provided")
    private Long bookId;


}
