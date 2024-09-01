package com.sparta.springupgradeschedule.repository;

import com.sparta.springupgradeschedule.config.JwtUtil;
import com.sparta.springupgradeschedule.config.PasswordEncoder;
import com.sparta.springupgradeschedule.entity.User;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Test
    @Transactional
    @Rollback(value = false)
    public void createUser() {

        // given    -    username, email, password
        User user = new User();
        user.setUsername("developer");
        user.setEmail("whtjdfo@gmail.com");
        user.setPassword("123456");

        // when
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        String bearerToken = jwtUtil.createToken(
                savedUser.getUser_id(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );

        // then
        Assertions.assertThat(savedUser.getUsername()).isEqualTo("developer");
        Assertions.assertThat(bearerToken).startsWith("Bearer ");
        Assertions.assertThat(passwordEncoder.matches("123456", encodedPassword)).isTrue();

    }

    @Test
    @Transactional
    @Rollback
    public void getUser() {
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 유저 데이터가 존재하지 않습니다."));
        System.out.println("Username : " + user.getUsername());
        System.out.println("Email : " + user.getEmail());
    }
}