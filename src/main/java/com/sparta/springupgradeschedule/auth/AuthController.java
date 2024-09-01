package com.sparta.springupgradeschedule.auth;

import com.sparta.springupgradeschedule.dto.auth.SigninRequestDTO;
import com.sparta.springupgradeschedule.dto.auth.SigninResponseDTO;
import com.sparta.springupgradeschedule.dto.auth.SignupRequestDTO;
import com.sparta.springupgradeschedule.dto.auth.SignupResponseDTO;
import com.sparta.springupgradeschedule.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/signup")
    public SignupResponseDTO signup(@RequestBody SignupRequestDTO signupRequestDTO){
        return authService.signup(signupRequestDTO);
    }

    @PostMapping("/auth/signin")
    public SigninResponseDTO signin(@RequestBody SigninRequestDTO signinRequestDTO){
        return authService.signin(signinRequestDTO);
    }
}