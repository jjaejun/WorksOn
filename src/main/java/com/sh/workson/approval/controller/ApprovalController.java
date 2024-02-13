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
     *
     */
    @GetMapping("/approvalHome.do")
    public void approvalHome(@PageableDefault(size = 3, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        // 결재 완료 문서 (승인 혹은 반려)
        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeCheckLeave = approvalService.findCheckLeave(employeeDetails.getEmployee().getId(),pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeCheckEquipment = approvalService.findCheckEquipment(employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeCheckCooperation = approvalService.findCheckCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalCheckLeave = {}", approvalHomeCheckLeave.getContent());
        log.debug("approvalCheckEquipment = {}", approvalHomeCheckEquipment.getContent());
        log.debug("approvalCheckCooperation = {}", approvalHomeCheckCooperation.getContent());

        model.addAttribute("approvalCheckLeave", approvalHomeCheckLeave.getContent());
        model.addAttribute("approvalCheckEquipment", approvalHomeCheckEquipment.getContent());
        model.addAttribute("approvalCheckCooperation", approvalHomeCheckCooperation.getContent());

        // 결재 진행 문서
        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeProceedingLeave = approvalService.findProceedingLeave(employeeDetails.getEmployee().getId(),pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeProceedingEquipment = approvalService.findProceedingEquipment(employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeProceedingCooperation = approvalService.findProceedingCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalProceedingLeave = {}", approvalHomeProceedingLeave.getContent());
        log.debug("approvalProceedingEquipment = {}", approvalHomeProceedingEquipment.getContent());
        log.debug("approvalProceedingCooperation = {}", approvalHomeProceedingCooperation.getContent());

        model.addAttribute("approvalProceedingLeave", approvalHomeProceedingLeave.getContent());
        model.addAttribute("approvalProceedingEquipment", approvalHomeProceedingEquipment.getContent());
        model.addAttribute("approvalProceedingCooperation", approvalHomeProceedingCooperation.getContent());

        // 결재 요청온 문서
        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeReceiveLeave = approvalService.findReceiveLeave(employeeDetails.getEmployee().getId(),pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeReceiveEquipment = approvalService.findReceiveEquipment(employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeReceiveCooperation = approvalService.findReceiveCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalReceiveLeave = {}", approvalHomeReceiveLeave.getContent());
        log.debug("approvalReceiveEquipment = {}", approvalHomeReceiveEquipment.getContent());
        log.debug("approvalReceiveCooperation = {}", approvalHomeReceiveCooperation.getContent());

        model.addAttribute("approvalReceiveLeave", approvalHomeReceiveLeave.getContent());
        model.addAttribute("approvalReceiveEquipment", approvalHomeReceiveEquipment.getContent());
        model.addAttribute("approvalReceiveCooperation", approvalHomeReceiveCooperation.getContent());


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

    /**
     * 수신 문서함
     */
    @GetMapping("/approvalReceived.do")
    public void approvalRecevied(@PageableDefault(size = 10, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeLeaveDtoPage = approvalService.findReLeave(employeeDetails.getEmployee().getId(),pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtoPage = approvalService.findReEquipment(employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeCooperationDtoPage= approvalService.findReCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalLeave = {}", approvalHomeLeaveDtoPage.getContent());
        log.debug("approvalEquipment = {}", approvalHomeEquipmentDtoPage.getContent());
        log.debug("approvalCooperation = {}", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("approvalLeave", approvalHomeLeaveDtoPage.getContent());
        model.addAttribute("approvalEquipment", approvalHomeEquipmentDtoPage.getContent());
        model.addAttribute("approvalCooperation", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("totalCount", approvalHomeLeaveDtoPage.getTotalElements());
    }

    /**
     * 발송 문서함
     */
    @GetMapping("/approvalSend.do")
    public void approvalSend(@PageableDefault(size = 3, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
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
}
