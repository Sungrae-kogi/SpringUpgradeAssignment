package com.sparta.springupgradeschedule.controller;

import com.sparta.springupgradeschedule.dto.user.UserRequestDTO;
import com.sparta.springupgradeschedule.dto.user.UserResponseDTO;
import com.sparta.springupgradeschedule.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저 저장
    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }
    // 유저 단건 조회
    @GetMapping("/{userId}")
    public UserResponseDTO getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    // 유저 전체 조회
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
