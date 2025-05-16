package com.example.librarymanagementsystem.mapStruct.dto.book;

import lombok.Data;

@Data
public class BookDtoForTransaction {
    private Long id;
    private String title;
    private String edition;
    private String language;
    private String summary;

}
