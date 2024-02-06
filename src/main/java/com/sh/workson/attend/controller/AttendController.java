package com.sh.workson.attend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/attend")
public class AttendController {

    @GetMapping ("/attend.do")
    public void attend(){

    }
}
