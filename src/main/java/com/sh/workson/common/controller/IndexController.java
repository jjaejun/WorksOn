package com.sh.workson.common.controller;

import com.sh.workson.auth.vo.EmployeeDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String index(
            Authentication authentication,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
    ){
        if(employeeDetails == null){
            return "/auth/login";
        }
        else {





















            return "index";
        }
    }
}
