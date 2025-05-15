package com.example.librarymanagementsystem.mapStruct.mapper;

import com.example.librarymanagementsystem.entity.Author;
import com.example.librarymanagementsystem.mapStruct.dto.author.AuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto mapToDto(Author author);

    Author mapToEntity(AuthorDto authorDto);

}
