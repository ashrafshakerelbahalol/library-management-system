package com.example.librarymanagementsystem.service;


import com.example.librarymanagementsystem.config.security.SecurityUtils;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.error.ResourceAlreadyExistException;
import com.example.librarymanagementsystem.error.ResourceNotFoundException;
import com.example.librarymanagementsystem.mapStruct.dto.user.AddingUserRequest;
import com.example.librarymanagementsystem.mapStruct.dto.user.UpdatingUserRequest;
import com.example.librarymanagementsystem.mapStruct.dto.user.UserDto;
import com.example.librarymanagementsystem.mapStruct.mapper.UserMapper;
import com.example.librarymanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<UserDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<User> users = userRepository.findAll(pageable);
        List<UserDto> userDTOs = users.stream().map(userMapper::toDTO).toList();
        if (userDTOs.isEmpty()) {
            logger.warn("getAllUsers returned empty list of users for user: {} ", SecurityUtils.getCurrentUsername());
            throw new ResourceNotFoundException("There is no users in this page");
        } else
            logger.info("List of users returned with page : {} and size : {} for user : {} ", page, size, SecurityUtils.getCurrentUsername());
        return userDTOs;

    }

    public UserDto createUser(AddingUserRequest addRequest) {
        User user = userMapper.toEntity(addRequest);
        Optional<User> userWithSameUsername = userRepository.findByUsername(user.getUsername());
        if (userWithSameUsername.isPresent()) {
            logger.error("User with username {} already exists", user.getUsername());
            throw new ResourceAlreadyExistException("User with username " + user.getUsername() + " already exists");
        }
        Optional<User> userWithSameEmail = userRepository.findByEmail(user.getEmail());
        if (userWithSameEmail.isPresent()) {
            logger.error("User with Email {} already exists", user.getEmail());
            throw new ResourceAlreadyExistException("User with email " + user.getEmail() + " already exists");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(passwordEncoder.encode(addRequest.getPassword()));
        userRepository.save(user);
        logger.info("User created by user : {}", SecurityUtils.getCurrentUsername());
        return userMapper.toDTO(user);

    }

    public UserDto updateUser(UpdatingUserRequest updateRequest) {
        Optional<User> user = userRepository.findById(updateRequest.getId());
        if (user.isEmpty()) {
            logger.error("User with id {} does not exist for updating by user:{}", updateRequest.getId(), SecurityUtils.getCurrentUsername());
            throw new ResourceNotFoundException("User with id " + updateRequest.getId() + " does not exist");
        }
        User ExistingUser = user.get();
        ExistingUser.setUsername(updateRequest.getUsername());
        ExistingUser.setEmail(updateRequest.getEmail());
        ExistingUser.setPassword(updateRequest.getPassword());
        ExistingUser.setRole(updateRequest.getRole());
        ExistingUser.setFullName(updateRequest.getFullName());
        ExistingUser.setPhone(updateRequest.getPhone());
        ExistingUser.setAddress(updateRequest.getAddress());
        userRepository.save(ExistingUser);
        logger.info("User with id :{} updated by user : {}", ExistingUser.getId(), SecurityUtils.getCurrentUsername());
        return userMapper.toDTO(ExistingUser);
    }

    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            logger.error("User with id {} does not exist for deleting by user : {}", id, SecurityUtils.getCurrentUsername());
            throw new ResourceNotFoundException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);
        logger.info("User with id : {} deleted by user : {}", id, SecurityUtils.getCurrentUsername());

    }
}
