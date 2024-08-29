package com.sparta.springupgradeschedule.dto.user;

import com.sparta.springupgradeschedule.entity.User;
import lombok.Getter;

//6단계 User id, username, email 정보
@Getter
public class UserSmallResponseDTO {
    private Long user_id;
    private String username;
    private String email;

    public UserSmallResponseDTO(User user) {
        this.user_id = user.getUser_id();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
