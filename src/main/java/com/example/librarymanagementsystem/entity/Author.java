package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name="authors")
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    private Integer id;
    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String nationality;
    @Column(length = 500)
    private String bio;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

}
