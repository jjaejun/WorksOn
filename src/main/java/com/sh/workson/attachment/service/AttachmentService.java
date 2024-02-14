package com.sh.workson.attachment.service;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.dto.AttachmentDetailDto;
import com.sh.workson.attachment.dto.ProjectAttachmentCreateDto;
import com.sh.workson.attachment.dto.ProjectAttachmentDetailDto;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.repository.AttachmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AttachmentService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AttachmentRepository attachmentRepository;

    public void createAttachment(AttachmentCreateDto attachmentCreateDto) {
        attachmentRepository.save(convertToAttachment(attachmentCreateDto));
    }

    private Attachment convertToAttachment(AttachmentCreateDto attachmentCreateDto) {
        return modelMapper.map(attachmentCreateDto, Attachment.class);
    }


    public AttachmentDetailDto findById(Long id) {
        return attachmentRepository.findById(id)
                .map((this::convertToAttachmentDetailDto))
                .orElseThrow();
    }

    private AttachmentDetailDto convertToAttachmentDetailDto(Attachment attachment) {
        return modelMapper.map(attachment, AttachmentDetailDto.class);
    }

    public ProjectAttachmentDetailDto findByProjectId(Long id) {
        return attachmentRepository.findById(id)
                .map(this::convertToProjectAttachmentDetailDto)
                .orElseThrow();
    }

    private ProjectAttachmentDetailDto convertToProjectAttachmentDetailDto(Attachment attachment) {
        ProjectAttachmentDetailDto projectAttachmentDetailDto = modelMapper.map(attachment, ProjectAttachmentDetailDto.class);
        projectAttachmentDetailDto.setEmpDeptName(attachment.getEmployee().getDepartment().getName());
        projectAttachmentDetailDto.setEmpName(attachment.getEmployee().getName());
        projectAttachmentDetailDto.setEmpPositionName(attachment.getEmployee().getPosition().getName());
        return projectAttachmentDetailDto;
    }

    public ProjectAttachmentDetailDto createProjectAttachment(ProjectAttachmentCreateDto attachmentCreateDto) {
        Attachment attachment =  attachmentRepository.save(modelMapper.map(attachmentCreateDto, Attachment.class));
        return convertToProjectAttachmentDetailDto(attachment);
    }
}
