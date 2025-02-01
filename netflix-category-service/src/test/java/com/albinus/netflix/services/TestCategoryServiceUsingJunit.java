package com.albinus.netflix.services;

import com.albinus.netflix.lib.dto.category.request.CategoryRequest;
import com.albinus.netflix.lib.dto.category.response.CategoryResponse;
import com.albinus.netflix.lib.mapper.CategoryMapper;
import com.albinus.netflix.lib.models.Category;
import com.albinus.netflix.services.repository.CategoryRepository;
import com.albinus.netflix.services.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TestCategoryServiceUsingJunit {

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private CategoryMapper categoryMapper;

	@InjectMocks
	private CategoryService categoryService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Should return all categories")
	void shouldReturnAllCategories() {
		// Arrange
		Category category = new Category();
		category.setId(1L);
		category.setName("Category 1");

		when(categoryRepository.findAll()).thenReturn(List.of(category));
		when(categoryMapper.toResponse(any(Category.class)))
				.thenReturn(new CategoryResponse(1L, "Category 1"));

		// Act
		List<CategoryResponse> categories = categoryService.findAll();

		// Assert
		assertNotNull(categories);
		assertEquals(1, categories.size());
		assertEquals("Category 1", categories.get(0).getName());
		verify(categoryRepository, times(1)).findAll();
		verify(categoryMapper, times(1)).toResponse(any(Category.class));
	}

	@Test
	@DisplayName("Should return a category by ID")
	void shouldReturnCategoryById() {
		// Arrange
		Category category = new Category();
		category.setId(1L);
		category.setName("Category 1");

		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		when(categoryMapper.toResponse(category)).thenReturn(new CategoryResponse(1L, "Category 1"));

		// Act
		Optional<CategoryResponse> response = categoryService.findById(1L);

		// Assert
		assertTrue(response.isPresent());
		assertEquals(1L, response.get().getId());
		assertEquals("Category 1", response.get().getName());
		verify(categoryRepository, times(1)).findById(1L);
		verify(categoryMapper, times(1)).toResponse(category);
	}

	@Test
	@DisplayName("Should save a category")
	void shouldSaveCategory() {
		// Arrange
		CategoryRequest request = new CategoryRequest("New Category");
		Category category = new Category();
		category.setId(1L);
		category.setName("New Category");

		when(categoryMapper.toEntity(request)).thenReturn(category);
		when(categoryRepository.save(category)).thenReturn(category);
		when(categoryMapper.toResponse(category)).thenReturn(new CategoryResponse(1L, "New Category"));

		// Act
		CategoryResponse response = categoryService.save(request);

		// Assert
		assertNotNull(response);
		assertEquals(1L, response.getId());
		assertEquals("New Category", response.getName());
		verify(categoryRepository, times(1)).save(category);
		verify(categoryMapper, times(1)).toEntity(request);
		verify(categoryMapper, times(1)).toResponse(category);
	}

	@Test
	@DisplayName("Should update a category")
	void shouldUpdateCategory() {
		// Arrange
		CategoryRequest request = new CategoryRequest("Updated Category");
		Category existingCategory = new Category();
		existingCategory.setId(1L);
		existingCategory.setName("Old Category");

		when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));
		doNothing().when(categoryMapper).updateFromDto(request, existingCategory);
		when(categoryRepository.save(existingCategory)).thenReturn(existingCategory);
		when(categoryMapper.toResponse(existingCategory)).thenReturn(new CategoryResponse(1L, "Updated Category"));

		// Act
		CategoryResponse response = categoryService.update(1L, request);

		// Assert
		assertNotNull(response);
		assertEquals(1L, response.getId());
		assertEquals("Updated Category", response.getName());
		verify(categoryRepository, times(1)).findById(1L);
		verify(categoryRepository, times(1)).save(existingCategory);
		verify(categoryMapper, times(1)).updateFromDto(request, existingCategory);
		verify(categoryMapper, times(1)).toResponse(existingCategory);
	}

	@Test
	@DisplayName("Should delete a category by ID")
	void shouldDeleteCategoryById() {
		// Act
		categoryService.delete(1L);

		// Assert
		verify(categoryRepository, times(1)).deleteById(1L);
	}
}
