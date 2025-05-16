package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.config.security.SecurityUtils;
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
        logger.info("Book created successfully by user:{}", SecurityUtils.getCurrentUsername());
        return bookMapper.toDto(savedBook);
    }

    public void changeQuantity(Book book, Boolean isReturned) {
        book = bookRepository.findById(book.getId()).get();
        if (isReturned) {
            book.setQuantity(book.getQuantity() + 1);
        } else {
            if (book.getQuantity() == 0) {
                logger.error("Book quantity is zero");
                throw new ResourceNotFoundException("Book quantity is zero");
            }
            book.setQuantity(book.getQuantity() - 1);
        }
        bookRepository.save(book);
    }

    public BookDto updateBook(BookDto bookDto) {
        Optional<Book> book = bookRepository.findById(bookDto.getId());
        if (book.isEmpty()) {
            logger.error("Book not found");
            throw new ResourceNotFoundException("Book not found");
        }
        Book existingBook = getBook(bookDto, book);
        resolveRelations(existingBook, bookDto);
        logger.info("Book with id : {}  updated successfully by user: {}", bookDto.getId(), SecurityUtils.getCurrentUsername());
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
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            logger.error("Book not found");
            throw new ResourceNotFoundException("Book not found");
        }
        logger.info("Book deleted with id : {} successfully by {}", id, SecurityUtils.getCurrentUsername());
        bookRepository.deleteById(id);
    }

    public List<BookDto> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Book> books = bookRepository.findAll(pageable);
        List<BookDto> bookDTOs = books.stream().map(bookMapper::toDto).toList();
        if (bookDTOs.isEmpty()) {
            logger.warn("getAllBooks returned empty list");
            throw new ResourceNotFoundException("There is no books in this page");
        } else
            logger.info("list of books is returned with page {} and size {} by {} ", page, size, SecurityUtils.getCurrentUsername());
        return bookDTOs;

    }

    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }
}