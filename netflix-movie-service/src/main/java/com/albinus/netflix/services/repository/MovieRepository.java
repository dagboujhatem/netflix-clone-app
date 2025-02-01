package com.albinus.netflix.services.repository;

import com.albinus.netflix.lib.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}