package com.sh.workson.employee.controller;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.auth.service.AuthService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.employee.dto.EmployeeUpdateProfileDto;
import com.sh.workson.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    @Autowired
    S3FileService s3FileService;

    @GetMapping("/employeeDetail.do")
    public void employeeDetail(
            Authentication authentication,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
            ){
        log.debug("authentication = {}", authentication);
        log.debug("employeeDetails = {}", employeeDetails);
    }

    @PostMapping("/updateProfile.do")
    public ResponseEntity<?> updateProfile(
            @Valid EmployeeUpdateProfileDto employeeUpdateProfileDto,
            BindingResult bindingResult,
            @RequestParam(name = "upFile") List<MultipartFile> upFiles,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        log.debug("upFiles = {}", upFiles);


        // 첨부파일 S3에 저장
        for(MultipartFile upFile : upFiles) {
            if(upFile.getSize() > 0){
                AttachmentCreateDto attachmentCreateDto = s3FileService.upload(upFile);
                employeeUpdateProfileDto.addProfile(employeeUpdateProfileDto);
            }
        }


        employeeUpdateProfileDto.setId(employeeDetails.getEmployee().getId());





        return null;
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
