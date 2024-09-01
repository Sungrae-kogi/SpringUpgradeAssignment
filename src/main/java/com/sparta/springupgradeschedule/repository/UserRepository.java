package com.sparta.springupgradeschedule.repository;

import com.sparta.springupgradeschedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
