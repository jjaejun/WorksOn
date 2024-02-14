package com.sh.workson.attend.controller;

import com.sh.workson.attend.entity.*;
import com.sh.workson.attend.repository.AttendRepository;
import com.sh.workson.attend.repository.AttendRequestRepository;
import com.sh.workson.attend.service.AttendService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.employee.entity.Employee;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/attend")
public class AttendController {

    @Autowired
    private AttendService attendService;
    @Autowired
    private AttendRepository attendRepository;
    @Autowired
    private AttendRequestRepository attendRequestRepository;

    @GetMapping("/attendList.do")
    public void attendList(@PageableDefault(value = 5, page = 0) Pageable pageable, Model model,
                           @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        Long id = employeeDetails.getEmployee().getId();
        Page<AttendListDto> attendPage = attendService.findAll(pageable, id);
        model.addAttribute("attends", attendPage.getContent());
        model.addAttribute("totalCount", attendPage.getTotalElements());


        Attend firstAttend = attendService.findByOrderByStartAt(id);
        model.addAttribute("attend", firstAttend);
        log.debug("attend = {}", firstAttend);
        log.debug("attends = {}", attendPage.getContent());
    }

    // 출근 버튼을 처리하는 메소드
    @PostMapping("/startWork.do")
    public ResponseEntity<?> startWork(
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes
    ) {
        employeeDetails.getEmployee().getId(); // 사용자 아이디
        log.debug("employeeId = {}", employeeDetails.getEmployee().getId());
        Attend attend = Attend.builder()
                .employee(employeeDetails.getEmployee())
                .build();

        attendService.insertAttend(attend);
        log.debug("attends = {}", attend);

        return ResponseEntity.ok("출근 등록이 완료 됐습니다.");
    }

    // 퇴근 버튼을 처리하는 메소드
    @PostMapping("/endWork.do")
    public ResponseEntity<?> endWork(
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            @AuthenticationPrincipal Attend attend,
            RedirectAttributes redirectAttributes
    ) {
        Long id = employeeDetails.getEmployee().getId();
        Attend attending = attendService.findAttendByEmployeeId(id);

        attendService.updateEndAt(attending);
        log.debug("attending = {}", attending);
        return ResponseEntity.ok("퇴근 등록이 완료되었습니다.");

    }

    @PostMapping("/request.do")
    public ResponseEntity<?> request(
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            @RequestParam("content") String content,
            @RequestParam("attendId") Long attendId,
            RedirectAttributes redirectAttributes
    ){
        AttendRequest attendRequest = AttendRequest.builder()
                .attend(Attend.builder()
                        .id(attendId)
                        .build())
                .content(content)
                .type(Type.WORK)
                .checkAr(CheckAr.N)
                .build();
        attendRequestRepository.save(attendRequest);
        log.debug("attendRequest = {}", attendRequest);
        return ResponseEntity.ok("정정 요청이 완료되었습니다");

    }
}
