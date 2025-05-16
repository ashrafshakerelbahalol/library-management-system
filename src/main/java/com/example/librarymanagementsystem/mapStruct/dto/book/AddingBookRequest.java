package com.example.librarymanagementsystem.mapStruct.dto.book;

import com.example.librarymanagementsystem.mapStruct.dto.author.AuthorDto;
import com.example.librarymanagementsystem.mapStruct.dto.category.CategoryDto;
import com.example.librarymanagementsystem.mapStruct.dto.publisher.PublisherDto;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class AddingBookRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between {min} and {max} characters")
    private String title;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^[0-9-]{10,17}$",
            message = "Invalid ISBN format (e.g., 978-3-16-148410-0)")
    private String isbn;

    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Year must be after 999")
    @Max(value = 2100, message = "Year cannot be after 2100")
    private Integer year;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;

    @NotBlank(message = "Edition information is required")
    @Pattern(regexp = "^[1-9]\\d*(st|nd|rd|th)?$",
            message = "Invalid edition format (e.g., '1st', '2nd', '3')")
    private String edition;

    @NotBlank(message = "Cover image URL is required")
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "Invalid URL format")
    private String coverImageUrl;

    @NotBlank(message = "Language is required")
    @Size(min = 2, max = 50, message = "Language must be between {min} and {max} characters")
    private String language;

    @NotBlank(message = "Summary is required")
    @Size(min = 50, max = 1000, message = "Summary must be between {min} and {max} characters")
    private String summary;

    @NotEmpty(message = "At least one author must be specified")
    private Set<AuthorDto> authors;

    @NotNull(message = "Publisher information is required")
    private PublisherDto publisher;

    @NotEmpty(message = "At least one category must be specified")
    private Set<CategoryDto> categories;
}
