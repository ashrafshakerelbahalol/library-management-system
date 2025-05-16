package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.config.security.SecurityUtils;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.BorrowingTransaction;
import com.example.librarymanagementsystem.entity.Member;
import com.example.librarymanagementsystem.error.InvalidUserInputException;
import com.example.librarymanagementsystem.error.ResourceNotFoundException;
import com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction.AddingBorrowingTransaction;
import com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction.BorrowingTransactionDto;
import com.example.librarymanagementsystem.mapStruct.dto.borrowingTransaction.ReturnBookTransactionRequest;
import com.example.librarymanagementsystem.mapStruct.mapper.BorrowingTransactionMapper;
import com.example.librarymanagementsystem.repository.BorrowingTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowingTransactionService {
    private final BorrowingTransactionMapper borrowingTransactionMapper;
    private final BorrowingTransactionRepository borrowingTransactionRepository;
    private final BookService bookService;
    private final MemberService memberService;
    Logger logger = LoggerFactory.getLogger(BorrowingTransactionService.class);
    @Value("${borrowingTransaction.GracePeriod}")
    private Long gracePeriod;
    @Value("${borrowingTransaction.fine.factor}")
    private Long fineFactor;

    public List<BorrowingTransactionDto> getBorrowingTransactions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<BorrowingTransaction> borrowingTransactionList = borrowingTransactionRepository.findAll(pageable).getContent();
        logger.warn("borrowing transactions list is retrieved by user {} with page {} and size {}", SecurityUtils.getCurrentUsername(), page, size);
        return borrowingTransactionList.stream().map(borrowingTransactionMapper::toDto).toList();
    }

    public BorrowingTransactionDto createBorrowingTransaction(AddingBorrowingTransaction addRequest) {
        BorrowingTransaction borrowingTransaction = new BorrowingTransaction();
        Book book = bookService.findById(addRequest.getBookId());
        bookService.changeQuantity(book, false);
        Member member = memberService.findById(addRequest.getMemberId());
        borrowingTransaction.setBook(book);
        borrowingTransaction.setMember(member);
        borrowingTransaction.setCheckoutDate(LocalDateTime.now());
        borrowingTransaction.setDueDate(LocalDateTime.now().plusDays(gracePeriod));
        borrowingTransactionRepository.save(borrowingTransaction);
        logger.info("Borrowing transaction created for book: {} and member: {} by user: {}", book.getId(), member.getId(), SecurityUtils.getCurrentUsername());
        return borrowingTransactionMapper.toDto(borrowingTransaction);

    }


    public BorrowingTransactionDto returnBook(ReturnBookTransactionRequest returnBookTransactionRequest) {
        Optional<BorrowingTransaction> transaction = borrowingTransactionRepository.findById(returnBookTransactionRequest.getTransactionId());

        if (transaction.isEmpty()) {
            logger.error("Transaction not found");
            throw new ResourceNotFoundException("Transaction not found");
        } else if (!returnBookTransactionRequest.getBookId().equals(transaction.get().getBook().getId())
                || !returnBookTransactionRequest.getMemberId().equals(transaction.get().getMember().getId())) {
            logger.error("Return quest is not valid");
            throw new InvalidUserInputException("Return quest is not valid");
        }
        BorrowingTransaction borrowingTransaction = transaction.get();
        borrowingTransaction.setReturnDate(LocalDateTime.now());
        Book book = bookService.findById(returnBookTransactionRequest.getBookId());
        bookService.changeQuantity(book, true);
        if (borrowingTransaction.getDueDate().isBefore(borrowingTransaction.getReturnDate())) {
            Long daysDifference = ChronoUnit.DAYS.between(borrowingTransaction.getDueDate(),
                    borrowingTransaction.getReturnDate());
            borrowingTransaction.setFineAmount(fineFactor * daysDifference);
            logger.info("The fine amount for the borrowing transaction is " + borrowingTransaction.getFineAmount());

        } else {
            logger.info("The book is returned Successfully within the gracePeriod");

        }
        borrowingTransactionRepository.save(borrowingTransaction);
        logger.info("Book returned successfully with id : {}  from member : {} by user :{}", borrowingTransaction.getBook().getId()
                , borrowingTransaction.getMember().getId(), SecurityUtils.getCurrentUsername());
        return borrowingTransactionMapper.toDto(borrowingTransaction);


    }

    public void deleteBorrowingTransaction(Long id) {
        Optional<BorrowingTransaction> borrowingTransaction = borrowingTransactionRepository.findById(id);
        if (borrowingTransaction.isEmpty()) {
            logger.warn("Transaction not found");
            throw new ResourceNotFoundException("Transaction not found");
        }
        logger.info("Transaction with id : {} is deleted Successfully by user: {}", id, SecurityUtils.getCurrentUsername());
        borrowingTransactionRepository.delete(borrowingTransaction.get());

    }
}
