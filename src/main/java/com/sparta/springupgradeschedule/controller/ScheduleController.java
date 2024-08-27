package com.sparta.springupgradeschedule.controller;

import com.sparta.springupgradeschedule.dto.schedule.SchedulePageListResponseDTO;
import com.sparta.springupgradeschedule.dto.schedule.ScheduleRequestDTO;
import com.sparta.springupgradeschedule.dto.schedule.ScheduleResponseDTO;
import com.sparta.springupgradeschedule.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 스케쥴 저장
    @PostMapping("/schedules")
    public ScheduleResponseDTO createSchedule(@RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        //요청 body Message -> RequestDTO 변환 및 scheduleService 전달.
        return scheduleService.createSchedule(scheduleRequestDTO);
    }

    // 스케쥴 단건 조회
    @GetMapping("/schedules/{id}")
    public ScheduleResponseDTO getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    // 스케쥴 전체 조회 - Pageable 적용 디폴트 페이지 크기 = 10
    @GetMapping("/schedules")
    public List<SchedulePageListResponseDTO> getAllSchedules(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedAt").descending());
        Page<SchedulePageListResponseDTO> schedulePage = scheduleService.getAllSchedules(pageable);
        return schedulePage.getContent();
    }

    // 스케쥴 수정
    @PutMapping("/schedules/{id}")
    public ScheduleResponseDTO updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return scheduleService.updateSchedule(id, scheduleRequestDTO);
    }

}
