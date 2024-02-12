package com.sh.workson.approval.controller;

import com.sh.workson.approval.dto.ApprovalHomeCooperationDto;
import com.sh.workson.approval.dto.ApprovalHomeEquipmentDto;
import com.sh.workson.approval.dto.ApprovalHomeLeaveDto;
import com.sh.workson.approval.service.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public void approvalHome(@PageableDefault(size = 5, page = 0) Pageable pageable, Model model) {
        log.info("approvalService = {}", approvalService.getClass()); //

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeLeaveDtoPage = approvalService.findAllLeave(pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtoPage = approvalService.findAllEquipment(pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeCooperationDtoPage= approvalService.findAllCooperation(pageable);

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

    @GetMapping("/approvalTemporary.do")
    public void approvalTemporary() {

    }

    @GetMapping("/approvalLeave.do")
    public void approvalLeave() {

    }

    @GetMapping("/approvalEquipment.do")
    public void approvalEquipment() {

    }

    @GetMapping("/approvalCooperation.do")
    public void approvalCooperation() {

    }

    @GetMapping("/approvalReceived.do")
    public void approvalRecevied() {

    }

    @GetMapping("/approvalSend.do")
    public void approvalSend() {

    }
}
