package com.sh.workson.common.controller;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.dailywork.entity.DailyWorkListDto;
import com.sh.workson.dailywork.service.DailyWorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

    @Autowired
    private DailyWorkService dailyWorkService;

    @GetMapping("")
    public String index(
            Authentication authentication,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            Model model,  @PageableDefault(value = 10, page = 0) Pageable pageable
    ){
        if(employeeDetails == null){
            return "/auth/login";
        }
        else {

//          민준
            Long id = employeeDetails.getEmployee().getId();

            int totalSeedCount = employeeDetails.getEmployee().getSeed();
            int totalCherryCount = employeeDetails.getEmployee().getCherry();

            Page<DailyWorkListDto> dailyWorkListDtoPage = dailyWorkService.findAll(pageable, id);

            model.addAttribute("totalSeedCount", String.valueOf(totalSeedCount));
            model.addAttribute("totalCherryCount", String.valueOf(totalCherryCount));
            model.addAttribute("dailyworks", dailyWorkListDtoPage.getContent());
            model.addAttribute("totalCount", dailyWorkListDtoPage.getTotalPages());

            log.debug("dailyworks = {}", dailyWorkListDtoPage.getContent());















//            민정

















//            재준










//            준희








//          무진







//          우진







            return "index";
        }
    }
}
