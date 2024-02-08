package com.sh.workson.project.repository;

import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.department.entity.Department;
import com.sh.workson.department.repository.DepartmentRepository;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.entity.WorkStatus;
import com.sh.workson.employee.repository.EmployeeRepository;
import com.sh.workson.position.entity.Position;
import com.sh.workson.position.repository.PositionRepository;
import com.sh.workson.project.dto.ProjectListDto;
import com.sh.workson.project.entity.Project;
import com.sh.workson.project.entity.ProjectEmployee;
import com.sh.workson.project.entity.ProjectRole;
import com.sh.workson.project.entity.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//@DataJpaTest // test에 관련된 bean만 로드하기 때문에 passwordencoder를 로드하지 못함
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED) // 트랜젝션 사용 X 설정
@SpringBootTest
class ProjectRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ProjectEmployeeRepository projectEmployeeRepository;
    @Autowired
    S3FileService s3FileService;

    @DisplayName("모든 프로젝트 및 각 프로젝트에 소속된 모든 employee 조회할 수 있다.")
    @Test
    void test1() {
        // given
        // select * from project p left join project_employee e on p.id = e.project_id
        // when
        final int pageSize = 5;
        Pageable pageable = PageRequest.of(0, pageSize);

        Page<Project> projects = projectRepository.findAll(pageable);
        // then
        assertThat(projects).isNotEmpty();
        assertThat(projects).allSatisfy(project -> {
            assertThat(project.getProjectEmployees()).isNotEmpty();
            assertThat(project.getTitle()).isNotNull();
        });
    }

    @DisplayName("프로젝트에 소속된 모든 employee 조회할 수 있다.")
    @Test
    void test2() {
        // given
        // select * from project p left join project_employee e on p.id = e.project_id
        // prject_id = 101L
        // when
        List<Project> projects = projectRepository.findByProjectId(101L);
        // then
        assertThat(projects).isNotEmpty();
        assertThat(projects).allSatisfy(project -> {
            assertThat(project.getProjectEmployees()).isNotEmpty();
            assertThat(project.getTitle()).isNotNull();
        });

    }
    @DisplayName("프로젝트를 생성할 수 있다.")
    @Test
    void test3() {
        // given
        // when
        // then
    }
    @DisplayName("프로젝트 정보를 업데이트할 수 있다.")
    @Test
    void test4() {
        // given
        // when
        // then
    }
    @DisplayName("프로젝트를 삭제할 수 있다.")
    @Test
    void test5() {
        // given
        // when
        // then
    }


    @DisplayName("내가 참여중인 프로젝트를 조회할 수 있다.")
    @Test
    void test6() {
        final int pageSize = 5;
        Pageable pageable = PageRequest.of(0, pageSize);

        Long id = 151L;
        Page<Project> projects = projectRepository.findByEmpId(id, pageable);
        System.out.println(projects);
    }

    @DisplayName("내가 생성한 프로젝트를 조회할 수 있다.")
    @Test
    void test7() {
        Long id = 351L;
        List<Project> projects = projectRepository.findByOwnerId(id);
        System.out.println(projects);
    }

    @DisplayName("프로젝트를 생성할 수 있습니다. (사원 등록 및 첨부파일 등록)")
    @Test
    void test8() {
        Project project = Project.builder()
                .title("프로젝트 테스트")
                .status(Status.ING)
                .build();
        projectRepository.save(project);
        List<ProjectEmployee> projectEmployees = new ArrayList<>();
        projectEmployees.add(ProjectEmployee.builder().empId(51L).projectId(project.getId()).role(ProjectRole.CREATE).build());
        projectEmployees.add(ProjectEmployee.builder().empId(101L).projectId(project.getId()).role(ProjectRole.CREATE).build());
        projectEmployees.add(ProjectEmployee.builder().empId(151L).projectId(project.getId()).role(ProjectRole.CREATE).build());
        projectEmployeeRepository.saveAll(projectEmployees);
    }

}