package com.project.demo.Service;

import com.project.demo.DTO.Request.DepartmentDTO;
import com.project.demo.DTO.Response.DepartmentResponse;
import com.project.demo.Entity.Department;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentDTO departmentDTO);
}
