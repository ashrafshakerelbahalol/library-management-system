package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publisher_id")
    private Integer id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 100)
    private String address;
    @Column(name="contact_email",unique = true,length = 100)
    private String email;
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

}
