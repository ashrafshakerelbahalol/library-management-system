package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction.AddingBorrowingTransaction;
import com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction.BorrowingTransactionDto;
import com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction.ReturnBookTransactionRequest;
import com.example.librarymanagementsystem.response.ApiResponse;
import com.example.librarymanagementsystem.service.BorrowingTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class BorrowingTransactionController {
    private final BorrowingTransactionService borrowingTransactionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('STAFF','LIBRARIAN')")
    public ResponseEntity<ApiResponse> getBorrowingTransaction(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size) {
        List<BorrowingTransactionDto> borrowingTransactionDtoList = borrowingTransactionService.getBorrowingTransactions(page, size);
        return ResponseEntity.ok(new ApiResponse("Transaction list retrieved successfully with page : " + page + " and size : " + size, borrowingTransactionDtoList));
    }

    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse> createBorrowingTransaction(@RequestBody AddingBorrowingTransaction addRequest) {
        BorrowingTransactionDto borrowingTransactionDto = borrowingTransactionService.createBorrowingTransaction(addRequest);
        return ResponseEntity.ok(new ApiResponse("Transaction added successfully", borrowingTransactionDto));
    }

    @PutMapping("return-of-book")
    @PreAuthorize("hasAnyRole('STAFF','LIBRARIAN')")
    public ResponseEntity<ApiResponse> returnBook(@RequestBody ReturnBookTransactionRequest returnBookTransactionRequest) {
        BorrowingTransactionDto borrowingTransactionDto = borrowingTransactionService.returnBook(returnBookTransactionRequest);
        return ResponseEntity.ok(new ApiResponse("Book returned successfully", borrowingTransactionDto));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse> deleteBorrowingTransaction(@RequestParam Long id) {
        borrowingTransactionService.deleteBorrowingTransaction(id);
        return ResponseEntity.ok(new ApiResponse("borrowing transaction deleted Successfully", id));
    }

}
