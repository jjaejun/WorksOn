package com.sh.workson.employee.controller;

import com.sh.workson.attachment.dto.ProfileAttachmentDto;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.auth.service.AuthService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/employee")
@Slf4j
@Validated
@DynamicInsert
@DynamicUpdate
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
            @RequestParam(name = "upFile") List<MultipartFile> upFiles,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        log.debug("upFiles = {}", upFiles);


        ProfileAttachmentDto profileAttachmentDto = new ProfileAttachmentDto();
        // 첨부파일 S3에 저장
        for(MultipartFile upFile : upFiles) {
            if(upFile.getSize() > 0){
                profileAttachmentDto = s3FileService.uploadProfile(upFile);
                log.debug("profileAttachmentDto = {}", profileAttachmentDto);
            }
        }

        // DB에 저장(게시글, 첨부파일)
        profileAttachmentDto.setEmpId(employeeDetails.getEmployee().getId());
        employeeService.updateProfile(profileAttachmentDto, employeeDetails);
        
        // 로그인된 사용자 업데이트
        authService.updateAuthentication(employeeDetails.getEmployee().getEmail());
        return ResponseEntity.ok("프로필 사진이 변경되었습니다.");
    }

    @GetMapping("/searchEmployee.do")
    public ResponseEntity<?> searchEmployee(
            @RequestParam(name = "name") String name
    ){
        return employeeService.findByName(name);
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
