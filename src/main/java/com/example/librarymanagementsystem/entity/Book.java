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
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="books")
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;
    @Column(length = 100,nullable = false)
    private String title;
    @Column(unique=true,nullable = true,length = 17)
    private String isbn;
    private int year;
    @Column(nullable = false)
    private int quantity;
    private String edition;
    @Column(name="cover_image_url",length =2048)
    private  String coverImageUrl;
    @Column(length = 10,nullable = false)
    private String language;
    @Column(length = 500)
    private String summary;
    @ManyToMany
    @JoinTable(name = "book_authors",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name="author_id")})
    private Set <Author> authors;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    @ManyToMany
    @JoinTable(name = "book_categories",
    joinColumns = {@JoinColumn(name="book_id")},
    inverseJoinColumns = {@JoinColumn(name="category_id")})
    private Set <Category> categories;
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
}
