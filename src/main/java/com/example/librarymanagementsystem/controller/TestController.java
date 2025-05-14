package com.example.librarymanagementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TestController {
    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World");
    }
}
