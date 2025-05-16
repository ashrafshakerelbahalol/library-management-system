package com.example.librarymanagementsystem.controller;


import com.example.librarymanagementsystem.mapStruct.dto.user.AddingUserRequest;
import com.example.librarymanagementsystem.mapStruct.dto.user.UpdatingUserRequest;
import com.example.librarymanagementsystem.mapStruct.dto.user.UserDto;
import com.example.librarymanagementsystem.response.ApiResponse;
import com.example.librarymanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "10") int size) {
        List<UserDto> userDtos = userService.getAllUsers(page, size);
        return ResponseEntity.ok(new ApiResponse("User list retrieved successfully with page : " + page + "and size : " + size, userDtos));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid AddingUserRequest addRequest) {
        UserDto userDto = userService.createUser(addRequest);
        return ResponseEntity.ok(new ApiResponse("User account created successfully", userDto));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> UpdateUser(@RequestBody @Valid UpdatingUserRequest updateRequest) {
        UserDto userDto = userService.updateUser(updateRequest);
        return ResponseEntity.ok(new ApiResponse("User profile updated successfully", userDto));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("User account deleted successfully", id));
    }


}
