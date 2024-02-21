package com.sh.workson.schedule.service;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import com.sh.workson.schedule.dto.CreateScheduleDto;
import com.sh.workson.schedule.dto.ScheduleCategoryDto;
import com.sh.workson.schedule.dto.ScheduleListDto;
import com.sh.workson.schedule.entity.Schedule;
import com.sh.workson.schedule.entity.ScheduleCategory;
import com.sh.workson.schedule.repository.ScheduleCategoryRepository;
import com.sh.workson.schedule.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    ScheduleCategoryRepository scheduleCategoryRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;

    public void createSchedule(CreateScheduleDto createScheduleDto) {
        Schedule schedule = convertToSchedule(createScheduleDto);
        log.debug("Schedule = {}",schedule);
        scheduleRepository.save(schedule);
    }

    private Schedule convertToSchedule(CreateScheduleDto createScheduleDto){
        Schedule schedule = modelMapper.map(createScheduleDto, Schedule.class);
        schedule.setEmployee(Employee.builder()
                    .id(createScheduleDto.getEmpId())
                    .build());
        if(createScheduleDto.getScheduleCategoryId() != null){
            schedule.setScheduleCategory(ScheduleCategory.builder()
                        .id(createScheduleDto.getScheduleCategoryId())
                        .build());
        }
        return schedule;
    }

    public List<ScheduleListDto> findByCategoryId(Long categoryId) {
        log.debug("categoryId = {}", categoryId);
        List<Schedule> schedules = scheduleRepository.findByCategoryId(categoryId);
        log.debug("schedules = {}", schedules);
        List<ScheduleListDto> scheduleListDtos = schedules.stream()
                                .map(this::convertToScheduleListDto)
                                .collect(Collectors.toList());
        log.debug("scheduleListDtos = {}", scheduleListDtos);
        return scheduleListDtos;
    }

    private ScheduleListDto convertToScheduleListDto(Schedule schedule){
        ScheduleListDto scheduleListDto = modelMapper.map(schedule, ScheduleListDto.class);
        scheduleListDto.setEmpId(schedule.getEmployee().getId());
        scheduleListDto.setColor(schedule.getScheduleCategory().getColor());
        return scheduleListDto;
    }



    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
