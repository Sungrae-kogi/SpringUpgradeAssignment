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
    private String title;

    @Column(nullable = false)
    private String contents;

    // @~~Many로 끝나는것은 기본값이 지연로딩, @~~One으로 끝나는것은 기본값이 즉시로딩
    // 단건조회시 유저정보 즉시로딩, 전체조회시 지연로딩

    //외래키 comment_id가 comment엔티티에 있으므로 연관관계의 주인은 Comment, mappedBy는 Comment에 있는 schedule
    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    //User 고유 식별자 필드를 가짐    하나의 User가 중간다리를 참조하여 여러 Schedule을 가질 수 있음.
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserSchedule> userSchedules = new ArrayList<>();

    public Schedule(ScheduleRequestDTO scheduleRequestDTO) {
        this.title = scheduleRequestDTO.getTitle();
        this.contents = scheduleRequestDTO.getContents();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        //Comment를 추가해주면서 comment 자체에 외래키도 설정해줌.
        comment.setSchedule(this);
    }

    // 일정과 관련된 사용자가 입력될 경우, 중간다리 테이블객체로 저장.
    public void addUser(User user) {
        UserSchedule userSchedule = new UserSchedule(this, user);
        this.userSchedules.add(userSchedule);
        // User측에도 userSchedule을 저장해줌, N:M 관계이므로
        user.getUserSchedules().add(userSchedule);
    }
}
