package com.sh.workson.attend.service;

import com.sh.workson.attend.entity.Attend;
import com.sh.workson.attend.entity.AttendListDto;
import com.sh.workson.attend.entity.State;
import com.sh.workson.attend.repository.AttendRepository;
import com.sh.workson.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AttendService {
    @Autowired
    AttendRepository attendRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<AttendListDto> findAll(Pageable pageable, Long id) {
        Page<Attend> attendPage = attendRepository.findAll(pageable, id);
        return attendPage.map((attend) -> convertToAttendListDto(attend));
    }

    private AttendListDto convertToAttendListDto(Attend attend) {
        AttendListDto attendListDto = modelMapper.map(attend, AttendListDto.class);
        attendListDto.setEmployeeId(
                Optional.ofNullable(attend.getEmployee())
                        .map((emloyee) -> emloyee.getId())
                        .orElse(null)
        );
        List<String> requestContents = attend.getAttendRequests().stream()
                .map(attendRequest -> attendRequest.getContent())  // 각 객체의 content 속성을 가져옴
                .collect(Collectors.toList());

        String content = String.join(", ", requestContents);
        attendListDto.setContent(content);


        return attendListDto;
    }

    public Attend insertAttend(Attend attend) {

        // 이미 출근 등록되어 있는지 확인
        Attend attending = attendRepository.findByAttendId(attend.getEmployee().getId());
        // 이미 출근 등록되어 있으면 예외 처리
        if (attending != null) {
            throw new RuntimeException("이미 출근이 등록되어 있습니다.");
        }

        // 출근시간 확인
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime nineAm = LocalDateTime.of(currentTime.getYear(), currentTime.getMonth(), currentTime.getDayOfMonth(), 9, 0);

        if (currentTime.isAfter(nineAm)) {
            // 9시 이후 출근이면 지각
            attend.setState(State.LATE);
        } else {
            // 9시 이전 출근이면 정상 출근으로 처리
            attend.setState(State.WORK);
        }

        // 출근 정보 저장
        attendRepository.save(attend);

        return attend;
    }


    public Attend findByOrderByStartAt(Long id) {
        Attend attend = attendRepository.findByOrderByStartAt(id);
        return attend;
    }

    public Attend updateEndAt(Attend attending) {
        // 출근 정보가 없는 경우 예외 처리
        if (attending == null) {
            throw new RuntimeException("출근 정보가 없습니다.");
        }

        // 퇴근시간 확인
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime sixPm = LocalDateTime.of(currentTime.getYear(), currentTime.getMonth(), currentTime.getDayOfMonth(), 18, 0);

        if (currentTime.isAfter(sixPm)) {
            // 18시 이후 퇴근이면 퇴근으로 처리
            attending.setState(State.QUIT);

            // endAt 컬럼을 현재 시간으로 업데이트
            attending.setEndAt(currentTime);

            // 출근 정보 업데이트 (endAt 컬럼만 업데이트)
            attendRepository.saveAndFlush(attending);

            return attending;
        } else {
            // 18시 이전 퇴근시 예외 처리
            throw new RuntimeException("퇴근시간이 아닙니다.");
        }
    }

    public Attend findAttendByEmployeeId(Long id) {
        return attendRepository.findAttendByEmployeeId(id);
    }


}
