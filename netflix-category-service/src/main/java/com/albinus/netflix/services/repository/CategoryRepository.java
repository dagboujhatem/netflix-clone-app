package com.albinus.netflix.services.repository;

import com.albinus.netflix.lib.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}