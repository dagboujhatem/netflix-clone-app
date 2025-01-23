package com.albinus.netflix.lib.mapper;

import com.albinus.netflix.lib.dto.movie.request.MovieRequest;
import com.albinus.netflix.lib.dto.movie.response.MovieResponse;
import com.albinus.netflix.lib.models.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface MovieMapper {
    // Map Movie entity to MovieResponse DTO
    @Mapping(target = "category", source = "category") // Include Category in response
    MovieResponse toResponse(Movie entity);

    // Map MovieRequest DTO to Movie entity
    @Mapping(target = "id", ignore = true) // Ignore ID during creation
    @Mapping(source = "categoryId", target = "category.id")
    Movie toEntity(MovieRequest dto);

    // Update an existing Movie entity from a MovieRequest DTO
    @Mapping(target = "id", ignore = true) // Prevent ID updates
    @Mapping(source = "categoryId", target = "category.id") // Update Category relationship
    void updateFromDto(MovieRequest dto, @MappingTarget Movie entity);
}