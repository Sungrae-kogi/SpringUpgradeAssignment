package com.sparta.springupgradeschedule.repository;

import com.sparta.springupgradeschedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
