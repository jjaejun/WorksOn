package com.sh.workson.project.service;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.project.dto.ProjectCommentCreateDto;
import com.sh.workson.project.dto.ProjectCommentDeleteDto;
import com.sh.workson.project.dto.ProjectCommentDetailDto;
import com.sh.workson.project.entity.ProjectComment;
import com.sh.workson.project.entity.ProjectCommentType;
import com.sh.workson.project.repository.ProjectCommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectCommentService {

    @Autowired
    private ProjectCommentRepository projectCommentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ProjectCommentDetailDto createProjectComment(ProjectCommentCreateDto commentCreateDto) {
        ProjectComment comment = projectCommentRepository.save(projectCommentCreateDtoConvertToProjectComment(commentCreateDto));
        ProjectCommentDetailDto projectCommentDetailDto;
        return modelMapper.map(comment, ProjectCommentDetailDto.class);
    }

    private ProjectComment projectCommentCreateDtoConvertToProjectComment(ProjectCommentCreateDto commentCreateDto) {
        ProjectComment comment = ProjectComment.builder()
                .content(commentCreateDto.getContent())
                .parentCommentId(commentCreateDto.getParentCommentId())
                .commentLevel(commentCreateDto.getCommentLevel())
                .type(ProjectCommentType.valueOf(commentCreateDto.getType()))
                .typeId(commentCreateDto.getTypeId())
                .employee(Employee.builder().id(commentCreateDto.getEmpId()).build())
                .build();
        return comment;
    }


    public void deleteProjectComment(ProjectCommentDeleteDto commentDeleteDto) {
        projectCommentRepository.deleteById(commentDeleteDto.getId());
    }

    public List<ProjectCommentDetailDto> findByTypeId(Long id, ProjectCommentType projectCommentType) {
        List<ProjectComment> comments = projectCommentRepository.findByTypeId(id, projectCommentType);
        return comments.stream().map(projectComment -> modelMapper.map(projectComment, ProjectCommentDetailDto.class)).toList();
    }
}
