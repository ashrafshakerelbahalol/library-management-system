package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="borrowing_transactions")
@EntityListeners(AuditingEntityListener.class)
public class BorrowingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;
    @CreatedBy
    @Column(name="created_by",length = 30,updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(name="checkout_date",updatable = false)
    private LocalDateTime checkoutDate;
    @LastModifiedBy
    @Column(name="modified_by",length = 30)
    private String modifiedBy;
    @LastModifiedDate
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Column(name="return_date")
    private LocalDateTime returnDate;
    @Column(name="due_date")
    private LocalDateTime dueDate;
    @Column(name="fine_amount")
    private Integer FineAmount;


}
