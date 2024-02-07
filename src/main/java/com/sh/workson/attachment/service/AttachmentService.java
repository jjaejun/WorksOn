package com.sh.workson.attachment.service;

import com.sh.app.attachment.dto.AttachmentCreateDto;
import com.sh.app.attachment.dto.AttachmentDetailDto;
import com.sh.app.attachment.entity.Attachment;
import com.sh.app.attachment.repository.AttachmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void createAttachment(AttachmentCreateDto attachmentCreateDto) {
        attachmentRepository.save(convertToAttachment(attachmentCreateDto));
    }

    private Attachment convertToAttachment(AttachmentCreateDto attachmentCreateDto) {
        return modelMapper.map(attachmentCreateDto, Attachment.class);
    }

    public AttachmentDetailDto findById(Long id) {
        return attachmentRepository.findById(id)
                .map(this::convertToAttachmentDetailDto) // 람다표현식 -> 메소드 단축표현식 현재 객체(findById로 받아온 객체)에 이 메소드를 사용해주세요
                .orElseThrow();
    }

    private AttachmentDetailDto convertToAttachmentDetailDto(Attachment attachment) {
        return modelMapper.map(attachment, AttachmentDetailDto.class);
    }
}
