package com.example.librarymanagementsystem.mapStruct.mapper;

import com.example.librarymanagementsystem.entity.BorrowingTransaction;
import com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction.BorrowingTransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class, MemberMapper.class})
public interface BorrowingTransactionMapper {
    BorrowingTransactionDto toDto(BorrowingTransaction borrowingTransaction);

    BorrowingTransaction toEntity(BorrowingTransactionDto borrowingTransactionDto);
}
