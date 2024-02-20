package com.sh.workson.schedule.controller;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.schedule.dto.*;
import com.sh.workson.schedule.service.ScheduleCategoryService;
import com.sh.workson.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/schedule")
@Slf4j
@Valid
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleCategoryService scheduleCategoryService;

    @GetMapping("/calendar.do")
    public void scheduleCategoryList(
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            Model model){
        log.debug("employeeDetails = {}", employeeDetails);
        List<ScheduleCategoryDto> myScheduleCategoryDtos = scheduleCategoryService.findByEmpId(employeeDetails);
        model.addAttribute("myScheduleCategories", myScheduleCategoryDtos);
    }

    @GetMapping("/mySchedule.do")
    @ResponseBody
    public ResponseEntity<?> mySchedule(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("isChecked") boolean isChecked){
        if(isChecked){
        List<ScheduleListDto> scheduleListDtos = scheduleService.findByCategoryId(categoryId);
        log.debug("scheduleListDtos = {}", scheduleListDtos);
        return new ResponseEntity<>(scheduleListDtos, HttpStatus.OK);
        }
        else{
            List<ScheduleListDto> scheduleListDtos = new ArrayList<>();
            return new ResponseEntity<>(scheduleListDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/createSchedule.do")
    public void  createSchedule(
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            Model model){
        log.debug("scheduleService = {}", scheduleService.getClass());
        log.debug("employee ={}", employeeDetails);

        List<ScheduleCategoryDto> scheduleCategoryDtos = scheduleCategoryService.findByEmpId(employeeDetails);
        log.debug("scheduleCategoryDtos = {}", scheduleCategoryDtos);
        model.addAttribute("scheduleCategories", scheduleCategoryDtos);


    }

    @PostMapping("/createSchedule.do")
    public String createSchedule(
            @RequestParam(value = "scheduleCategoryId") Long scheduleCategoryId,
            @Valid CreateScheduleDto createScheduleDto,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        createScheduleDto.setEmpId(employeeDetails.getEmployee().getId());
        createScheduleDto.setScheduleCategoryId(scheduleCategoryId);

        log.debug("createScheduleDto = {}", createScheduleDto);
        scheduleService.createSchedule(createScheduleDto);
        redirectAttributes.addFlashAttribute("msg", "게시글을 성공적으로 등록했습니다!\uD83D\uDC4D");
        return "redirect:/schedule/calendar.do";
    }

    @GetMapping("/categoryList.do")
    public void myCategoryList(
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            Model model
    ){
    }

    @PostMapping("/CUCategory.do")
    public String cuCategory(
            @Valid ScheduleCategoryDto scheduleCategoryDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            // 에러 처리 로직 개선
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/errorPage"; // 에러 페이지로 리다이렉트
        }

        log.debug("name = {}", scheduleCategoryDto.getName());
        log.debug("id = {}", scheduleCategoryDto.getId());
        log.debug("color = {}", scheduleCategoryDto.getColor());

        if (scheduleCategoryDto.getId() == null) {
            // Create
            scheduleCategoryDto.setEmpId(employeeDetails.getEmployee().getId());
            scheduleCategoryService.createScheduleCategory(scheduleCategoryDto);
        } else {
            // Update
            scheduleCategoryDto.setEmpId(employeeDetails.getEmployee().getId());
            scheduleCategoryService.updateScheduleCategory(scheduleCategoryDto);
        }

        return "redirect:/schedule/calendar.do";
    }

    @PostMapping("/deleteCategory.do")
    @ResponseBody
    public ResponseEntity<?> deleteCategory(
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            @RequestParam (value = "scheduleCategoryId") Long id){
        scheduleCategoryService.deleteById(id);
        log.debug("id = {}", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    // @PostMapping("/deleteCategory.do")
    // public String deleteCategory(
    //         @AuthenticationPrincipal EmployeeDetails employeeDetails,
    //         @RequestParam (value = "scheduleCategoryId") Long id,
    //         RedirectAttributes redirectAttributes){
    //     scheduleCategoryService.deleteById(id);
    //     log.debug("id = {}", id);
    //     return "redirect:/schedule/calendar.do";
    // }

}
