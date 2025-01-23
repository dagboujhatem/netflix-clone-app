package com.albinus.netflix.lib.dto.movie.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    private String title;
    private String description;
    private String director;
    private String genre;
    private Integer releaseYear;
    private Long categoryId; // Represent the category relationship by ID
}