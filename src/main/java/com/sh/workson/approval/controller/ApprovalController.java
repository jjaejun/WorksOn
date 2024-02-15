package com.sh.workson.approval.controller;

import com.sh.workson.approval.dto.*;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 결재 대기 함
     */
    @GetMapping("/approvalWait.do")
    public void approvalWait(@PageableDefault(size = 10, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalWaitLeave = approvalService.findWaitLeave(employeeDetails.getEmployee().getId(),pageable);
        Page<ApprovalHomeEquipmentDto> approvalWaitEquipment = approvalService.findWaitEquipment(employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeCooperationDto> approvalWaitCooperation = approvalService.findWaitCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalWaitLeave = {}", approvalWaitLeave.getContent());
        log.debug("approvalWaitEquipment = {}", approvalWaitEquipment.getContent());
        log.debug("approvalWaitCooperation = {}", approvalWaitCooperation.getContent());

        model.addAttribute("approvalWaitLeave", approvalWaitLeave.getContent());
        model.addAttribute("approvalWaitEquipment", approvalWaitEquipment.getContent());
        model.addAttribute("approvalWaitCooperation", approvalWaitCooperation.getContent());

    }

    // 결재 수신 문서함 같은 부서의 사람들의 결재 내용을 참조
    @GetMapping("/approvalReception.do")
    public void approvalReception(@PageableDefault(size = 3, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeReceptionLeave = approvalService.findReceptionLeave(employeeDetails.getEmployee().getDepartment().getId(), employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeReceptionEquipment = approvalService.findReceptionEquipment(employeeDetails.getEmployee().getDepartment().getId(), employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeReceptionCooperation = approvalService.findReceptionCooperation(employeeDetails.getEmployee().getDepartment().getId(), employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalReceptionLeave = {}", approvalHomeReceptionLeave.getContent());
        log.debug("approvalReceptionEquipment = {}", approvalHomeReceptionEquipment.getContent());
        log.debug("approvalReceptionCooperation = {}", approvalHomeReceptionCooperation.getContent());

        model.addAttribute("approvalReceptionLeave", approvalHomeReceptionLeave.getContent());
        model.addAttribute("approvalReceptionEquipment", approvalHomeReceptionEquipment.getContent());
        model.addAttribute("approvalReceptionCooperation", approvalHomeReceptionCooperation.getContent());
    }

    /**
     * 결재 예정함
     */
    @GetMapping("/approvalExpected.do")
    public void approvalExpected(@PageableDefault(size = 10, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalExceptedLeave = approvalService.findExceptedLeave(employeeDetails.getEmployee().getId(),pageable);
        Page<ApprovalHomeEquipmentDto> approvalExceptedEquipment = approvalService.findExceptedEquipment(employeeDetails.getEmployee().getId(), pageable);
        Page<ApprovalHomeCooperationDto> approvalExceptedCooperation = approvalService.findExceptedCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalExceptedLeave = {}", approvalExceptedLeave.getContent());
        log.debug("approvalExceptedEquipment = {}", approvalExceptedEquipment.getContent());
        log.debug("approvalExceptedCooperation = {}", approvalExceptedCooperation.getContent());

        model.addAttribute("approvalExceptedLeave", approvalExceptedLeave.getContent());
        model.addAttribute("approvalExceptedEquipment", approvalExceptedEquipment.getContent());
        model.addAttribute("approvalExceptedCooperation", approvalExceptedCooperation.getContent());

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

    /**
     * 연차 상세 페이지 조회
     */
    @GetMapping("/approvalDetailLeave.do")
    public void approvalDetailLeave(@RequestParam("id") Long id, Model model) {
        IApprovalLeave iApprovalLeave = approvalService.findLeaveDetailById(id);
        model.addAttribute("id", iApprovalLeave.getId());
        model.addAttribute("approvalEndDate", iApprovalLeave.getApprovalEndDate());
        model.addAttribute("endDate", iApprovalLeave.getEndDate());
        model.addAttribute("startDate", iApprovalLeave.getStartDate());
        model.addAttribute("empId", iApprovalLeave.getEmpId());
        model.addAttribute("deptId", iApprovalLeave.getDeptId());
        model.addAttribute("leaveContent", iApprovalLeave.getLeaveContent());
        model.addAttribute("leId", iApprovalLeave.getLeId());
        model.addAttribute("leaveType", iApprovalLeave.getLeaveType());
        model.addAttribute("annul", iApprovalLeave.getAnnul());
        model.addAttribute("leaveCount", iApprovalLeave.getLeaveCount());
    }

    /**
     * 비품 상세 페이지 조회
     */
    @GetMapping("/approvalDetailEquipment.do")
    public void approvalDetailEquipment(@RequestParam("id") Long id, Model model){
        IApprovalEquipment iApprovalEquipment = approvalService.findEquipmentDetailById(id);
        model.addAttribute("id",iApprovalEquipment.getId());
        model.addAttribute("approvalEndDate",iApprovalEquipment.getApprovalEndDate());
        model.addAttribute("empId",iApprovalEquipment.getEmpId());
        model.addAttribute("deptId",iApprovalEquipment.getDeptId());
        model.addAttribute("eqId",iApprovalEquipment.getEqId());
        model.addAttribute("title",iApprovalEquipment.getTitle());
        model.addAttribute("content",iApprovalEquipment.getContent());
        model.addAttribute("productName",iApprovalEquipment.getProductName());
        model.addAttribute("price",iApprovalEquipment.getPrice());
        model.addAttribute("count",iApprovalEquipment.getCount());
        model.addAttribute("totalPrice",iApprovalEquipment.getTotalPrice());
        model.addAttribute("usage",iApprovalEquipment.getUsage());

    }

    /**
     * 협조 상세 페이지 조회
     */
    @GetMapping("/approvalDetailCooperation.do")
    public void approvalDetailCooperation(@RequestParam("id") Long id, Model model) {
        IApprovalCooperation iApprovalCooperation = approvalService.findCooperationDetailById(id);
        model.addAttribute("id",iApprovalCooperation.getId());
        model.addAttribute("approvalEndDate",iApprovalCooperation.getApprovalEndDate());
        model.addAttribute("empId",iApprovalCooperation.getEmpId());
        model.addAttribute("deptId",iApprovalCooperation.getDeptId());
        model.addAttribute("coId", iApprovalCooperation.getCoId());
        model.addAttribute("cooperationDept", iApprovalCooperation.getCooperationDept());
        model.addAttribute("title", iApprovalCooperation.getTitle());
        model.addAttribute("content", iApprovalCooperation.getContent());
        model.addAttribute("startDate", iApprovalCooperation.getStartDate());
        model.addAttribute("endDate", iApprovalCooperation.getEndDate());
    }
}
