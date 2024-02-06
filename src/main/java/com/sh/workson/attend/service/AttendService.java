package com.sh.workson.attend.service;

import com.sh.workson.attend.entity.Attend;
import com.sh.workson.attend.entity.AttendListDto;
import com.sh.workson.attend.repository.AttendRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendService {
    @Autowired
    AttendRepository attendRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Page<AttendListDto> findAll(Pageable pageable) {
        Page<Attend> attendPage = attendRepository.findAll(pageable);
        return attendPage.map((attend) -> convertToAttendListDto(attend));
    }

    private AttendListDto convertToAttendListDto(Attend attend) {
        AttendListDto attendListDto = modelMapper.map(attend, AttendListDto.class);
        attendListDto.setEmployeeId(
                Optional.ofNullable(attend.getEmployee())
                        .map((emloyee) -> emloyee.getId())
                        .orElse(null)
        );
        attendListDto.setContent(attend.getAttendRequest().getContent());
        return attendListDto;
    }
}
