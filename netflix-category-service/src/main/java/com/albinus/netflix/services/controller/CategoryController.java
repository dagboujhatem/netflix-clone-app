package com.albinus.netflix.services.controller;

import com.albinus.netflix.lib.dto.category.request.CategoryRequest;
import com.albinus.netflix.lib.dto.category.response.CategoryResponse;
import com.albinus.netflix.services.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<CategoryResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CategoryResponse> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CategoryResponse create(@RequestBody CategoryRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(
        @PathVariable("id") Long id,
        @RequestBody CategoryRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}