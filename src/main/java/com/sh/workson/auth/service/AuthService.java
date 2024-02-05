package com.sh.workson.auth.service;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService implements UserDetailsService {
    @Autowired
    private EmployeeService employeeService;

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
}
