package com.albinus.netflix.services.repository;

import com.albinus.netflix.lib.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}