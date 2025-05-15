package com.example.librarymanagementsystem.controller;


import com.example.librarymanagementsystem.mapStruct.dto.user.AddingUserRequest;
import com.example.librarymanagementsystem.mapStruct.dto.user.UpdatingUserRequest;
import com.example.librarymanagementsystem.mapStruct.dto.user.UserDto;
import com.example.librarymanagementsystem.response.ApiResponse;
import com.example.librarymanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "10") int size) {
        List<UserDto> userDtos = userService.getAllUsers(page, size);
        return ResponseEntity.ok(new ApiResponse("User list retrieved successfully with page : " + page + "and size : " + size, userDtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody AddingUserRequest addRequest) {
        UserDto userDto = userService.createUser(addRequest);
        return ResponseEntity.ok(new ApiResponse("User account created successfully", userDto));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> UpdateUser(@RequestBody UpdatingUserRequest updateRequest) {
        UserDto userDto = userService.updateUser(updateRequest);
        return ResponseEntity.ok(new ApiResponse("User profile updated successfully", userDto));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("User account deleted successfully", id));
    }


}
