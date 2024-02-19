package com.sh.workson.project.service;

import com.sh.workson.employee.dto.EmployeeTaskDetailDto;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.project.dto.*;
import com.sh.workson.project.entity.Project;
import com.sh.workson.project.entity.Task;
import com.sh.workson.project.entity.TaskStatus;
import com.sh.workson.project.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;

    public TaskListDto convertToTaskListDto(Task task){
        return TaskListDto.builder()
                .id(task.getId())
                .name(task.getName())
                .priority(task.getPriority())
                .status(task.getStatus().toString())
                .empId(task.getEmployee().getId())
                .empName(task.getEmployee().getName())
                .empProfileUrl(task.getEmployee().getProfileUrl())
                .positionName(task.getEmployee().getPosition().getName())
                .build();
    }
    public TaskListDto afterInsertCovertToTaskListDto(Task task){
        return TaskListDto.builder()
                .id(task.getId())
                .name(task.getName())
                .priority(task.getPriority())
                .status(task.getStatus().toString())
                .empId(task.getEmployee().getId())
                .build();
    }
    public TaskListDto createTask(TaskCreateDto taskCreateDto) {
        Task task = taskRepository.save(convertToTask(taskCreateDto));
        return afterInsertCovertToTaskListDto(task);
    }

    private Task convertToTask(TaskCreateDto taskCreateDto) {
        Task task = Task.builder()
                .name(taskCreateDto.getName())
                .content(taskCreateDto.getContent())
                .priority(taskCreateDto.getPriority())
                .startAt(taskCreateDto.getStartAt())
                .endAt(taskCreateDto.getEndAt())
                .build();
        task.setOwner(Employee.builder().id(taskCreateDto.getTaskOwnerId()).build());
        task.setEmployee(Employee.builder().id(taskCreateDto.getTaskEmpId()).build());
        task.setProject(Project.builder().id(taskCreateDto.getProjectId()).build());
        TaskStatus status = null;
        switch (taskCreateDto.getStatus()){
            case "To do": status = TaskStatus.TODO ; break;
            case "In progress": status = TaskStatus.INPROGRESS ; break;
            case "Done": status = TaskStatus.DONE ; break;
        }
        task.setStatus(status);
        return task;
    }

    public void updateTask(TaskUpdateDto taskUpdateDto) {
        Task task = taskRepository.findById(taskUpdateDto.getId()).orElseThrow();
        task.setStatus(TaskStatus.valueOf(taskUpdateDto.getStatus()));
        taskRepository.save(task);
    }

    public TaskDetailDto findById(Long id) {
        return convertToTaskDetailDto(taskRepository.findById(id).orElse(null));
    }

    public TaskDetailDto convertToTaskDetailDto(Task task){
        TaskDetailDto taskDetailDto = modelMapper.map(task, TaskDetailDto.class);
        taskDetailDto.setEmployee(EmployeeTaskDetailDto.builder()
                .id(task.getEmployee().getId())
                .profileUrl(task.getEmployee().getProfileUrl())
                .name(task.getEmployee().getName())
                .positionName(task.getEmployee().getPosition().getName())
                .deptName(task.getEmployee().getDepartment().getName())
                .build());
        taskDetailDto.setOwner(EmployeeTaskDetailDto.builder()
                .id(task.getOwner().getId())
                .profileUrl(task.getOwner().getProfileUrl())
                .name(task.getOwner().getName())
                .positionName(task.getOwner().getPosition().getName())
                .deptName(task.getOwner().getDepartment().getName())
                .build());
        taskDetailDto.setProject(ProjectTaskDetailDto.builder()
                        .id(task.getProject().getId())
                        .title(task.getProject().getTitle())
                .build());
        return taskDetailDto;
    }

    public void updateTaskDetail(TaskDetailUpdateDto taskDetailUpdateDto) {
        Task task = taskDetailUpdateDtoCovertToTask(taskDetailUpdateDto);
    }

    private Task taskDetailUpdateDtoCovertToTask(TaskDetailUpdateDto taskDetailUpdateDto) {
        Task task = taskRepository.findById(taskDetailUpdateDto.getId()).orElse(null);

        task.setStatus(TaskStatus.valueOf(taskDetailUpdateDto.getStatus()));
        task.setEmployee(Employee.builder().id(taskDetailUpdateDto.getEmpId()).build());
        task.setContent(taskDetailUpdateDto.getContent());
        task.setStartAt(taskDetailUpdateDto.getStartAt());
        task.setEndAt(taskDetailUpdateDto.getEndAt());
        task.setPriority(taskDetailUpdateDto.getPriority());
        return task;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Page<TaskDetailDto> findAllMyTask(Long id, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllMyTask(id, pageable);
        return tasks.map(project -> convertToTaskDetailDto(project));
    }
}
