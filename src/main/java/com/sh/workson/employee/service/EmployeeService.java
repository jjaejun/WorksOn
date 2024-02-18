package com.sh.workson.employee.service;

import com.sh.workson.attachment.dto.ProfileAttachmentDto;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.authority.entity.Authority;
import com.sh.workson.authority.entity.RoleAuth;
import com.sh.workson.authority.service.AuthorityService;
import com.sh.workson.employee.dto.EmployeeSearchDto;
import com.sh.workson.employee.dto.IApprover;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    AuthorityService authorityService;


    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }



    /**
     * 민정
     */
    @Autowired
    private ModelMapper modelMapper;
    public void updateProfile(
            ProfileAttachmentDto profileAttachmentDto,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
    ) {

        Employee employee = employeeDetails.getEmployee();
        employee.setProfileKey(profileAttachmentDto.getKey());
        employee.setProfileUrl(profileAttachmentDto.getUrl());
        employee.setProfileOriginalName(profileAttachmentDto.getOriginalFilename());

        employeeRepository.save(employee);
    }

    public List<EmployeeSearchDto> findByName(String name){
        List<Employee> employees = employeeRepository.findByName(name);
        List<EmployeeSearchDto> employeeDtos = new ArrayList<>();
        for(Employee e : employees){
            employeeDtos.add(modelMapper.map(e, EmployeeSearchDto.class));
        }

        return employeeDtos;
    }


    /**
     * 재준
     */
    public String findNameByEmpId(Long employeeId) {
        return employeeRepository.findNameByEmpId(employeeId);
    }








    /**
     * 우진
     */
    public List<Employee> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return  employees;
    }

    public List<IApprover> findApprover(Long id) {
        List<IApprover> employees = employeeRepository.findApprover(id);
        return employees;
    }

    public Employee findLoginUser(Long id) {
        Employee loginUser = employeeRepository.findLoginUser(id);
        return loginUser;
    }






    /**
     * 민준
     */








    /**
     * 준희
     */









    /**
     * 무진
     */


    public Employee employeeCreate(Employee employee) {
        employeeRepository.save(employee);
        Authority authority = Authority.builder()
                .empId(employee.getId())  // authority id
                .name(RoleAuth.ROLE_EMP)
                .build();
        authorityService.createAuthority(authority);
        return employee;
    }


    public Employee checkEmailDuplicate(String email) {
        return employeeRepository.checkEmailDuplicate(email);
    }


}
