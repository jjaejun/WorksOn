package com.sh.workson.schedule.service;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.schedule.dto.ScheduleCategoryDto;
import com.sh.workson.schedule.entity.ScheduleCategory;
import com.sh.workson.schedule.repository.ScheduleCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ScheduleCategoryService {
    @Autowired
    private ScheduleCategoryRepository scheduleCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    // public ScheduleCategoryDto findByEmpId(Employee employee) {
    //     List<ScheduleCategory> scheduleCategories = scheduleCategoryRepository.findByEmpId(employee);
    //     // scheduleCategories.stream().map(())
    //     // scheduleCategories.stream().map((scheduleCategory)->convertToScheduleCategoryDto(scheduleCategory));
    //     List<ScheduleCategoryDto> scheduleCategoryDtos = scheduleCategories
    //             .stream().map(this::convertToScheduleCategoryDto)
    //             .collect(Collectors.toList());
    //     log.debug("scheduleCategoryDtos = {}",scheduleCategoryDtos);
    //     return null;
    //
    // }
    //
    // private ScheduleCategoryDto convertToScheduleCategoryDto(ScheduleCategory scheduleCategory){
    //     ScheduleCategoryDto scheduleCategoryDto = modelMapper.map(scheduleCategory, ScheduleCategoryDto.class);
    //     // scheduleCategoryDto.setEmployee(
    //     //         Optional.ofNullable(scheduleCategory.getEmp_id())
    //     //                 .map((employee) -> employee.getId())
    //     //                 .orElse()
    //     // );
    //     return scheduleCategoryDto;
    // }
    public List<ScheduleCategoryDto> findByEmpId(Employee employee) {
        List<ScheduleCategory> scheduleCategories = scheduleCategoryRepository.findByEmpId(employee);
        List<ScheduleCategoryDto> scheduleCategoryDtos = scheduleCategories.stream()
                .map(this::convertToScheduleCategoryDto)
                .collect(Collectors.toList());
        return scheduleCategoryDtos;
    }

    private ScheduleCategoryDto convertToScheduleCategoryDto(ScheduleCategory scheduleCategory) {
        ScheduleCategoryDto scheduleCategoryDto = modelMapper.map(scheduleCategory, ScheduleCategoryDto.class);
        return scheduleCategoryDto;
    }


}
