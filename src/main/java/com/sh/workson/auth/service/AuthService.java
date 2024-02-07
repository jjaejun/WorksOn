package com.sh.workson.auth.service;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService implements UserDetailsService {
    @Autowired
    private final EmployeeService employeeService;

    public AuthService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 이메일로 로그인하니까 email로 매개변수명 작성
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeService.findByEmail(username);
        log.debug("employee = {}", employee);

        if(employee == null){
            throw new UsernameNotFoundException(username);
        }

        return new EmployeeDetails(employee);
    }

//    public void updateAuthentication(String username) {
//        Employee newEmployee = employeeService.findByEmail(username);
//        EmployeeDetails newEmployeeDetails = new EmployeeDetails(newEmployee);
//        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
//                newEmployeeDetails,
//                newEmployeeDetails.getPassword(),
//                newEmployeeDetails.getAuthorities()
//        );
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(newAuthentication);
//    }

//    public void deleteAuthentication(String username) {
//        Employee employee = employeeService.findByEmail(username);
//
//    }
}
