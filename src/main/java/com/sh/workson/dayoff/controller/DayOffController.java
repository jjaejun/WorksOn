package com.sh.workson.dayoff.controller;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.dayoff.entity.DayOff;
import com.sh.workson.dayoff.entity.DayOffListDto;
import com.sh.workson.dayoff.repository.DayOffRepository;
import com.sh.workson.dayoff.service.DayOffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/dayoff")
public class DayOffController {

    @Autowired
    private DayOffService dayOffService;
    @Autowired
    private DayOffRepository dayOffRepository;

    @GetMapping("/dayoffList.do")
    public void dayoffList(
            @PageableDefault(value = 10, page = 0) Pageable pageable, Model model,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
    ){
        Long id = employeeDetails.getEmployee().getId();

        // 총 연차 정보
        int totalDayOffCount = employeeDetails.getEmployee().getRest();

        Page<DayOffListDto> dayOffPage = dayOffService.findAll(pageable, id);

        // 사용 연차 정보
        double useDayOffCount = dayOffPage.getContent().stream()
                .mapToInt(DayOffListDto::getCount) // mapToInt를 사용하여 IntStream을 생성
                .sum();

        // 남은 연차 정보
        double remainDayOffCount = totalDayOffCount - useDayOffCount;

        model.addAttribute("size", dayOffPage.getSize());
        model.addAttribute("number", dayOffPage.getNumber());
        model.addAttribute("totalpages", dayOffPage.getTotalPages());
        model.addAttribute("remainDayOffCount", String.valueOf(remainDayOffCount));
        model.addAttribute("useDayOffCount", String.valueOf(useDayOffCount));
        model.addAttribute("dayoffs", dayOffPage.getContent());
        model.addAttribute("totalCount", dayOffPage.getTotalPages());
        log.debug("dayoffs = {}", dayOffPage.getContent());
    }

    @GetMapping("/dayOffListSearchDate.do")
    public ResponseEntity<?> dayoffListSearchDate(
            @RequestParam("year") String year,
            @PageableDefault(value = 5, page = 0) Pageable pageable,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
    ){
        LocalDateTime yearTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(year)), ZoneId.systemDefault());
//        log.debug("year = {}", year);
//        log.debug("yearTime = {}", yearTime);

        LocalDateTime startOfYear = yearTime.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
        LocalDateTime endOfYear = yearTime.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
        Long id = employeeDetails.getEmployee().getId();

        Page<DayOffListDto> dayOffListDtoPage = dayOffService.findEqSearchDate(pageable, id, startOfYear,endOfYear);
        log.debug("dayOffListDtoPage = {}", dayOffListDtoPage);
        return ResponseEntity.ok(dayOffListDtoPage);
    }
}
