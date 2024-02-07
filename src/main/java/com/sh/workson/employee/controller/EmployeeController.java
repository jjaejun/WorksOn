package com.sh.workson.employee.controller;

import com.sh.workson.auth.service.AuthService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
@Slf4j
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 민정
     */
    @Autowired
    AuthService authService;

    @GetMapping("/employeeDetail.do")
    public void employeeDetail(
            Authentication authentication,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
            ){
        log.debug("authentication = {}", authentication);
        log.debug("employeeDetails = {}", employeeDetails);
    }





    /**
     * 재준
     */









    /**
     * 우진
     */







    /**
     * 민준
     */








    /**
     * 준희
     */









    /**
     * 무진
     */











}
