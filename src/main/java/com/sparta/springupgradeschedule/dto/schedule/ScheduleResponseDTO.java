package com.sparta.springupgradeschedule.dto.schedule;

import com.sparta.springupgradeschedule.dto.user.UserResponseDTO;
import com.sparta.springupgradeschedule.dto.user.UserSmallResponseDTO;
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
    // 일정에 배치된 유저들  - id, username, email담은 유저정보
    private List<UserSmallResponseDTO> assignedUsers;
    private List<UserResponseDTO> assignedUserResponses;

    public ScheduleResponseDTO(Schedule schedule) {
        this.schedule_id = schedule.getSchedule_id();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.assignedUserResponses = schedule.getUserSchedules().stream().map(userSchedule -> new UserResponseDTO(userSchedule.getUser())).collect(Collectors.toList());
    }

    public ScheduleResponseDTO(Schedule schedule, List<UserSmallResponseDTO> users) {
        this.schedule_id = schedule.getSchedule_id();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        // 일정에 배치된 유저 리스트   UserSchedule -> UserResponseDTO로
        this.assignedUsers = users;
    }
}
