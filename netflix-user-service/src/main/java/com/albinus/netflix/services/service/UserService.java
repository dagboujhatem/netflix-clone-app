package com.albinus.netflix.services.service;

import com.albinus.netflix.lib.dto.user.request.UserRequest;
import com.albinus.netflix.lib.dto.user.response.UserResponse;
import com.albinus.netflix.lib.mapper.UserMapper;
import com.albinus.netflix.lib.models.User;
import com.albinus.netflix.services.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public Optional<UserResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public UserResponse save(UserRequest request) {
        User entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public UserResponse update(Long id, UserRequest request) {
        User existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        
        mapper.updateFromDto(request, existingEntity);
        
        User savedEntity = repository.save(existingEntity);
        return mapper.toResponse(savedEntity);
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }
}