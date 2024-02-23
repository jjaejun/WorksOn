package com.sh.workson.approval.controller;

import com.sh.workson.approval.dto.*;
import com.sh.workson.approval.entity.Approval;
import com.sh.workson.approval.entity.ApprovalLeave;
import com.sh.workson.approval.entity.ApprovalLine;
import com.sh.workson.approval.service.ApprovalService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.department.entity.Department;
import com.sh.workson.department.service.DepartmentService;
import com.sh.workson.employee.dto.IApprover;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

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
    public void approvalWait(@PageableDefault(size = 8, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        List<ApprovalHomeLeaveDto> approvalWaitLeave = approvalService.findWaitLeave(employeeDetails.getEmployee().getId());
        List<ApprovalHomeEquipmentDto> approvalWaitEquipment = approvalService.findWaitEquipment(employeeDetails.getEmployee().getId());
        List<ApprovalHomeCooperationDto> approvalWaitCooperation = approvalService.findWaitCooperation(employeeDetails.getEmployee().getId());

        List<Object> waitList = new ArrayList<>();
        waitList.addAll(approvalWaitLeave);
        waitList.addAll(approvalWaitEquipment);
        waitList.addAll(approvalWaitCooperation);

        Collections.sort(waitList, Comparator.comparingInt(o -> {
            if (o instanceof ApprovalHomeLeaveDto) {
                return Math.toIntExact(((ApprovalHomeLeaveDto) o).getId());
            } else if (o instanceof ApprovalHomeEquipmentDto) {
                return Math.toIntExact(((ApprovalHomeEquipmentDto) o).getId());
            } else if (o instanceof ApprovalHomeCooperationDto) {
                return Math.toIntExact(((ApprovalHomeCooperationDto) o).getId());
            }
            return 0;
        }));

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), waitList.size());

        if (start > end) {
            start = end;
        }

        log.debug("start = {}", (int) pageable.getOffset());
        log.debug("end = {}", Math.min((start + pageable.getPageSize()), waitList.size()));

        List<Object> pageContent = waitList.subList(start, end);

        Page<Object> page = new PageImpl<>(pageContent, pageable, waitList.size());


        // 모델에 추가
        model.addAttribute("waitList", page.getContent());
        model.addAttribute("size", page.getSize());
        model.addAttribute("number", page.getNumber());
        model.addAttribute("totalpages", page.getTotalPages());

    }

    /**
     * 결재 수신 문서함
     * - 같은 부서의 사람들의 결재 내용을 참조
     */
    @GetMapping("/approvalReception.do")
    public void approvalReception(@PageableDefault(size = 8, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.debug("pageable = {}", pageable);
        List<ApprovalHomeLeaveDto> approvalHomeReceptionLeave = approvalService.findReceptionLeave(employeeDetails.getEmployee().getDepartment().getId(), employeeDetails.getEmployee().getId());
        List<ApprovalHomeEquipmentDto> approvalHomeReceptionEquipment = approvalService.findReceptionEquipment(employeeDetails.getEmployee().getDepartment().getId(), employeeDetails.getEmployee().getId());
        List<ApprovalHomeCooperationDto> approvalHomeReceptionCooperation = approvalService.findReceptionCooperation(employeeDetails.getEmployee().getDepartment().getId(), employeeDetails.getEmployee().getId());

        List<Object> receList = new ArrayList<>();
        receList.addAll(approvalHomeReceptionLeave);
        receList.addAll(approvalHomeReceptionEquipment);
        receList.addAll(approvalHomeReceptionCooperation);

        Collections.sort(receList, Comparator.comparingInt(o -> {
            if (o instanceof ApprovalHomeLeaveDto) {
                return Math.toIntExact(((ApprovalHomeLeaveDto) o).getId());
            } else if (o instanceof ApprovalHomeEquipmentDto) {
                return Math.toIntExact(((ApprovalHomeEquipmentDto) o).getId());
            } else if (o instanceof ApprovalHomeCooperationDto) {
                return Math.toIntExact(((ApprovalHomeCooperationDto) o).getId());
            }
            return 0;
        }));

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), receList.size());

        if (start > end) {
            start = end;
        }

        log.debug("start = {}", (int) pageable.getOffset());
        log.debug("end = {}", Math.min((start + pageable.getPageSize()), receList.size()));

        List<Object> pageContent = receList.subList(start, end);

        Page<Object> page = new PageImpl<>(pageContent, pageable, receList.size());


        // 모델에 추가
        model.addAttribute("receList", page.getContent());
        model.addAttribute("size", page.getSize());
        model.addAttribute("number", page.getNumber());
        model.addAttribute("totalpages", page.getTotalPages());

    }

    /**
     * 결재 예정함
     */
    @GetMapping("/approvalExpected.do")
    public void approvalExpected(@PageableDefault(size = 8, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        List<ApprovalHomeLeaveDto> approvalExceptedLeave = approvalService.findExceptedLeave(employeeDetails.getEmployee().getId());
        List<ApprovalHomeEquipmentDto> approvalExceptedEquipment = approvalService.findExceptedEquipment(employeeDetails.getEmployee().getId());
        List<ApprovalHomeCooperationDto> approvalExceptedCooperation = approvalService.findExceptedCooperation(employeeDetails.getEmployee().getId());

        List<Object> exList = new ArrayList<>();
        exList.addAll(approvalExceptedLeave);
        exList.addAll(approvalExceptedEquipment);
        exList.addAll(approvalExceptedCooperation);

        Collections.sort(exList, Comparator.comparingInt(o -> {
            if (o instanceof ApprovalHomeLeaveDto) {
                return Math.toIntExact(((ApprovalHomeLeaveDto) o).getId());
            } else if (o instanceof ApprovalHomeEquipmentDto) {
                return Math.toIntExact(((ApprovalHomeEquipmentDto) o).getId());
            } else if (o instanceof ApprovalHomeCooperationDto) {
                return Math.toIntExact(((ApprovalHomeCooperationDto) o).getId());
            }
            return 0;
        }));

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), exList.size());

        if (start > end) {
            start = end;
        }

        log.debug("start = {}", (int) pageable.getOffset());
        log.debug("end = {}", Math.min((start + pageable.getPageSize()), exList.size()));

        List<Object> pageContent = exList.subList(start, end);

        Page<Object> page = new PageImpl<>(pageContent, pageable, exList.size());


        // 모델에 추가
        model.addAttribute("exList", page.getContent());
        model.addAttribute("size", page.getSize());
        model.addAttribute("number", page.getNumber());
        model.addAttribute("totalpages", page.getTotalPages());


    }

    /**
     * 임시 저장함 조회
     */
    @GetMapping("/approvalTemporary.do")
    public void approvalTemporary(@PageableDefault(size = 8, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);

        List<ApprovalHomeLeaveDto> approvalHomeLeaveDtoPage = approvalService.findTemporaryLeave(employeeDetails.getEmployee().getId());
        List<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtoPage = approvalService.findTemporaryEquipment(employeeDetails.getEmployee().getId());
        List<ApprovalHomeCooperationDto> approvalHomeCooperationDtoPage= approvalService.findTemporaryCooperation(employeeDetails.getEmployee().getId());


        List<Object> temporaryList = new ArrayList<>();
        temporaryList.addAll(approvalHomeLeaveDtoPage);
        temporaryList.addAll(approvalHomeEquipmentDtoPage);
        temporaryList.addAll(approvalHomeCooperationDtoPage);

        Collections.sort(temporaryList, Comparator.comparingInt(o -> {
            if (o instanceof ApprovalHomeLeaveDto) {
                return Math.toIntExact(((ApprovalHomeLeaveDto) o).getId());
            } else if (o instanceof ApprovalHomeEquipmentDto) {
                return Math.toIntExact(((ApprovalHomeEquipmentDto) o).getId());
            } else if (o instanceof ApprovalHomeCooperationDto) {
                return Math.toIntExact(((ApprovalHomeCooperationDto) o).getId());
            }
            return 0;
        }));

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), temporaryList.size());

        if (start > end) {
            start = end;
        }

        log.debug("start = {}", (int) pageable.getOffset());
        log.debug("end = {}", Math.min((start + pageable.getPageSize()), temporaryList.size()));

        List<Object> pageContent = temporaryList.subList(start, end);

        Page<Object> page = new PageImpl<>(pageContent, pageable, temporaryList.size());


        // 모델에 추가
        model.addAttribute("temporaryList", page.getContent());
        model.addAttribute("size", page.getSize());
        model.addAttribute("number", page.getNumber());
        model.addAttribute("totalpages", page.getTotalPages());

        log.debug("temporaryList = {}", page.getContent());
        log.debug("size = {}", page.getSize());
        log.debug("number = {}", page.getNumber());
        log.debug("totalpages = {}", page.getTotalPages());
        log.debug("temporaryList.size() = {}", temporaryList.size());
        log.debug("start = {}", pageable.getOffset());

    }

    /**
     * 연차 문서함 조회
     */
    @GetMapping("/approvalLeave.do")
    public void approvalLeave(@PageableDefault(size = 8, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeLeaveDto> approvalHomeLeaveDtoPage = approvalService.findLeave(employeeDetails.getEmployee().getId(),pageable);

        log.debug("approvalLeave = {}", approvalHomeLeaveDtoPage.getContent());

        model.addAttribute("approvalLeave", approvalHomeLeaveDtoPage.getContent());

        model.addAttribute("totalCount", approvalHomeLeaveDtoPage.getTotalElements());
        model.addAttribute("size", approvalHomeLeaveDtoPage.getSize());
        model.addAttribute("number", approvalHomeLeaveDtoPage.getNumber());
        model.addAttribute("totalpages", approvalHomeLeaveDtoPage.getTotalPages());

        log.debug("totalCount = {}", approvalHomeLeaveDtoPage.getTotalElements());
        log.debug("size = {}", approvalHomeLeaveDtoPage.getSize());
        log.debug("number = {}", approvalHomeLeaveDtoPage.getNumber());
        log.debug("totalpages = {}", approvalHomeLeaveDtoPage.getTotalPages());


    }

    /**
     * 비품 문서함 조회
     */
    @GetMapping("/approvalEquipment.do")
    public void approvalEquipment(@PageableDefault(size = 8, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtoPage = approvalService.findEquipment(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalEquipment = {}", approvalHomeEquipmentDtoPage.getContent());

        model.addAttribute("approvalEquipment", approvalHomeEquipmentDtoPage.getContent());

        model.addAttribute("totalCount", approvalHomeEquipmentDtoPage.getTotalElements());

        model.addAttribute("size", approvalHomeEquipmentDtoPage.getSize());
        model.addAttribute("number", approvalHomeEquipmentDtoPage.getNumber());
        model.addAttribute("totalpages", approvalHomeEquipmentDtoPage.getTotalPages());
    }

    /**
     * 협조 문서함 조회
     */
    @GetMapping("/approvalCooperation.do")
    public void approvalCooperation(@PageableDefault(size = 8, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        Page<ApprovalHomeCooperationDto> approvalHomeCooperationDtoPage= approvalService.findCooperation(employeeDetails.getEmployee().getId(), pageable);

        log.debug("approvalCooperation = {}", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("approvalCooperation", approvalHomeCooperationDtoPage.getContent());

        model.addAttribute("totalCount", approvalHomeCooperationDtoPage.getTotalElements());

        model.addAttribute("size", approvalHomeCooperationDtoPage.getSize());
        model.addAttribute("number", approvalHomeCooperationDtoPage.getNumber());
        model.addAttribute("totalpages", approvalHomeCooperationDtoPage.getTotalPages());

        log.debug("totalCount = {}", approvalHomeCooperationDtoPage.getTotalElements());
        log.debug("size = {}", approvalHomeCooperationDtoPage.getSize());
        log.debug("number = {}", approvalHomeCooperationDtoPage.getNumber());
        log.debug("totalpages = {}", approvalHomeCooperationDtoPage.getTotalPages());
    }

    /**
     * 수신 문서함
     */
    @GetMapping("/approvalReceived.do")
    public void approvalRecevied(@PageableDefault(size = 8, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        List<ApprovalHomeLeaveDto> approvalHomeLeaveDtoPage = approvalService.findReLeave(employeeDetails.getEmployee().getId());
        List<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtoPage = approvalService.findReEquipment(employeeDetails.getEmployee().getId());
        List<ApprovalHomeCooperationDto> approvalHomeCooperationDtoPage= approvalService.findReCooperation(employeeDetails.getEmployee().getId());

        List<Object> ReList = new ArrayList<>();
        ReList.addAll(approvalHomeLeaveDtoPage);
        ReList.addAll(approvalHomeEquipmentDtoPage);
        ReList.addAll(approvalHomeCooperationDtoPage);

        Collections.sort(ReList, Comparator.comparingInt(o -> {
            if (o instanceof ApprovalHomeLeaveDto) {
                return Math.toIntExact(((ApprovalHomeLeaveDto) o).getId());
            } else if (o instanceof ApprovalHomeEquipmentDto) {
                return Math.toIntExact(((ApprovalHomeEquipmentDto) o).getId());
            } else if (o instanceof ApprovalHomeCooperationDto) {
                return Math.toIntExact(((ApprovalHomeCooperationDto) o).getId());
            }
            return 0;
        }));

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), ReList.size());

        if (start > end) {
            start = end;
        }

        log.debug("start = {}", (int) pageable.getOffset());
        log.debug("end = {}", Math.min((start + pageable.getPageSize()), ReList.size()));

        List<Object> pageContent = ReList.subList(start, end);

        Page<Object> page = new PageImpl<>(pageContent, pageable, ReList.size());


        // 모델에 추가
        model.addAttribute("reList", page.getContent());
        model.addAttribute("size", page.getSize());
        model.addAttribute("number", page.getNumber());
        model.addAttribute("totalpages", page.getTotalPages());
    }

    /**
     * 발송 문서함
     */
    @GetMapping("/approvalSend.do")
    public void approvalSend(@PageableDefault(size = 8, page = 0) Pageable pageable, Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        log.info("approvalService = {}", approvalService.getClass());

        log.debug("pageable = {}", pageable);
        List<ApprovalHomeLeaveDto> approvalHomeLeaveDtoPage = approvalService.findAllLeave(employeeDetails.getEmployee().getId());
        List<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtoPage = approvalService.findAllEquipment(employeeDetails.getEmployee().getId());
        List<ApprovalHomeCooperationDto> approvalHomeCooperationDtoPage= approvalService.findAllCooperation(employeeDetails.getEmployee().getId());

        List<Object> SendList = new ArrayList<>();
        SendList.addAll(approvalHomeLeaveDtoPage);
        SendList.addAll(approvalHomeEquipmentDtoPage);
        SendList.addAll(approvalHomeCooperationDtoPage);

        Collections.sort(SendList, Comparator.comparingInt(o -> {
            if (o instanceof ApprovalHomeLeaveDto) {
                return Math.toIntExact(((ApprovalHomeLeaveDto) o).getId());
            } else if (o instanceof ApprovalHomeEquipmentDto) {
                return Math.toIntExact(((ApprovalHomeEquipmentDto) o).getId());
            } else if (o instanceof ApprovalHomeCooperationDto) {
                return Math.toIntExact(((ApprovalHomeCooperationDto) o).getId());
            }
            return 0;
        }));

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), SendList.size());

        if (start > end) {
            start = end;
        }

        log.debug("start = {}", (int) pageable.getOffset());
        log.debug("end = {}", Math.min((start + pageable.getPageSize()), SendList.size()));

        List<Object> pageContent = SendList.subList(start, end);

        Page<Object> page = new PageImpl<>(pageContent, pageable, SendList.size());


        // 모델에 추가
        model.addAttribute("sendList", page.getContent());
        model.addAttribute("size", page.getSize());
        model.addAttribute("number", page.getNumber());
        model.addAttribute("totalpages", page.getTotalPages());


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
        model.addAttribute("status", iApprovalCooperation.getStatus());
//        model.addAttribute("lineId", iApprovalCooperation.getLineId());

        List<IApproverCooperation> iApproverCooperationList = approvalService.findCooperationApprover(id);
        model.addAttribute("approver", iApproverCooperationList);
        log.debug("approver = {}", iApproverCooperationList);
    }

    /**
     * 새 결재 진행 회원 정보 가져오기
     */
    @GetMapping("/createApproval.do")
    public void createApproval(Model model, @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        List<IApprover> employees = employeeService.findApprover(employeeDetails.getEmployee().getId());
        model.addAttribute("employees", employees);
        log.debug("employees = {}", employees);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        log.debug("departments = {}", departments);

        Employee loginUser = employeeService.findLoginUser(employeeDetails.getEmployee().getId());
        model.addAttribute("loginUser", loginUser);
        log.debug("loginUser = {}", loginUser);
    }

    // 새 결재 진행 업무협조
    @PostMapping("/createApproval.do")
    public String createApproval(@Valid CreateCooperationDto createCooperationDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal EmployeeDetails employeeDetails) {
        // 1. 사용자 입력값
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new RuntimeException(message);
        }
        log.debug("createApprovalDto = {}", createCooperationDto);

        // createApprovalDto -> approval로 변환
        createCooperationDto.setEmpId(employeeDetails.getEmployee().getId());
        approvalService.createCooperationApproval(createCooperationDto);


        return "redirect:/approval/approvalHome.do";
    }

    // 협조 양식 승인
    @PostMapping("/approvalDetailCooperation.do")
    public String recognize(@Valid RecognizeCooperationDto recognizeCooperationDto,
                            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new RuntimeException(message);
        }
        log.debug("recognizeCooperationdto = {}", recognizeCooperationDto);

        recognizeCooperationDto.setIsLast(recognizeCooperationDto.getIsLast());
        approvalService.recognizeCooperation(recognizeCooperationDto);


        return "redirect:/approval/approvalHome.do";
    }

    // 협조 양식 반려
    @PostMapping("/rejectDetailCooperation.do")
    public String reject(@Valid RejectCooperationDto rejectCooperationDto,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new RuntimeException(message);
        }
        log.debug("rejectCooperationDto = {}", rejectCooperationDto);

        approvalService.rejectCooperation(rejectCooperationDto);

        return "redirect:/approval/approvalHome.do";
    }
}
