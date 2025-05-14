package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Integer id;
    @Column(length = 30, nullable = false,unique = true)
    private String name;
    @ManyToMany
    @JoinTable(name = "category_hierarchy",
            joinColumns={@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name="category_parent_id")})
    private Set<Category> categoryParentId;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;

}
