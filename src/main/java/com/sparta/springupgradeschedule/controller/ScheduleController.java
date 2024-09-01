package com.sparta.springupgradeschedule.controller;

import com.sparta.springupgradeschedule.dto.schedule.response.SchedulePageListResponseDTO;
import com.sparta.springupgradeschedule.dto.schedule.request.ScheduleRequestDTO;
import com.sparta.springupgradeschedule.dto.schedule.response.ScheduleResponseDTO;
import com.sparta.springupgradeschedule.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 스케쥴 저장   - 스케쥴이 유저 고유식별자를 가지므로 생성시 정보가 필요함.
    @PostMapping("/{userId}")
    public ScheduleResponseDTO createSchedule(@PathVariable Long userId, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        //요청 body Message -> RequestDTO 변환 및 scheduleService 전달.
        return scheduleService.createSchedule(userId, scheduleRequestDTO);
    }

    // 스케쥴 단건 조회
    @GetMapping("/{scheduleId}")
    public ScheduleResponseDTO getSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    // 스케쥴 전체 조회 - Pageable 적용 디폴트 페이지 크기 = 10
    @GetMapping
    public List<SchedulePageListResponseDTO> getAllSchedules(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        //Pageable 인터페이스는 직접 인스턴스화가 불가능 따라서, PageRequest라는 구현 클래스를 사용하여 인스턴스화.
        //PageRequest : Pageable의 구현체로 파라미터로 페이지 번호, 크기, 정렬 기준을 전달
        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedAt").descending());
        Page<SchedulePageListResponseDTO> schedulePage = scheduleService.getAllSchedules(pageable);
        return schedulePage.getContent();
    }

    // 스케쥴 수정
    @PutMapping("/{scheduleId}")
    public ScheduleResponseDTO updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return scheduleService.updateSchedule(scheduleId, scheduleRequestDTO);
    }

    // 스케쥴 생성 이후 해당 스케쥴에 다른 유저 추가로 배치
    @PutMapping("/{scheduleId}/{userId}")
    public ScheduleResponseDTO addUserToSchedule(@PathVariable Long scheduleId, @PathVariable Long userId){
        return scheduleService.addUserToSchedule(scheduleId, userId);
    }



    // 스케쥴 삭제
    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }

}
