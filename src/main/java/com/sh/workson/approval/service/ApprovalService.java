package com.sh.workson.approval.service;

import com.sh.workson.approval.dto.*;
import com.sh.workson.approval.entity.Approval;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.department.entity.Department;
import com.sh.workson.approval.repository.ApprovalRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Page<ApprovalHomeLeaveDto> findAllLeave(Long id,Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findAllLeave(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
//        return approvalRepository.findAllLeave(pageable);
    }



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


    public Page<ApprovalHomeEquipmentDto> findAllEquipment(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findAllEquipment(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

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

    public Page<ApprovalHomeCooperationDto> findAllCooperation(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findAllCooperation(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
    }

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


    public Page<ApprovalHomeLeaveDto> findTemporaryLeave(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findTempoeraryLeave(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
    }

    public Page<ApprovalHomeEquipmentDto> findTemporaryEquipment(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findTemporaryEquipment(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

    public Page<ApprovalHomeCooperationDto> findTemporaryCooperation(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findTemporaryCooperation(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
    }

    public Page<ApprovalHomeLeaveDto> findReLeave(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findReLeave(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
    }

    public Page<ApprovalHomeEquipmentDto> findReEquipment(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findReEquipment(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

    public Page<ApprovalHomeCooperationDto> findReCooperation(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findReCooperation(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
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

    public Page<ApprovalHomeLeaveDto> findReceptionLeave(Long deptId, Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findReceptionLeave(deptId, id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
    }

    public Page<ApprovalHomeEquipmentDto> findReceptionEquipment(Long deptId, Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findReceptionEquipment(deptId, id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

    public Page<ApprovalHomeCooperationDto> findReceptionCooperation(Long deptId, Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findReceptionCooperation(deptId, id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
    }

    public Page<ApprovalHomeLeaveDto> findWaitLeave(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findWaitLeave(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
    }

    public Page<ApprovalHomeEquipmentDto> findWaitEquipment(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findWaitEquipment(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

    public Page<ApprovalHomeCooperationDto> findWaitCooperation(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findWaitCooperation(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
    }

    public Page<ApprovalHomeLeaveDto> findExceptedLeave(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findExceptedLeave(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
    }

    public Page<ApprovalHomeEquipmentDto> findExceptedEquipment(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findExceptedEquipment(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeEquipmentDto(approval));
    }

    public Page<ApprovalHomeCooperationDto> findExceptedCooperation(Long id, Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findExceptedCooperation(id, pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeCooperationDto(approval));
    }

    public IApprovalLeave findLeaveDetailById(Long id) {
        IApprovalLeave iApprovalLeave = approvalRepository.findLeaveDetailById(id);
        return iApprovalLeave;
    }

    public IApprovalEquipment findEquipmentDetailById(Long id) {
        IApprovalEquipment iApprovalEquipment = approvalRepository.findEquipmentDetailById(id);
        return iApprovalEquipment;
    }

    public IApprovalCooperation findCooperationDetailById(Long id) {
        IApprovalCooperation iApprovalCooperation = approvalRepository.findCooperationDetailById(id);
        return iApprovalCooperation;
    }
}
