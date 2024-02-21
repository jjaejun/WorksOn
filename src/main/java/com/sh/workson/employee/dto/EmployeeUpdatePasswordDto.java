package com.sh.workson.employee.dto;

import com.sh.workson.authority.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdatePasswordDto {
    private Long id;
    private String password;
    private Authority authority;
}
