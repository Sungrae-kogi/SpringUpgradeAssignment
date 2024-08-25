package com.sparta.springupgradeschedule.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ScheduleTest {
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback //@Rollback(value = false) 하면 DB에 반영을 함, 없으면 기본값이 true로 DB에 반영하지않고 롤백함.
    @DisplayName("스케쥴 생성")
    void createScheduleTest() {
        Schedule schedule = new Schedule();
        schedule.setUsername("sungrae2");
        schedule.setTitle("할일 제목");
        schedule.setContents("할일 내용");
        em.persist(schedule);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }

}