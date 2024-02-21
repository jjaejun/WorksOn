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
    public void dayoffListSearchDate(
            @RequestParam("year") String year,
            @PageableDefault(value = 10, page = 0) Pageable pageable,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            Model model
    ){
        LocalDateTime yearTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(year)), ZoneId.systemDefault());
//        log.debug("year = {}", year);
//        log.debug("yearTime = {}", yearTime);

        LocalDateTime startOfYear = yearTime.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
        LocalDateTime endOfYear = yearTime.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
        Long id = employeeDetails.getEmployee().getId();

        // 총 연차 정보
        int totalDayOffCount = employeeDetails.getEmployee().getRest();

//        Page<DayOffListDto> dayOffPage = dayOffService.findAll(pageable, id);

        Page<DayOffListDto> dayOffListDtoPage = dayOffService.findEqSearchDate(pageable, id, startOfYear,endOfYear);
        log.debug("dayOffListDtoPage = {}", dayOffListDtoPage);

        // 사용 연차 정보
        double useDayOffCount = 0.0;
        for(DayOffListDto dayOff : dayOffListDtoPage.get().toList()){
            useDayOffCount += dayOff.getCount();
        }
        log.debug("useDayoffCount = {}", useDayOffCount);

        // 남은 연차 정보
        double remainDayOffCount = totalDayOffCount - useDayOffCount;

        model.addAttribute("dayoffs", dayOffListDtoPage.getContent());
        model.addAttribute("totalCount", dayOffListDtoPage.getTotalElements());
        model.addAttribute("size", dayOffListDtoPage.getSize());
        model.addAttribute("number", dayOffListDtoPage.getNumber());
        model.addAttribute("totalpages", dayOffListDtoPage.getTotalPages());
        model.addAttribute("remainDayOffCount", String.valueOf(remainDayOffCount));
        model.addAttribute("useDayOffCount", String.valueOf(useDayOffCount));
//        return ResponseEntity.ok(dayOffListDtoPage);
    }
}
