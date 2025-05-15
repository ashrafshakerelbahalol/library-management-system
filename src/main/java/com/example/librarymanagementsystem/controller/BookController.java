package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.mapStruct.dto.book.AddingBookRequest;
import com.example.librarymanagementsystem.mapStruct.dto.book.BookDto;
import com.example.librarymanagementsystem.response.ApiResponse;
import com.example.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
@Validated
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllBooks(@RequestParam(required = false, defaultValue = "0") int page,
                                                   @RequestParam(required = false, defaultValue = "10") int size) {
        List<BookDto> bookDto = bookService.getAllBooks(page, size);
        return ResponseEntity.ok(new ApiResponse("Book list retrieved successfully with page : " + page + "and size : " + size, bookDto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createBook(@Validated @RequestBody AddingBookRequest addRequest) {
        BookDto createdBookDto = bookService.createBook(addRequest);
        return ResponseEntity.ok(new ApiResponse("Book created successfully", createdBookDto));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateBook(@Validated @RequestBody BookDto bookDto) {
        BookDto updatedBookDto = bookService.updateBook(bookDto);
        return ResponseEntity.ok(new ApiResponse("Book updated successfully", updatedBookDto));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse("Book Deleted Successfully", id));
    }

}
