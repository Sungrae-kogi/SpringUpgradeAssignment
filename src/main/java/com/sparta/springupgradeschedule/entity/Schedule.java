package com.sparta.springupgradeschedule.entity;

import com.sparta.springupgradeschedule.dto.schedule.ScheduleRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "schedule")
    private List<Comment> comments = new ArrayList<>();

    public Schedule(ScheduleRequestDTO scheduleRequestDTO) {
        this.username = scheduleRequestDTO.getUsername();
        this.title = scheduleRequestDTO.getTitle();
        this.contents = scheduleRequestDTO.getContents();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
