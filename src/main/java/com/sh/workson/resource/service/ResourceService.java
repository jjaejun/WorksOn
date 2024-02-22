package com.sh.workson.resource.service;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.repository.AttachmentRepository;
import com.sh.workson.attachment.service.AttachmentService;
import com.sh.workson.resource.dto.ResourceAttachmentDto;
import com.sh.workson.resource.dto.ResourceCreateDto;
import com.sh.workson.resource.entity.Resource;
import com.sh.workson.resource.entity.Type;
import com.sh.workson.resource.repository.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AttachmentService attachmentService;

    public List<ResourceAttachmentDto> findByType(Type type) {
        List<Resource> resources = resourceRepository.findByType(type);
        List<ResourceAttachmentDto> resourceAttachmentDtos = new ArrayList<>();
        resources.forEach((resource) -> resourceAttachmentDtos.add(convertToResourceAttachmentDto(resource)));
        resourceAttachmentDtos.forEach((resourceAttachmentDto) -> resourceAttachmentDto.setAttachments(attachmentService.findByResourceId(resourceAttachmentDto.getId())));
        return resourceAttachmentDtos;
    }

    private ResourceAttachmentDto convertToResourceAttachmentDto(Resource resource) {
        ResourceAttachmentDto resourceAttachmentDto = modelMapper.map(resource, ResourceAttachmentDto.class);
        return resourceAttachmentDto;
    }

    public void createResource(ResourceCreateDto resourceCreateDto) {
        Resource resource = modelMapper.map(resourceCreateDto, Resource.class);
        resourceRepository.save(resource);

        if (!resourceCreateDto.getAttaches().isEmpty()) {
            for (AttachmentCreateDto attachmentCreateDto : resourceCreateDto.getAttaches()) {
                attachmentCreateDto.setBoardId(resource.getId());
                Attachment attachment = modelMapper.map(attachmentCreateDto, Attachment.class);
                attachmentRepository.save(attachment);
            }
        }
    }

    public List<Resource> findByOrderByType() {
        return resourceRepository.findByOrderByType();
    }

    public void deleteById(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }
}
