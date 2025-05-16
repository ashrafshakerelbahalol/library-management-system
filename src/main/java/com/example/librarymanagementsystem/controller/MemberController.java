package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.mapStruct.dto.member.AddingMemberRequest;
import com.example.librarymanagementsystem.mapStruct.dto.member.MemberDto;
import com.example.librarymanagementsystem.response.ApiResponse;
import com.example.librarymanagementsystem.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    @PreAuthorize("hasAnyRole('STAFF','LIBRARIAN')")
    public ResponseEntity<ApiResponse> getAllMembers(
            @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "10") int size) {
        List<MemberDto> memberDtos = memberService.getAllMembers(page, size);
        return ResponseEntity.ok(new ApiResponse("Member list retrieved successfully with page : " + page + "and size : " + size, memberDtos));
    }

    @PostMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse> createMembers(@RequestBody @Valid AddingMemberRequest addRequest) {
        MemberDto userDto = memberService.createMember(addRequest);
        return ResponseEntity.ok(new ApiResponse("Member account created successfully", userDto));
    }

    @PutMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse> UpdateMembers(@RequestBody @Valid MemberDto updateRequest) {
        MemberDto memberDto = memberService.updateMember(updateRequest);
        return ResponseEntity.ok(new ApiResponse("Member profile updated successfully", memberDto));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse> deleteMembers(@RequestParam Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok(new ApiResponse(" Member deleted successfully", id));
    }


}
