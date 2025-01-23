package com.albinus.netflix.services.service;

import com.albinus.netflix.lib.dto.category.request.CategoryRequest;
import com.albinus.netflix.lib.dto.category.response.CategoryResponse;
import com.albinus.netflix.lib.mapper.CategoryMapper;
import com.albinus.netflix.lib.models.Category;
import com.albinus.netflix.services.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<CategoryResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public Optional<CategoryResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public CategoryResponse save(CategoryRequest request) {
        Category entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        Category existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        
        mapper.updateFromDto(request, existingEntity);
        
        Category savedEntity = repository.save(existingEntity);
        return mapper.toResponse(savedEntity);
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }
}