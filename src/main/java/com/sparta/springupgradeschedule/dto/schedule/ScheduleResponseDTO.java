package com.sparta.springupgradeschedule.dto.schedule;

import com.sparta.springupgradeschedule.dto.user.UserResponseDTO;
import com.sparta.springupgradeschedule.entity.Schedule;
import com.sparta.springupgradeschedule.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScheduleResponseDTO {
    private Long schedule_id;
    private String contents;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    // 일정에 배치된 유저들
    private List<UserResponseDTO> assignedUsers;

    public ScheduleResponseDTO(Schedule schedule) {
        this.schedule_id = schedule.getSchedule_id();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        // 일정에 배치된 유저 리스트   UserSchedule -> UserResponseDTO로
        this.assignedUsers = schedule.getUserSchedules().stream().map(userSchedule -> new UserResponseDTO(userSchedule.getUser())).collect(Collectors.toList());
    }
}
