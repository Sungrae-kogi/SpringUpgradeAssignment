package com.sparta.springupgradeschedule.dto.user.request;

import lombok.Getter;

@Getter
public class UserSaveRequestDTO {
    private String username;
    private String email;
    private String password;
}
