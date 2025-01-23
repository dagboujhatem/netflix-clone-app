package com.albinus.netflix.lib.mapper;

import com.albinus.netflix.lib.dto.category.request.CategoryRequest;
import com.albinus.netflix.lib.dto.category.response.CategoryResponse;
import com.albinus.netflix.lib.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    // Map Category Entity to CategoryResponse
    CategoryResponse toResponse(Category entity);

    // Map CategoryRequest to Category Entity
    @Mapping(target = "id", ignore = true) // Ignore id as it's usually auto-generated
    Category toEntity(CategoryRequest dto);

    // Update existing Category entity from CategoryRequest DTO
    @Mapping(target = "id", ignore = true) // Prevent ID updates
    void updateFromDto(CategoryRequest dto, @MappingTarget Category entity);
}