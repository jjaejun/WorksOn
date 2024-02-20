package com.sh.workson.resource.service;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.repository.AttachmentRepository;
import com.sh.workson.resource.dto.ResourceCreateDto;
import com.sh.workson.resource.entity.Resource;
import com.sh.workson.resource.entity.Type;
import com.sh.workson.resource.repository.ResourceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Resource> findByType(Type type) {
        return resourceRepository.findByType(type);
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
