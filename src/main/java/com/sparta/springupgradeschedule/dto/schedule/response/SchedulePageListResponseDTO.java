package com.sparta.springupgradeschedule.dto.schedule.response;

import com.sparta.springupgradeschedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageListResponseDTO {
    private Long schedule_id;
    private String contents;
    private String title;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public SchedulePageListResponseDTO(Schedule schedule) {
        this.schedule_id = schedule.getSchedule_id();
        this.title = schedule.getTitle();
        this.commentCount = schedule.getComments().size();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
