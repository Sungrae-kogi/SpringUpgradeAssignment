package com.sparta.springupgradeschedule.service;

import com.sparta.springupgradeschedule.dto.user.UserRequestDTO;
import com.sparta.springupgradeschedule.dto.user.UserResponseDTO;
import com.sparta.springupgradeschedule.entity.User;
import com.sparta.springupgradeschedule.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // RequestDTO -> Entity
        User user = new User(userRequestDTO);

        // DB 저장
        User savedUser = userRepository.save(user);

        // Entity -> ResponseDTO 반환
        return new UserResponseDTO(savedUser);
    }

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
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 id값을 가진 유저 데이터가 존재하지 않습니다."));
    }
}
