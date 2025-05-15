package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.Category;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
