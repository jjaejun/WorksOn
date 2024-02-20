package com.sh.workson.approval.service;

import com.sh.workson.approval.dto.*;
import com.sh.workson.approval.entity.Approval;
import com.sh.workson.approval.entity.ApprovalCooperation;
import com.sh.workson.approval.entity.ApprovalLine;
import com.sh.workson.approval.entity.Status;
import com.sh.workson.approval.repository.ApprovalCooperationRepository;
import com.sh.workson.approval.repository.ApprovalLineRepository;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.department.entity.Department;
import com.sh.workson.approval.repository.ApprovalRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.core.convert.TypeDescriptor.map;

@Service
@Transactional
@Slf4j
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private ApprovalCooperationRepository approvalCooperationRepository;

    @Autowired
    private ApprovalLineRepository approvalLineRepository;

    @Autowired
    private ModelMapper modelMapper;

    // 연차 문서함 컨버트
    private ApprovalHomeLeaveDto convertToApprovalHomeLeaveDto(Approval approval) {
        ApprovalHomeLeaveDto approvalHomeDto = modelMapper.map(approval, ApprovalHomeLeaveDto.class);

        // emp_id 부분
        approvalHomeDto.setEmpId(
                Optional.ofNullable(approval.getEmployee())
                        .map((employee) -> employee.getName())
                        .orElse(null)
        );

        // emp_receives_id
        approvalHomeDto.setEmpReId(
                Optional.ofNullable(approval.getEmployeeRe())
                        .map((employee) -> employee.getName())
                        .orElse(null)
        );

        // employee.department.id - 추가사항
        approvalHomeDto.setDeptId(
                String.valueOf(Optional.ofNullable(approval.getEmployee())
                        .map(Employee::getDepartment)  // 여기서 getDepartment()가 null인지 확인
                        .map(Department::getId)  // 이후 getId()를 호출하려고 함
                        .orElse(null))
        );


        // name 부분
        approvalHomeDto.setName(
                Optional.ofNullable(approval.getApprovalLeave())
                        .map(approvalLeaves -> approvalLeaves.getName())
                        .orElse(null)
        );

        // title 부분 수정
        approvalHomeDto.setTitle(
                Optional.ofNullable(approval.getApprovalLeave())
                        .map(approvalLeave -> approvalLeave.getTitle())
                        .orElse(null)
        );

        return approvalHomeDto;
    }


    // 비품 문서함 컨버트
    private ApprovalHomeEquipmentDto convertToApprovalHomeEquipmentDto(Approval approval) {
        ApprovalHomeEquipmentDto approvalHomeDto = modelMapper.map(approval, ApprovalHomeEquipmentDto.class);

        // approval_id 부분
        approvalHomeDto.setEmpId(
                Optional.ofNullable(approval.getEmployee())
                        .map((employee) -> employee.getName())
                        .orElse(null)
        );

        // emp_receives_id
        approvalHomeDto.setEmpReId(
                Optional.ofNullable(approval.getEmployeeRe())
                        .map((employee) -> employee.getName())
                        .orElse(null)
        );

        // employee.department.id - 추가사항
        approvalHomeDto.setDeptId(
                String.valueOf(Optional.ofNullable(approval.getEmployee())
                        .map(Employee::getDepartment)  // 여기서 getDepartment()가 null인지 확인
                        .map(Department::getId)  // 이후 getId()를 호출하려고 함
                        .orElse(null))
        );

        // name 부분
        approvalHomeDto.setName(
                Optional.ofNullable(approval.getApprovalEquipment())
                        .map(approvalEquipment -> approvalEquipment.getName())
                        .orElse(null)
        );

        // title 부분 수정
        approvalHomeDto.setTitle(
                Optional.ofNullable(approval.getApprovalEquipment())
                        .map(approvalEquipment -> approvalEquipment.getTitle())
                        .orElse(null)
        );

        return approvalHomeDto;
    }


    // 협조 문서함 컨버트
    private ApprovalHomeCooperationDto convertToApprovalHomeCooperationDto(Approval approval) {
        ApprovalHomeCooperationDto approvalHomeDto = modelMapper.map(approval, ApprovalHomeCooperationDto.class);

        // approval_id 부분
        approvalHomeDto.setEmpId(
                Optional.ofNullable(approval.getEmployee())
                        .map((employee) -> employee.getName())
                        .orElse(null)
        );

        // emp_receives_id
        approvalHomeDto.setEmpReId(
                Optional.ofNullable(approval.getEmployeeRe())
                        .map((employee) -> employee.getName())
                        .orElse(null)
        );

        // employee.department.id - 추가사항
        approvalHomeDto.setDeptId(
                String.valueOf(Optional.ofNullable(approval.getEmployee())
                        .map(Employee::getDepartment)  // 여기서 getDepartment()가 null인지 확인
                        .map(Department::getId)  // 이후 getId()를 호출하려고 함
                        .orElse(null))
        );

        // name 부분
        approvalHomeDto.setName(
                Optional.ofNullable(approval.getApprovalCooperation())
                        .map(approvalCooperation -> approvalCooperation.getName())
                        .orElse(null)
        );

        // title 부분 수정
        approvalHomeDto.setTitle(
                Optional.ofNullable(approval.getApprovalCooperation())
                        .map(approvalCooperation -> approvalCooperation.getTitle())
                        .orElse(null)
        );

        return approvalHomeDto;
    }


    // 발송 문서함 조회
    public List<ApprovalHomeLeaveDto> findAllLeave(Long id) {
        List<Approval> approvals = approvalRepository.findAllLeave(id);
        List<ApprovalHomeLeaveDto> approvalHomeLeaveDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeLeaveDtos.add(convertToApprovalHomeLeaveDto(a));
        }

        return approvalHomeLeaveDtos;
    }


    public List<ApprovalHomeEquipmentDto> findAllEquipment(Long id) {
        List<Approval> approvals = approvalRepository.findAllEquipment(id);
        List<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeEquipmentDtos.add(convertToApprovalHomeEquipmentDto(a));
        }

        return approvalHomeEquipmentDtos;
    }

    public List<ApprovalHomeCooperationDto> findAllCooperation(Long id) {
        List<Approval> approvals = approvalRepository.findAllCooperation(id);
        List<ApprovalHomeCooperationDto> approvalHomeCooperationDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeCooperationDtos.add(convertToApprovalHomeCooperationDto(a));
        }

        return approvalHomeCooperationDtos;
    }

    // 수신 문서함 조회
    public List<ApprovalHomeLeaveDto> findReLeave(Long id) {
        List<Approval> approvals = approvalRepository.findReLeave(id);
        List<ApprovalHomeLeaveDto> approvalHomeLeaveDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeLeaveDtos.add(convertToApprovalHomeLeaveDto(a));
        }

        return approvalHomeLeaveDtos;
    }

    public List<ApprovalHomeEquipmentDto> findReEquipment(Long id) {
        List<Approval> approvals = approvalRepository.findReEquipment(id);
        List<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeEquipmentDtos.add(convertToApprovalHomeEquipmentDto(a));
        }

        return approvalHomeEquipmentDtos;
    }

    public List<ApprovalHomeCooperationDto> findReCooperation(Long id) {
        List<Approval> approvals = approvalRepository.findReCooperation(id);
        List<ApprovalHomeCooperationDto> approvalHomeCooperationDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeCooperationDtos.add(convertToApprovalHomeCooperationDto(a));
        }

        return approvalHomeCooperationDtos;
    }

    public Page<ApprovalHomeLeaveDto> findCheckLeave(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findCheckLeave(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
    }

    public Page<ApprovalHomeEquipmentDto> findCheckEquipment(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findCheckEquipment(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

    public Page<ApprovalHomeCooperationDto> findCheckCooperation(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findCheckCooperation(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
    }

    public Page<ApprovalHomeLeaveDto> findProceedingLeave(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findProceedingLeave(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
    }

    public Page<ApprovalHomeEquipmentDto> findProceedingEquipment(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findProceedingEquipment(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

    public Page<ApprovalHomeCooperationDto> findProceedingCooperation(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findProceedingCooperation(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
    }

    public Page<ApprovalHomeLeaveDto> findReceiveLeave(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findReceiveLeave(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
    }

    public Page<ApprovalHomeEquipmentDto> findReceiveEquipment(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findReceiveEquipment(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

    public Page<ApprovalHomeCooperationDto> findReceiveCooperation(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findReceiveCooperation(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
    }

    public List<ApprovalHomeLeaveDto> findReceptionLeave(Long deptId, Long id) {
        List<Approval> approvals = approvalRepository.findReceptionLeave(deptId, id);
        List<ApprovalHomeLeaveDto> approvalHomeLeaveDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeLeaveDtos.add(convertToApprovalHomeLeaveDto(a));
        }

        return approvalHomeLeaveDtos;
    }

    public List<ApprovalHomeEquipmentDto> findReceptionEquipment(Long deptId, Long id) {
        List<Approval> approvals = approvalRepository.findReceptionEquipment(deptId, id);
        List<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeEquipmentDtos.add(convertToApprovalHomeEquipmentDto(a));
        }

        return approvalHomeEquipmentDtos;
    }

    public List<ApprovalHomeCooperationDto> findReceptionCooperation(Long deptId, Long id) {
        List<Approval> approvals = approvalRepository.findReceptionCooperation(deptId, id);
        List<ApprovalHomeCooperationDto> approvalHomeCooperationDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeCooperationDtos.add(convertToApprovalHomeCooperationDto(a));
        }

        return approvalHomeCooperationDtos;
    }

    public List<ApprovalHomeLeaveDto> findWaitLeave(Long id) {
        List<Approval> approvals = approvalRepository.findWaitLeave(id);
        List<ApprovalHomeLeaveDto> approvalHomeLeaveDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeLeaveDtos.add(convertToApprovalHomeLeaveDto(a));
        }

        return approvalHomeLeaveDtos;
    }

    public List<ApprovalHomeEquipmentDto> findWaitEquipment(Long id) {
        List<Approval> approvals = approvalRepository.findWaitEquipment(id);
        List<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeEquipmentDtos.add(convertToApprovalHomeEquipmentDto(a));
        }

        return approvalHomeEquipmentDtos;
    }

    public List<ApprovalHomeCooperationDto> findWaitCooperation(Long id) {
        List<Approval> approvals = approvalRepository.findWaitCooperation(id);
        List<ApprovalHomeCooperationDto> approvalHomeCooperationDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeCooperationDtos.add(convertToApprovalHomeCooperationDto(a));
        }

        return approvalHomeCooperationDtos;
    }

    public List<ApprovalHomeLeaveDto> findExceptedLeave(Long id) {
        List<Approval> approvals = approvalRepository.findExceptedLeave(id);
        List<ApprovalHomeLeaveDto> approvalHomeLeaveDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeLeaveDtos.add(convertToApprovalHomeLeaveDto(a));
        }

        return approvalHomeLeaveDtos;
    }

    public List<ApprovalHomeEquipmentDto> findExceptedEquipment(Long id) {
        List<Approval> approvals = approvalRepository.findExceptedEquipment(id);
        List<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeEquipmentDtos.add(convertToApprovalHomeEquipmentDto(a));
        }

        return approvalHomeEquipmentDtos;
    }

    public List<ApprovalHomeCooperationDto> findExceptedCooperation(Long id) {
        List<Approval> approvals = approvalRepository.findExceptedCooperation(id);
        List<ApprovalHomeCooperationDto> approvalHomeCooperationDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeCooperationDtos.add(convertToApprovalHomeCooperationDto(a));
        }

        return approvalHomeCooperationDtos;
    }

    // 연차 상세 페이지 조회
    public IApprovalLeave findLeaveDetailById(Long id) {
        IApprovalLeave iApprovalLeave = approvalRepository.findLeaveDetailById(id);
        return iApprovalLeave;
    }

    // 비품 상세 페이지 조회
    public IApprovalEquipment findEquipmentDetailById(Long id) {
        IApprovalEquipment iApprovalEquipment = approvalRepository.findEquipmentDetailById(id);
        return iApprovalEquipment;
    }

    // 협조 상세 페이지 조회
    public IApprovalCooperation findCooperationDetailById(Long id) {
        IApprovalCooperation iApprovalCooperation = approvalRepository.findCooperationDetailById(id);
        return iApprovalCooperation;
    }


    // 임시 저장함 list 조회
    public List<ApprovalHomeLeaveDto> findTemporaryLeave(Long id) {
        List<Approval> approvals = approvalRepository.findTempoeraryLeave(id);
        List<ApprovalHomeLeaveDto> approvalHomeLeaveDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeLeaveDtos.add(convertToApprovalHomeLeaveDto(a));
        }

        return approvalHomeLeaveDtos;
    }

    public List<ApprovalHomeEquipmentDto> findTemporaryEquipment(Long id) {
        List<Approval> approvals = approvalRepository.findTemporaryEquipment(id);
        List<ApprovalHomeEquipmentDto> approvalHomeEquipmentDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeEquipmentDtos.add(convertToApprovalHomeEquipmentDto(a));
        }

        return approvalHomeEquipmentDtos;
    }

    public List<ApprovalHomeCooperationDto> findTemporaryCooperation(Long id) {
        List<Approval> approvals = approvalRepository.findTemporaryCooperation(id);
        List<ApprovalHomeCooperationDto> approvalHomeCooperationDtos = new ArrayList<>();

        for (Approval a: approvals) {
            approvalHomeCooperationDtos.add(convertToApprovalHomeCooperationDto(a));
        }

        return approvalHomeCooperationDtos;
    }

    // 연차 문서함 조회
    public Page<ApprovalHomeLeaveDto> findLeave(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findLeave(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
    }

    // 비품 문서함 조회
    public Page<ApprovalHomeEquipmentDto> findEquipment(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findEquipment(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

    // 협조 문서함 조회
    public Page<ApprovalHomeCooperationDto> findCooperation(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findCooperation(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
    }


    /**
     * mapper로 createCooperationDto -> Approval로 변환
     * - 안될 시 mapper를 approval_cooperation으로 설정해보자
     */
    @Transactional
    public void createCooperationApproval(CreateCooperationDto createCooperationDto) {
//        ApprovalCooperation approvalCooperation = modelMapper.map(createCooperationDto,ApprovalCooperation.class);
//        log.debug("approvalCooperation = {}", approvalCooperation);
//
//        approvalCooperationRepository.save(approvalCooperation);
//
//        Approval approval = Approval.builder()
//                .employee(Employee.builder().build())
//                .build();
        ApprovalCooperation approvalCooperation = ApprovalCooperation.builder()
                .name("업무 협조")
                .cooperationDept(createCooperationDto.getCooperationDept())
                .title(createCooperationDto.getTitle())
                .content(createCooperationDto.getContent())
                .StartDate(createCooperationDto.getStartDate())
                .EndDate(createCooperationDto.getEndDate())
                .build();

        approvalCooperationRepository.save(approvalCooperation);

        Approval approval = Approval.builder()
                .approvalEndDate(createCooperationDto.getApprovalEndDate())
                .status(Status.대기)
                .employee(Employee.builder().id(createCooperationDto.getEmpId()).build())
                .approvalCooperation(approvalCooperation)
                .build();
        approvalRepository.save(approval);

        // mapper가 처리하지 못하는 cooperationDept, title, content, startDate, endDate
        // approval_cooperation으로 처리


        // approverId approver_line에 처리
        List<ApprovalLine> approvalLines = new ArrayList<>();
            for(Long a : createCooperationDto.getApproverId()) {
                approvalLines.add(ApprovalLine.builder().approverId(a).approval(approval).status(Status.대기).build());
            }
        approvalLineRepository.saveAll(approvalLines);

//        List<ApprovalLine> approvalLines = new ArrayList<>();
//
//        for (Long ApprovalId : createCooperationDto.getApprovalLineList()) {
//            ApprovalLine approvalLine = ApprovalLine.builder()
//                    .approverId(approverId)
//                    .build();
//
//            approvalLines.add(approvalLine);
//        }
//
//        approvalRepository.saveAll(approvalLines);



    }



}
