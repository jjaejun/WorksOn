package com.sh.workson.dailywork.service;

import com.sh.workson.dailywork.entity.DailyWork;
import com.sh.workson.dailywork.entity.DailyWorkListDto;
import com.sh.workson.dailywork.repository.DailyWorkRepository;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

@Service
@Transactional
@Slf4j
public class DailyWorkService {
    @Autowired
    private DailyWorkRepository dailyWorkRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<DailyWorkListDto> findAll(Pageable pageable, Long id) {
        Page<DailyWork> dailyWorkPage = dailyWorkRepository.findAll(pageable, id);
        return dailyWorkPage.map((dailyWork) -> convertToDailyWorkListDto(dailyWork));
    }

    private DailyWorkListDto convertToDailyWorkListDto(DailyWork dailyWork) {
        DailyWorkListDto dailyWorkListDto = modelMapper.map(dailyWork, DailyWorkListDto.class);
        return dailyWorkListDto;
    }


}
