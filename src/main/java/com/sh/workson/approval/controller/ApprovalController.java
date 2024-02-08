package com.sh.workson.approval.controller;

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
        Page<ApprovalHomeLeaveDto> approvalHomeDtoPage = approvalService.findAll(pageable);
        log.debug("approvals = {}", approvalHomeDtoPage.getContent());
        model.addAttribute("approvals", approvalHomeDtoPage.getContent());
        model.addAttribute("totalCount", approvalHomeDtoPage.getTotalElements()); // 전체 게시물 수

    }
}
