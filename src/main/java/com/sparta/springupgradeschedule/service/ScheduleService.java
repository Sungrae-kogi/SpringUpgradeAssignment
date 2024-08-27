package com.sparta.springupgradeschedule.service;

import com.sparta.springupgradeschedule.dto.schedule.SchedulePageListResponseDTO;
import com.sparta.springupgradeschedule.dto.schedule.ScheduleRequestDTO;
import com.sparta.springupgradeschedule.dto.schedule.ScheduleResponseDTO;
import com.sparta.springupgradeschedule.entity.Schedule;
import com.sparta.springupgradeschedule.repository.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Page<>는 다양한 메서드를 제공하여 페이지네이션과 관련된 정보를 제공합니다:
     *
     * getContent(): 페이지에 포함된 실제 데이터를 리스트 형태로 반환합니다.
     *
     * getTotalPages(): 전체 페이지 수를 반환합니다. 예를 들어, 100개의 항목이 있고 한 페이지에 10개씩 보여준다면 총 10페이지가 됩니다.
     *
     * getTotalElements(): 전체 데이터의 개수를 반환합니다.
     *
     * getNumber(): 현재 페이지 번호를 반환합니다. 페이지 번호는 0부터 시작합니다.
     *
     * getSize(): 한 페이지에 포함된 데이터의 개수를 반환합니다.
     *
     * getNumberOfElements(): 현재 페이지에 포함된 데이터의 개수를 반환합니다. 이는 페이지 크기와 다를 수 있으며, 마지막 페이지에서 주로 사용됩니다.
     *
     * hasNext(): 다음 페이지가 있는지 여부를 반환합니다.
     *
     * hasPrevious(): 이전 페이지가 있는지 여부를 반환합니다.
     *
     * isFirst(): 현재 페이지가 첫 페이지인지 여부를 반환합니다.
     *
     * isLast(): 현재 페이지가 마지막 페이지인지 여부를 반환합니다.
     *
     */
    public Page<SchedulePageListResponseDTO> getAllSchedules(Pageable pageable) {
        // Schedule 엔티티를 페이지네이션하여 가져옴
        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);

        // Schedule 엔티티를 ScheduleResponseDTO로 변환
        return schedulePage.map(SchedulePageListResponseDTO::new);
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
