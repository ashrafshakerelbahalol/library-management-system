package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Author;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Category;
import com.example.librarymanagementsystem.entity.Publisher;
import com.example.librarymanagementsystem.error.ResourceNotFoundException;
import com.example.librarymanagementsystem.mapStruct.dto.book.AddingBookRequest;
import com.example.librarymanagementsystem.mapStruct.dto.book.BookDto;
import com.example.librarymanagementsystem.mapStruct.mapper.BookMapper;
import com.example.librarymanagementsystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;
    private final BookMapper bookMapper;
    Logger logger = LoggerFactory.getLogger(BookService.class);


    public BookDto createBook(AddingBookRequest addRequest) {
        Book book = bookMapper.toEntity(addRequest);
        BookDto bookDto = bookMapper.toDto(addRequest);
        resolveRelations(book, bookDto);
        Book savedBook = bookRepository.save(book);
        logger.info("Book created successfully");
        return bookMapper.toDto(savedBook);
    }

    public BookDto updateBook(BookDto bookDto) {
        Optional<Book> book = bookRepository.findById(bookDto.getId());
        if (book.isEmpty()) {
            logger.info("Book not found");
            throw new ResourceNotFoundException("Book not found");
        }
        Book existingBook = getBook(bookDto, book);
        resolveRelations(existingBook, bookDto);
        logger.info("Book updated successfully");
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(updatedBook);
    }

    private Book getBook(BookDto bookDto, Optional<Book> book) {
        Book existingBook = book.get();
        existingBook.setCoverImageUrl(bookDto.getCoverImageUrl());
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setEdition(bookDto.getEdition());
        existingBook.setLanguage(bookDto.getLanguage());
        existingBook.setQuantity(bookDto.getQuantity());
        existingBook.setSummary(bookDto.getSummary());
        existingBook.setYear(bookDto.getYear());
        existingBook.setIsbn(bookDto.getIsbn());
        return existingBook;
    }

    private void resolveRelations(Book book, BookDto bookDto) {
        Set<Author> authors = bookDto.getAuthors().stream()
                .map(authorDto -> authorService.findById(authorDto.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        book.setAuthors(authors);

        // Handle categories
        Set<Category> categories = bookDto.getCategories().stream()
                .map(categoryDTO -> categoryService.findById(categoryDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        book.setCategories(categories);

        // Handle publisher
        if (bookDto.getPublisher() != null) {
            Publisher publisher = publisherService.findById(bookDto.getPublisher().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));
            book.setPublisher(publisher);
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDto> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Book> books = bookRepository.findAll(pageable);
        List<BookDto> bookDTOs = books.stream().map(bookMapper::toDto).toList();
        if (bookDTOs.isEmpty()) {
            logger.warn("getAllUsers returned empty list");
            throw new ResourceNotFoundException("There is no books in this page");
        } else
            logger.warn("getAllUsers returned list of books");
        return bookDTOs;

    }
}