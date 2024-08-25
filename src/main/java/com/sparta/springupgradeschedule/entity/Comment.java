package com.sparta.springupgradeschedule.entity;

import com.sparta.springupgradeschedule.dto.comment.CommentRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(CommentRequestDTO commentRequestDTO, Schedule schedule) {
        this.comment = commentRequestDTO.getComment();
        this.username = commentRequestDTO.getUsername();
        this.schedule = schedule;
    }
}
