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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Page<DayOffListDto> dayOffPage = dayOffService.findAll(pageable, id);
        model.addAttribute("dayoffs", dayOffPage.getContent());
        model.addAttribute("totalCount", dayOffPage.getTotalPages());
        log.debug("dayoffs = {}", dayOffPage.getContent());
    }
}
