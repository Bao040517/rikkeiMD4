package com.project.demo.Mapper;

import com.project.demo.DTO.Request.DepartmentDTO;
import com.project.demo.Entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department toEntity (DepartmentDTO departmentDTO) {
        return Department.builder()
                .name(departmentDTO.getName())
                .description(departmentDTO.getDescription())
                .build();
    }

    public DepartmentDTO toDTO (Department department) {
        return DepartmentDTO.builder()
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }
}
