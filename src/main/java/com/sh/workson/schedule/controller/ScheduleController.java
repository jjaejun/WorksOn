package com.sh.workson.schedule.controller;

import com.sh.workson.schedule.dto.CreateScheduleDto;
import com.sh.workson.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/schedule")
@Slf4j
@Valid
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/calender.do")
    public void schedule(){
        log.debug("scheduleService = {}", scheduleService.getClass());
    }

    @GetMapping("/createSchedule.do")
    public void  createSchedule(){
    }

    @PostMapping("/createSchedule.do")
    public String createSchedule(
            @Valid CreateScheduleDto createScheduleDto,
            BindingResult bindingResult) throws IOException {


        return "redirect:/schedule/calender.do";
    }

}
