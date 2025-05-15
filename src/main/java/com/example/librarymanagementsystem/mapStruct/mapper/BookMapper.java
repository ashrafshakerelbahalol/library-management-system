package com.example.librarymanagementsystem.mapStruct.mapper;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.mapStruct.dto.book.AddingBookRequest;
import com.example.librarymanagementsystem.mapStruct.dto.book.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class, AuthorMapper.class, CategoryMapper.class, PublisherMapper.class})
public interface BookMapper {
    Book toEntity(BookDto bookDto);

    BookDto toDto(Book book);

    Book toEntity(AddingBookRequest addRequest);

    BookDto toDto(AddingBookRequest addRequest);
}
