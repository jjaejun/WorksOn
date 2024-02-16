package com.sh.workson.dayoff.service;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.dayoff.entity.DayOff;
import com.sh.workson.dayoff.entity.DayOffListDto;
import com.sh.workson.dayoff.repository.DayOffRepository;
import com.sh.workson.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class DayOffService {

    @Autowired
    private DayOffRepository dayOffRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeRepository employeeRepository;


    public Page<DayOffListDto> findAll(Pageable pageable, Long id) {
        Page<DayOff> dayOffPage = dayOffRepository.findAll(pageable, id);
        return dayOffPage.map((dayOff) -> convertToDayOffListDto(dayOff));
    }

    private DayOffListDto convertToDayOffListDto(DayOff dayOff) {
        DayOffListDto dayOffListDto = modelMapper.map(dayOff, DayOffListDto.class);
        return dayOffListDto;
    }
}
