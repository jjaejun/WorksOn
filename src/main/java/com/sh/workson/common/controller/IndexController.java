package com.sh.workson.common.controller;

import com.sh.workson.approval.dto.ApprovalHomeCooperationDto;
import com.sh.workson.approval.dto.ApprovalHomeEquipmentDto;
import com.sh.workson.approval.dto.ApprovalHomeLeaveDto;
import com.sh.workson.approval.service.ApprovalService;
import com.sh.workson.attend.entity.Attend;
import com.sh.workson.attend.entity.State;
import com.sh.workson.attend.service.AttendService;
import com.sh.workson.auth.service.AuthService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.cherry.entity.Cherry;
import com.sh.workson.cherry.repository.CherryRepository;
import com.sh.workson.dailywork.entity.DailyWork;
import com.sh.workson.dailywork.entity.DailyWorkListDto;
import com.sh.workson.dailywork.repository.DailyWorkRepository;
import com.sh.workson.dailywork.service.DailyWorkService;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import com.sh.workson.employee.service.EmployeeService;
import com.sh.workson.project.dto.ProjectDashBoardDto;
import com.sh.workson.project.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

    @Autowired
    private DailyWorkService dailyWorkService;
    @Autowired
    private DailyWorkRepository dailyWorkRepository;
    @Autowired
    private CherryRepository cherryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private AuthService authService;
    @Autowired
    private AttendService attendService;
    @Autowired
    private ApprovalService approvalService;


    @GetMapping("")
    public String index(
            Authentication authentication,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            Model model, @PageableDefault(value = 10, page = 0) Pageable pageable
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
            // project
            Page<ProjectDashBoardDto> projectPage = projectService.findTop3Project(employeeDetails.getEmployee().getId(), PageRequest.of(0, 3));
            model.addAttribute("projects", projectPage);
            log.debug("projectDashBoard = {}", projectPage);



//            Page<Approval>
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

            log.debug("size = {}",waitList.size());;
            if(waitList.size() > 3){
                model.addAttribute("waitApprovals", waitList.subList(0, 2));
            }
            else {
                model.addAttribute("waitApprovals", waitList);
            }

            // 수신문서함
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

            if(ReList.size() > 3){
                model.addAttribute("reApprovals", ReList.subList(0, 2));
            }
            else {
                model.addAttribute("reApprovals", ReList);
            }







            // 출퇴근 찍기
            Attend firstAttend = attendService.findByOrderByStartAt(id);
            String stateKr = null;
            if(firstAttend != null) {
                if(firstAttend.getState().equals(State.LATE) || firstAttend.getState().equals(State.WORK)){
                    stateKr = "업무중";
                }
                else if(firstAttend.getState().equals(State.QUIT)){
                    stateKr = "퇴근";
                }
            }
            else {
                // firstAttend가 null 일때는 startAt와 endAt만 셋팅한 빈 객체를 만들자
                firstAttend = new Attend();
                firstAttend.setStartAt(null);
                firstAttend.setEndAt(null);
                stateKr = "";
            }


            model.addAttribute("attend", firstAttend);
            model.addAttribute("state", stateKr);













//            재준










//            준희








//          무진







//          우진






            return "index";
        }
    }

    @Transactional
    @PostMapping("/indexCherry.do")
    public String indexCherry(
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            @RequestParam("createEmp") List<Long> employees,
            @RequestParam("contentGift") String contentGift,
            @RequestParam("workLog") String workLog,
            @RequestParam("praise") String praise
    ){
        Long id = employeeDetails.getEmployee().getId();
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + id));

        DailyWork dailyWork = DailyWork.builder()
                .content(workLog)
                .createdAt(LocalDateTime.now())
                .cherryCount(employees.size() * Integer.parseInt(praise))
                .employee(employeeDetails.getEmployee())
                .build();


        int totalUsedSeedCount = employees.size() * Integer.parseInt(praise);
        int currentSeedCount = employee.getSeed();
        int updatedSeedCount = Math.max(currentSeedCount - totalUsedSeedCount, 0);
        employee.setSeed(updatedSeedCount);

        // Save DailyWork entity
        dailyWorkRepository.save(dailyWork);

        for (Long receivingEmployeeId : employees) {
            Employee receivingEmployee = employeeRepository.findById(receivingEmployeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + receivingEmployeeId));

            int receivedCherry = Integer.parseInt(praise);
            receivingEmployee.updateCherry(receivedCherry);
            employeeRepository.save(receivingEmployee);

            Cherry cherry = Cherry.builder()
                    .getCherry(receivedCherry)
                    .cherryContent(contentGift)
                    .employee(receivingEmployee)  // cherry를 받는 사람의 아이디로 설정
                    .dailyWork(dailyWork)
                    .build();



            // Save Cherry entity
            cherryRepository.save(cherry);
            log.debug("cherry = {}", cherry);
            authService.updateAuthentication(employeeDetails.getUsername());

        }

        return "redirect:/";
    }

}
//            receivingEmployee.updateCherry(Integer.parseInt(praise));
//            employeeRepository.save(receivingEmployee);