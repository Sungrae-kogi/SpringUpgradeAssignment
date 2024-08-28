package com.sparta.springupgradeschedule.dto.user;

import com.sparta.springupgradeschedule.entity.User;
import com.sparta.springupgradeschedule.entity.UserSchedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserResponseDTO {
    private Long user_id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<UserSchedule> userSchedules;


    public UserResponseDTO(User user) {
        this.user_id = user.getUser_id();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
        this.userSchedules = user.getUserSchedules();
    }
}
