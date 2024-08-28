package com.sparta.springupgradeschedule.dto.schedule;

import com.sparta.springupgradeschedule.dto.user.UserResponseDTO;
import com.sparta.springupgradeschedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SchedulePageListResponseDTO {
    private Long schedule_id;
    private String contents;
    private String title;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    // 일정에 배치된 유저 리스트
    private List<UserResponseDTO> assignedUsers;

    public SchedulePageListResponseDTO(Schedule schedule) {
        this.schedule_id = schedule.getSchedule_id();
        this.title = schedule.getTitle();
        this.commentCount = schedule.getComments().size();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.assignedUsers = schedule.getUserSchedules().stream().map(userSchedule -> new UserResponseDTO(userSchedule.getUser())).collect(Collectors.toList());
    }
}
