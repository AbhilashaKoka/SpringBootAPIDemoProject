package com.example.apidemo.model;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Book {
    private int id;

    private String isbn;

    private String title;

    private String subTitle;

    private String author;

    private Instant publishDate;

    private String publisher;

    private Integer pages;

    private String description;

    private String website;
}
