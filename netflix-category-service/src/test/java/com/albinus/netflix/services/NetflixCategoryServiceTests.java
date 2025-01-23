package com.albinus.netflix.services;

import com.albinus.netflix.lib.dto.category.request.CategoryRequest;
import com.albinus.netflix.lib.dto.category.response.CategoryResponse;
import com.albinus.netflix.lib.mapper.CategoryMapper;
import com.albinus.netflix.lib.models.Category;
import com.albinus.netflix.services.repository.CategoryRepository;
import com.albinus.netflix.services.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test") // Indicate that this test uses the 'test' profile
@Transactional // Ensure a clean database state after each test
class NetflixCategoryServiceTests {

	@Autowired
	private CategoryService categoryService;

	@MockBean
	private CategoryRepository categoryRepository;

	@MockBean
	private CategoryMapper categoryMapper;

	@Test
	@DisplayName("Retrieve all movie categories") // Descriptive test name
	void testFindAll() {
		// Prepare mock data
		Category category = new Category();
		category.setId(1L);
		category.setName("Drama");

		CategoryResponse response = new CategoryResponse();
		response.setId(1L);
		response.setName("Drama");

		when(categoryRepository.findAll()).thenReturn(List.of(category));
		when(categoryMapper.toResponse(category)).thenReturn(response);

		// Execute the method and verify the result
		List<CategoryResponse> result = categoryService.findAll();

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getName()).isEqualTo("Drama");
		verify(categoryRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("Find a category by existing ID")
	void testFindById() {
		// Prepare mock data
		Long id = 1L;
		Category category = new Category();
		category.setId(id);
		category.setName("Action");

		CategoryResponse response = new CategoryResponse();
		response.setId(id);
		response.setName("Action");

		when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
		when(categoryMapper.toResponse(category)).thenReturn(response);

		// Execute the method and verify the result
		Optional<CategoryResponse> result = categoryService.findById(id);

		assertThat(result).isPresent();
		assertThat(result.get().getName()).isEqualTo("Action");
		verify(categoryRepository, times(1)).findById(id);
	}

	@Test
	@DisplayName("Save a new category")
	void testSave() {
		// Prepare mock data
		CategoryRequest request = new CategoryRequest();
		request.setName("Comedy");

		Category category = new Category();
		category.setId(1L);
		category.setName("Comedy");

		CategoryResponse response = new CategoryResponse();
		response.setId(1L);
		response.setName("Comedy");

		when(categoryMapper.toEntity(request)).thenReturn(category);
		when(categoryRepository.save(category)).thenReturn(category);
		when(categoryMapper.toResponse(category)).thenReturn(response);

		// Execute the method and verify the result
		CategoryResponse result = categoryService.save(request);

		assertThat(result.getName()).isEqualTo("Comedy");
		verify(categoryRepository, times(1)).save(category);
	}

	@Test
	@DisplayName("Update an existing category")
	void testUpdate() {
		// Prepare mock data
		Long id = 1L;
		CategoryRequest request = new CategoryRequest();
		request.setName("Thriller");

		Category existingCategory = new Category();
		existingCategory.setId(id);
		existingCategory.setName("Old Name");

		Category updatedCategory = new Category();
		updatedCategory.setId(id);
		updatedCategory.setName("Thriller");

		CategoryResponse response = new CategoryResponse();
		response.setId(id);
		response.setName("Thriller");

		when(categoryRepository.findById(id)).thenReturn(Optional.of(existingCategory));
		doAnswer(invocation -> {
			CategoryRequest req = invocation.getArgument(0);
			Category cat = invocation.getArgument(1);
			cat.setName(req.getName());
			return null;
		}).when(categoryMapper).updateFromDto(any(CategoryRequest.class), any(Category.class));
		when(categoryRepository.save(existingCategory)).thenReturn(updatedCategory);
		when(categoryMapper.toResponse(updatedCategory)).thenReturn(response);

		// Execute the method and verify the result
		CategoryResponse result = categoryService.update(id, request);

		assertThat(result.getName()).isEqualTo("Thriller");
		verify(categoryRepository, times(1)).findById(id);
		verify(categoryRepository, times(1)).save(existingCategory);
	}

	@Test
	@DisplayName("Delete a category by ID")
	void testDelete() {
		// Prepare mock data
		Long id = 1L;

		// Execute the method and verify correct invocation
		categoryService.delete(id);

		verify(categoryRepository, times(1)).deleteById(id);
	}
}
