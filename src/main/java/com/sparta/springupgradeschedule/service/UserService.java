package com.sparta.springupgradeschedule.service;

import com.sparta.springupgradeschedule.config.JwtUtil;
import com.sparta.springupgradeschedule.config.PasswordEncoder;
import com.sparta.springupgradeschedule.dto.user.response.UserResponseDTO;
import com.sparta.springupgradeschedule.dto.user.request.UserSaveRequestDTO;
import com.sparta.springupgradeschedule.dto.user.response.UserSaveResponseDTO;
import com.sparta.springupgradeschedule.entity.User;
import com.sparta.springupgradeschedule.exception.UserNotFoundByIdException;
import com.sparta.springupgradeschedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO getUser(Long userId) {
        // 유저 검색
        User user = findById(userId);
        return new UserResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }

    public void deleteUser(Long userId) {
        // 유저 검색
        User user = findById(userId);
        userRepository.delete(user);

    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException("해당 id값을 가진 유저 데이터가 존재하지 않습니다."));
    }
}
