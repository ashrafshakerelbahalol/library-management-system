package com.example.librarymanagementsystem.mapStruct.mapper;

import com.example.librarymanagementsystem.entity.Member;
import com.example.librarymanagementsystem.mapStruct.dto.member.AddingMemberRequest;
import com.example.librarymanagementsystem.mapStruct.dto.member.MemberDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberDto toDTO(Member user);

    Member toEntity(AddingMemberRequest memberRequest);
}
