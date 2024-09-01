package com.sparta.springupgradeschedule.entity;

import com.sparta.springupgradeschedule.dto.user.request.UserRequestDTO;
import com.sparta.springupgradeschedule.dto.user.request.UserSaveRequestDTO;
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
@Table(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSchedule> userSchedules = new ArrayList<>();

    public User(UserRequestDTO userRequestDTO) {
        this.username = userRequestDTO.getUsername();
        this.email = userRequestDTO.getEmail();
    }

    public User(UserSaveRequestDTO userSaveRequestDTO) {
        this.username = userSaveRequestDTO.getUsername();
        this.email = userSaveRequestDTO.getEmail();
        this.password = userSaveRequestDTO.getPassword();
    }

    //User측에서도 Schedule 등록시 중간다리 객체로 저장.
    public void addSchedule(Schedule schedule) {
        UserSchedule userSchedule = new UserSchedule(schedule, this);
        userSchedules.add(userSchedule);
        // Schedule에도 userSchedule을 저장해줘야함, 서로 N:1 이므로.
        schedule.getUserSchedules().add(userSchedule);
    }
}
