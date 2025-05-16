package com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction;

import lombok.Data;

@Data
public class ReturnBookTransactionRequest {
    private Long transactionId;
    private Long memberId;
    private Long bookId;


}
