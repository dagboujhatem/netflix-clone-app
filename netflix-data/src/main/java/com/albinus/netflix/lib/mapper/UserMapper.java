package com.albinus.netflix.lib.mapper;

import com.albinus.netflix.lib.dto.user.request.UserRequest;
import com.albinus.netflix.lib.dto.user.response.UserResponse;
import com.albinus.netflix.lib.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User entity);

    // Map UserRequest DTO to Movie entity
    @Mapping(target = "id", ignore = true) // Ignore ID during creation
    User toEntity(UserRequest dto);

    // Update an existing User entity from a UserRequest DTO
    @Mapping(target = "id", ignore = true) // Prevent ID updates
    void updateFromDto(UserRequest dto, @MappingTarget User entity);
}