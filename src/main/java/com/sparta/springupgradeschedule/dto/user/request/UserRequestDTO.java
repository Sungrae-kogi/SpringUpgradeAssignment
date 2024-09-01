package com.sparta.springupgradeschedule.dto.user.request;

import com.sparta.springupgradeschedule.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDTO {
    private String username;
    private String email;

    public UserRequestDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
