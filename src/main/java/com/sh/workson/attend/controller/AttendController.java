package com.sh.workson.attend.controller;

import com.sh.workson.attend.entity.AttendListDto;
import com.sh.workson.attend.service.AttendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    @GetMapping ("/attendList.do")
    public void attendList(@PageableDefault(value = 5, page = 0)Pageable pageable, Model model){
        Long id = 952L;
    Page<AttendListDto> attendPage = attendService.findAll(pageable, id);
    model.addAttribute("attends",attendPage.getContent());
    model.addAttribute("totalCount", attendPage.getTotalElements());
        log.debug("attends = {}", attendPage.getContent());
    }
}
