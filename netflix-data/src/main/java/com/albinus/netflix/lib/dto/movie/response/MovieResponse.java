package com.albinus.netflix.lib.dto.movie.response;

import com.albinus.netflix.lib.dto.category.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private String director;
    private String genre;
    private Integer releaseYear;
    private CategoryResponse category; // Include the Category details

}