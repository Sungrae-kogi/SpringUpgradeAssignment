package com.sparta.springupgradeschedule.service;

import com.sparta.springupgradeschedule.dto.schedule.ScheduleRequestDTO;
import com.sparta.springupgradeschedule.dto.schedule.ScheduleResponseDTO;
import com.sparta.springupgradeschedule.entity.Schedule;
import com.sparta.springupgradeschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDTO createSchedule(ScheduleRequestDTO scheduleRequestDTO) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(scheduleRequestDTO);

        // DB 저장     저장 전 존재 유무 확인 작업 추가 가능
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDTO
        ScheduleResponseDTO scheduleResponseDTO = new ScheduleResponseDTO(saveSchedule);

        return scheduleResponseDTO;
    }

    public ScheduleResponseDTO getSchedule(Long id) {
        // Schedule 검색      검색 이전에 id를 가진 Schedule이 존재하는지 여부 확인 작업 추가 가능    ** 단일 검색기능은 update에서도 공통으로 쓰이므로 함수로 따로 빼도될것.
        // .get()의 경우 결과값이 null일 경우 NoSuchElementException 발생
        // .orElseThrow() 값이 존재하면 반환하고, 없는 경우 지정된 예외를 반환.
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // Entity -> ResponseDTO
        return new ScheduleResponseDTO(schedule);
    }

    public ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO scheduleRequestDTO) {
        // Schedule 검색      존재 유무 확인작업 추가 필요.
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));
        // schedule 변수가 내용이 있을때 -> RequestDTO 내용 반영 -> save()
        schedule.setContents(scheduleRequestDTO.getContents());
        schedule.setTitle(scheduleRequestDTO.getTitle());
        schedule.setUsername(scheduleRequestDTO.getUsername());
        Schedule updatedSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDTO
        return new ScheduleResponseDTO(updatedSchedule);
    }

}
