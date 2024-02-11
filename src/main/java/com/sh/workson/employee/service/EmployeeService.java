package com.sh.workson.employee.service;

import com.sh.workson.attachment.dto.ProfileAttachmentDto;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


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

    public ResponseEntity<?> findByName(String name){
        return employeeRepository.findByName(name);
    }



    /**
     * 재준
     */









    /**
     * 우진
     */







    /**
     * 민준
     */








    /**
     * 준희
     */









    /**
     * 무진
     */









}
