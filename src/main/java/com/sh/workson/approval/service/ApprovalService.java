package com.sh.workson.approval.service;

import com.sh.workson.approval.dto.ApprovalHomeLeaveDto;
import com.sh.workson.approval.entity.Approval;
import com.sh.workson.approval.repository.ApprovalRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Page<ApprovalHomeLeaveDto> findAll(Pageable pageable) {
        Page<Approval> approvalPage = approvalRepository.findAll(pageable);
//        return approvalPage;
        return approvalPage.map((approval) -> convertToApprovalHomeDto(approval));
    }

    private ApprovalHomeLeaveDto convertToApprovalHomeDto(Approval approval) {
        ApprovalHomeLeaveDto approvalHomeDto = modelMapper.map(approval, ApprovalHomeLeaveDto.class);
//        // name 부분
//        approvalHomeDto.setName(
//                Optional.ofNullable(approval.getApprovalTypeId().getClass())
//                        .map(approvalLeaves -> approvalLeaves.getName())
//                        .orElse(null)
//        );
//
//        // title부분
//        approvalHomeDto.setTitle(
//                Optional.ofNullable(approval.getApprovalTypeId().getClass())
//                        .map(approvalLeaves -> approvalLeaves.getTitle())
//                        .orElse(null)
//        );
        // approval_id 부분
        approvalHomeDto.setEmpId(
                Optional.ofNullable(approval.getEmployee())
                        .map((employee) -> employee.getName())
                        .orElse(null)
        );
        return approvalHomeDto;
    }
}
