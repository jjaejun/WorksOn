package com.sh.workson.approval.controller;

import com.sh.workson.approval.dto.ApprovalHomeCooperationDto;
import com.sh.workson.approval.dto.ApprovalHomeEquipmentDto;
import com.sh.workson.approval.dto.ApprovalHomeLeaveDto;
import com.sh.workson.approval.service.ApprovalService;
import com.sh.workson.auth.vo.EmployeeDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    /**
     * 전자 결재 메인 화면 조회
     */
    @GetMapping("/approvalHome.do")
    public void approvalHome(@PageableDefault(size = 5, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeLeaveDtoPage = approvalService.findAllLeave(employeeDetails.getEmployee().getId(),pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtoPage = approvalService.findAllEquipment(employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeCooperationDtoPage= approvalService.findAllCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalLeave = {}", approvalHomeLeaveDtoPage.getContent());
        log.debug("approvalEquipment = {}", approvalHomeEquipmentDtoPage.getContent());
        log.debug("approvalCooperation = {}", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("approvalLeave", approvalHomeLeaveDtoPage.getContent());
        model.addAttribute("approvalEquipment", approvalHomeEquipmentDtoPage.getContent());
        model.addAttribute("approvalCooperation", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("totalCount", approvalHomeLeaveDtoPage.getTotalElements());

    }

    @GetMapping("/approvalWait.do")
    public void approvalWait() {

    }

    @GetMapping("/approvalReception.do")
    public void approvalReception() {

    }

    @GetMapping("/approvalExpected.do")
    public void approvalExpected() {

    }

    /**
     * 임시 저장함 조회
     */
    @GetMapping("/approvalTemporary.do")
    public void approvalTemporary(@PageableDefault(size = 10, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeLeaveDtoPage = approvalService.findTemporaryLeave(employeeDetails.getEmployee().getId(),pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtoPage = approvalService.findTemporaryEquipment(employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeCooperationDtoPage= approvalService.findTemporaryCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalLeave = {}", approvalHomeLeaveDtoPage.getContent());
        log.debug("approvalEquipment = {}", approvalHomeEquipmentDtoPage.getContent());
        log.debug("approvalCooperation = {}", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("approvalLeave", approvalHomeLeaveDtoPage.getContent());
        model.addAttribute("approvalEquipment", approvalHomeEquipmentDtoPage.getContent());
        model.addAttribute("approvalCooperation", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("totalCount", approvalHomeLeaveDtoPage.getTotalElements());
    }

    /**
     * 연차 문서함 조회
     */
    @GetMapping("/approvalLeave.do")
    public void approvalLeave(@PageableDefault(size = 10, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeLeaveDtoPage = approvalService.findAllLeave(employeeDetails.getEmployee().getId(),pageable);

        log.debug("approvalLeave = {}", approvalHomeLeaveDtoPage.getContent());

        model.addAttribute("approvalLeave", approvalHomeLeaveDtoPage.getContent());

        model.addAttribute("totalCount", approvalHomeLeaveDtoPage.getTotalElements());
    }

    /**
     * 비품 문서함 조회
     */
    @GetMapping("/approvalEquipment.do")
    public void approvalEquipment(@PageableDefault(size = 10, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtoPage = approvalService.findAllEquipment(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalEquipment = {}", approvalHomeEquipmentDtoPage.getContent());

        model.addAttribute("approvalEquipment", approvalHomeEquipmentDtoPage.getContent());

        model.addAttribute("totalCount", approvalHomeEquipmentDtoPage.getTotalElements());
    }

    /**
     * 협조 문서함 조회
     */
    @GetMapping("/approvalCooperation.do")
    public void approvalCooperation(@PageableDefault(size = 10, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeCooperationDtoPage= approvalService.findAllCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalCooperation = {}", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("approvalCooperation", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("totalCount", approvalHomeCooperationDtoPage.getTotalElements());
    }

    @GetMapping("/approvalReceived.do")
    public void approvalRecevied() {

    }

    @GetMapping("/approvalSend.do")
    public void approvalSend() {

    }
}
