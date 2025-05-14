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
import java.util.List;

@Table(name = "Members")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;
    @Column(length = 100, nullable = false)
    private String fullName;
    @Column(unique = true,nullable=false,length = 100)
    private String email;
    @Column(length = 100)
    private String address;
    @Column(length = 15)
    private String phone;
    @CreatedBy
    @Column(name="created_by",updatable = false,length = 50)
    private String createdBy;
    @CreatedDate
    @Column(name="creation_date",updatable = false)
    private LocalDateTime creationDate;
    @LastModifiedBy
    @Column(name="modified_by",length = 50)
    private String modifiedBy;
    @LastModifiedDate
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Column(name = "max_borrow_limit")
    private int maxBorrowLimit;
    @OneToMany(mappedBy = "member")
    private List<BorrowingTransaction> borrowingTransaction;



}
