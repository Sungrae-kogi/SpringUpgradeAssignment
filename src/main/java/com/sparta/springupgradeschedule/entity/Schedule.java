package com.sparta.springupgradeschedule.entity;

import com.sparta.springupgradeschedule.dto.ScheduleRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Schedule(ScheduleRequestDTO scheduleRequestDTO) {
        this.username = scheduleRequestDTO.getUsername();
        this.title = scheduleRequestDTO.getTitle();
        this.contents = scheduleRequestDTO.getContents();
    }
}
