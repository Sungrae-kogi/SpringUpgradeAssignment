package com.sparta.springupgradeschedule.repository;

import com.sparta.springupgradeschedule.dto.ScheduleResponseDTO;
import com.sparta.springupgradeschedule.entity.Schedule;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleRepositoryTest {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    @Transactional
    @Rollback
    public void createSchedule(){
        Schedule schedule = new Schedule();
        schedule.setUsername("sungrae");
        schedule.setTitle("스케쥴제목");
        schedule.setContents("스케쥴 내용");

        //Schedule 반환.
        //findBy~~~ 식으로 사용하는 경우 Optional타입을 반환한다고 하는 부분 찾아보기.
        Schedule result = scheduleRepository.save(schedule);

        System.out.println(result.getUsername());
    }

    @Test
    @Transactional
    @Rollback
    public void getSchedule(){
        // id key값으로 검색 - 현재 id = 4인 test DB가 들어가있는상태.
        // .get()의 경우 결과값이 null일 경우 NoSuchElementException 발생   https://velog.io/@aidenshin/Optional-%EA%B4%80%EB%A0%A8..
        // .orElseThrow()로 값이 null일 경우 지정한 예외를 발생시켜줄 수 있음.
        Schedule schedule = scheduleRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException("해당 id값을 가진 데이터가 존재하지 않습니다."));

        System.out.println(schedule.getId());
        System.out.println(schedule.getContents());
        System.out.println(schedule.getTitle());
        System.out.println(schedule.getUsername());
        System.out.println(schedule.getCreatedAt());
    }
}