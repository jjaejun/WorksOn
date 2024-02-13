package com.sh.workson.approval.service;

import com.sh.workson.approval.dto.ApprovalHomeCooperationDto;
import com.sh.workson.approval.dto.ApprovalHomeEquipmentDto;
import com.sh.workson.approval.dto.ApprovalHomeLeaveDto;
import com.sh.workson.approval.entity.Approval;
import com.sh.workson.approval.entity.ApprovalLeave;
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
}
