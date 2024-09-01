package com.sparta.springupgradeschedule.dto.auth;

import lombok.Getter;

@Getter
public class SignupRequestDTO {
    private String username;
    private String email;
    private String password;
}
