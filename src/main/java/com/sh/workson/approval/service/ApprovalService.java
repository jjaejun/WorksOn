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


    public Page<ApprovalHomeLeaveDto> findAllLeave(Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findAllLeave(pageable);
        return approvalPage.map((approval) -> convertToApprovalHomeLeaveDto(approval));
//        return approvalRepository.findAllLeave(pageable);
    }



    private ApprovalHomeLeaveDto convertToApprovalHomeLeaveDto(Approval approval) {
        ApprovalHomeLeaveDto approvalHomeDto = modelMapper.map(approval, ApprovalHomeLeaveDto.class);

        // approval_id 부분
        approvalHomeDto.setEmpId(
                Optional.ofNullable(approval.getEmployee())
                        .map((employee) -> employee.getName())
                        .orElse(null)
        );

//        // name 부분
//        approvalHomeDto.setName(
//                Optional.ofNullable(approval.getApprovalFormId())
//                        .map(approvalLeaves -> approvalLeaves.getName())
//                        .orElse(null)
//        );
//
//        // title 부분 수정
//        approvalHomeDto.setTitle(
//                Optional.ofNullable(approval.getApprovalLeaves())
//                        .map(approvalLeave -> approvalLeave.get(0).getTitle())
//                        .orElse(null)
//        );

        return approvalHomeDto;
    }


    public Page<ApprovalHomeEquipmentDto> findAllEquipment(Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findAllEquipment(pageable);
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

        return approvalHomeDto;
    }

    public Page<ApprovalHomeCooperationDto> findAllCooperation(Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findAllCooperation(pageable);
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

        return approvalHomeDto;
    }


}
