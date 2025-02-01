package com.albinus.netflix.services.controller;

import com.albinus.netflix.lib.dto.movie.request.MovieRequest;
import com.albinus.netflix.lib.dto.movie.response.MovieResponse;
import com.albinus.netflix.services.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService service;

    @GetMapping
    public List<MovieResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MovieResponse> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public MovieResponse create(@RequestBody MovieRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public MovieResponse update(
        @PathVariable("id") Long id,
        @RequestBody MovieRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}