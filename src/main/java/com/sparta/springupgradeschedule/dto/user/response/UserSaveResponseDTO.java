package com.sparta.springupgradeschedule.dto.user.response;

import lombok.Getter;

@Getter
public class UserSaveResponseDTO {
    private final String bearerToken;

    public UserSaveResponseDTO(String bearerToken) {
        this.bearerToken = bearerToken;
    }
}
