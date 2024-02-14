package com.sh.workson.schedule.service;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import com.sh.workson.schedule.dto.CreateScheduleDto;
import com.sh.workson.schedule.entity.Schedule;
import com.sh.workson.schedule.entity.ScheduleCategory;
import com.sh.workson.schedule.repository.ScheduleCategoryRepository;
import com.sh.workson.schedule.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        schedule.setScheduleCategory(ScheduleCategory.builder()
                    .id(createScheduleDto.getScheduleCategoryId())
                    .build());
        return schedule;
    }
}
