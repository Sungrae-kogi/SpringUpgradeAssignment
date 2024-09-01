package com.sparta.springupgradeschedule.dto.auth;

import lombok.Getter;

@Getter
public class SigninResponseDTO {
    private final String bearerToken;

    public SigninResponseDTO(String bearerToken) {
        this.bearerToken = bearerToken;
    }
}
