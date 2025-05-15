package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.mapStruct.dto.member.AddingMemberRequest;
import com.example.librarymanagementsystem.mapStruct.dto.member.MemberDto;
import com.example.librarymanagementsystem.response.ApiResponse;
import com.example.librarymanagementsystem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/members")
@RequiredArgsConstructor
@Validated
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMembers(
            @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "10") int size) {
        List<MemberDto> memberDtos = memberService.getAllMembers(page, size);
        return ResponseEntity.ok(new ApiResponse("Member list retrieved successfully with page : " + page + "and size : " + size, memberDtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createMembers(@RequestBody AddingMemberRequest addRequest) {
        MemberDto userDto = memberService.createMember(addRequest);
        return ResponseEntity.ok(new ApiResponse("Member account created successfully", userDto));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> UpdateMembers(@RequestBody MemberDto updateRequest) {
        MemberDto memberDto = memberService.updateMember(updateRequest);
        return ResponseEntity.ok(new ApiResponse("Member profile updated successfully", memberDto));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteMembers(@RequestParam Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok(new ApiResponse(" Member deleted successfully", id));
    }


}
