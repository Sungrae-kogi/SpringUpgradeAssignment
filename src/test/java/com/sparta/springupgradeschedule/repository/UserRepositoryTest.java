package com.sparta.springupgradeschedule.repository;

import com.sparta.springupgradeschedule.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void createUser(){

        // RequestDTO -> Entity
        User user = new User();
        user.setUsername("collaborator");
        user.setEmail("whtjdfo@gmail.com");

        // DB 저장
        User savedUser = userRepository.save(user);

        System.out.println("저장 유저명: " + savedUser.getUsername());
        System.out.println("저장 유저이메일: " + savedUser.getEmail());
    }

    @Test
    @Transactional
    @Rollback
    public void getUser(){
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 유저 데이터가 존재하지 않습니다."));
        System.out.println("Username : " + user.getUsername());
        System.out.println("Email : " + user.getEmail());
    }
}