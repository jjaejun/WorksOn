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
//                Optional.ofNullable(approvalLeave.getApprovalLeaves())
//                        .map(approvalLeave -> approvalLeave.get(0).getName())
//                        .orElse(null)
//        );
//
//        // title 부분
//        approvalHomeDto.setTitle(
//                Optional.ofNullable(approval.getApprovalLeaves())
//                        .map(approvalLeave -> approvalLeave.get(0).getName())
//                        .orElse(null)
//        );

        return approvalHomeDto;
    }



//    public ApprovalHomeLeaveDto convertToApprovalHomeDto(Long approvalTypeId) {
//        Approval approval = approvalRepository.findById(approvalTypeId).orElse(null);
//
//        if (approval == null) {
//            // 처리할 내용 추가
//        }
//
//        ApprovalHomeLeaveDto approvalHomeDto = modelMapper.map(approval, ApprovalHomeLeaveDto.class);
//
//        // approval_id 부분
//        approvalHomeDto.setEmpId(
//                Optional.ofNullable(approval.getEmployee())
//                        .map((employee) -> employee.getName())
//                        .orElse(null)
//        );
//
//        // approval_leave 정보 가져오기
//        List<ApprovalLeave> approvalLeaves = approvalRepository.findApprovalLeavesByApprovalId(approvalTypeId);
//        if (!approvalLeaves.isEmpty()) {
//            ApprovalLeave firstApprovalLeave = approvalLeaves.get(0);
//
//            approvalHomeDto.setName(firstApprovalLeave.getName());
//            approvalHomeDto.setTitle(firstApprovalLeave.getTitle());
//        }
//
//        return approvalHomeDto;
//    }

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
