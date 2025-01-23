package com.albinus.netflix.services.controller;

import com.albinus.netflix.lib.dto.user.request.UserRequest;
import com.albinus.netflix.lib.dto.user.response.UserResponse;
import com.albinus.netflix.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserResponse> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public UserResponse update(
        @PathVariable("id") Long id,
        @RequestBody UserRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}