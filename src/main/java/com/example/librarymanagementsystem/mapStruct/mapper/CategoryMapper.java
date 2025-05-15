package com.example.librarymanagementsystem.mapStruct.mapper;

import com.example.librarymanagementsystem.entity.Category;
import com.example.librarymanagementsystem.mapStruct.dto.category.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto ToDto(Category category);

    Category ToEntity(CategoryDto categoryDto);
}
