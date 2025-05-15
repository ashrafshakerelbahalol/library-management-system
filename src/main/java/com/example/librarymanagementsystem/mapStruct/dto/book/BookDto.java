package com.example.librarymanagementsystem.mapStruct.dto.book;

import com.example.librarymanagementsystem.mapStruct.dto.author.AuthorDto;
import com.example.librarymanagementsystem.mapStruct.dto.category.CategoryDto;
import com.example.librarymanagementsystem.mapStruct.dto.publisher.PublisherDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String isbn;
    private int year;
    private int quantity;
    private String edition;
    private String coverImageUrl;
    private String language;
    private String summary;
    private Set<AuthorDto> authors;
    private PublisherDto publisher;
    private Set<CategoryDto> categories;
    private String createdBy;
    private LocalDateTime creationDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
}
