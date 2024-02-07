package com.sh.workson.attend.service;

import com.sh.workson.attend.entity.Attend;
import com.sh.workson.attend.entity.AttendListDto;
import com.sh.workson.attend.entity.State;
import com.sh.workson.attend.repository.AttendRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendService {
    @Autowired
    AttendRepository attendRepository;
    @Autowired
    private ModelMapper modelMapper;

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
        if (attending != null && attending.getState() == State.WORK){
            throw new RuntimeException("이미 출근이 등록되어 있습니다.");
        }

        // 출근시간 확인
        LocalDateTime currentTime  = LocalDateTime.now();
        LocalDateTime nineAm = LocalDateTime.of(currentTime.getYear(), currentTime.getMonth(), currentTime.getDayOfMonth(), 9, 0);

        if (currentTime.isAfter(nineAm)){
            // 9시 이후 출근이면 지각
            attend.setState(State.LATE);
        } else {
            // 9시 이전 출근이면 정상 출근으로 처리
            attend.setState(State.WORK);
        }
        attendRepository.save(attend);

        return attend;
    }
}
