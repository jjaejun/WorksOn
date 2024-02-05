package com.sh.workson.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/project")
public class ProjectController {

    @GetMapping("/projectList.do")
    public void projectList(){

    }
}
