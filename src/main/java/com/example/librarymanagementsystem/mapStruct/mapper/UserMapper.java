package com.example.librarymanagementsystem.mapStruct.mapper;

import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.mapStruct.dto.user.AddingUserRequest;
import com.example.librarymanagementsystem.mapStruct.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDTO(User user);
    User toEntity(AddingUserRequest userRequest);
}
