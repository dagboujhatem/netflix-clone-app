package com.albinus.netflix.services.api.service;

import com.albinus.netflix.lib.dto.movie.request.MovieRequest;
import com.albinus.netflix.lib.dto.movie.response.MovieResponse;
import com.albinus.netflix.lib.mapper.MovieMapper;
import com.albinus.netflix.lib.models.Movie;
import com.albinus.netflix.services.api.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final MovieMapper mapper;

    public List<MovieResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public Optional<MovieResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public MovieResponse save(MovieRequest request) {
        Movie entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public MovieResponse update(Long id, MovieRequest request) {
        Movie existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + id));
        
        mapper.updateFromDto(request, existingEntity);
        
        Movie savedEntity = repository.save(existingEntity);
        return mapper.toResponse(savedEntity);
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }
}