package com.sparta.springupgradeschedule.dto.auth;

import lombok.Getter;

@Getter
public class SigninRequestDTO {
    // 회원가입인 Signup에서는 username, password, email 이 필요했지만 로그인에는 email과 password
    private String email;
    private String password;
}
