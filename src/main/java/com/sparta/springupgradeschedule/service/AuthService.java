package com.sparta.springupgradeschedule.service;

import com.sparta.springupgradeschedule.config.JwtUtil;
import com.sparta.springupgradeschedule.config.PasswordEncoder;
import com.sparta.springupgradeschedule.dto.auth.SigninRequestDTO;
import com.sparta.springupgradeschedule.dto.auth.SigninResponseDTO;
import com.sparta.springupgradeschedule.dto.auth.SignupRequestDTO;
import com.sparta.springupgradeschedule.dto.auth.SignupResponseDTO;
import com.sparta.springupgradeschedule.entity.User;
import com.sparta.springupgradeschedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        // RequestDTO -> Entity
        User user = new User(signupRequestDTO);

        // 전달받은 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signupRequestDTO.getPassword());
        user.setPassword(encodedPassword);

        // DB 저장
        User savedUser = userRepository.save(user);

        // JWT 발급 후 반환
        String bearerToken = jwtUtil.createToken(
                savedUser.getUser_id(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );

        // Entity -> ResponseDTO 반환
        return new SignupResponseDTO(bearerToken);
    }

    public SigninResponseDTO signin(SigninRequestDTO signinRequestDTO) {
        // DTO의 Email을 기준으로 유저를 검색
        User user = userRepository.findByEmail(signinRequestDTO.getEmail()).orElseThrow(
                () -> new RuntimeException("가입되지 않은 유저입니다.")
        );

        // rawPassword = DTO의 password
        if(!passwordEncoder.matches(signinRequestDTO.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다");
        }

        // 인증되었으니 JWT 토큰 발행. 이떄 JWT 토큰에는 인증된 유저의 정보가 일부 담겨있음.
        // 이것을 클라이언트에 전달하여 클라이언트가 JWT를 보관, Session은 생성 후 서버에 보관.
        String bearerToken = jwtUtil.createToken(
                user.getUser_id(),
                user.getUsername(),
                user.getEmail()
        );

        return new SigninResponseDTO(bearerToken);
    }
}
