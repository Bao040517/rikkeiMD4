package com.project.demo.Service;

import com.project.demo.DTO.Request.EmployeeCreateDTO;
import com.project.demo.Entity.Employee;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {
    Employee createEmployee(EmployeeCreateDTO employeeCreateDTO);
    String uploadAvatar(Long id, MultipartFile file);
}
