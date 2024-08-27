package com.sparta.springupgradeschedule.entity;

import com.sparta.springupgradeschedule.dto.schedule.ScheduleRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "comments")     //블로그 문제상황 정리 : 양방향 연관관계에 있는 두 엔티티가 @ToString을 서로 계속 호출하면서 StackOverFlow가 발생.
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

    //외래키 comment_id가 comment엔티티에 있으므로 연관관계의 주인은 Comment, mappedBy는 Comment에 있는 schedule
    @OneToMany(mappedBy = "schedule")
    private List<Comment> comments = new ArrayList<>();

    public Schedule(ScheduleRequestDTO scheduleRequestDTO) {
        this.username = scheduleRequestDTO.getUsername();
        this.title = scheduleRequestDTO.getTitle();
        this.contents = scheduleRequestDTO.getContents();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        //Comment를 추가해주면서 comment 자체에 외래키도 설정해줌.
        comment.setSchedule(this);
    }
}
