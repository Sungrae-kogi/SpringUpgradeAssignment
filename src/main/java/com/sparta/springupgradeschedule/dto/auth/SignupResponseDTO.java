package com.sparta.springupgradeschedule.dto.auth;

import lombok.Getter;

@Getter
public class SignupResponseDTO {
    private final String bearerToken;

    public SignupResponseDTO(String bearerToken) {
        this.bearerToken = bearerToken;
    }
}
