package com.project.demo.Service.Impl;

import com.project.demo.DTO.Request.DepartmentDTO;
import com.project.demo.DTO.Response.DepartmentResponse;
import com.project.demo.Entity.Department;
import com.project.demo.Mapper.DepartmentMapper;
import com.project.demo.Repository.DepartmentRepository;
import com.project.demo.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public DepartmentResponse createDepartment(DepartmentDTO departmentDTO) {

        Department department = departmentMapper.toEntity(departmentDTO);

        Department savedDepartment = departmentRepository.save(department);

        return DepartmentResponse.builder()
                .id(savedDepartment.getId())
                .name(savedDepartment.getName())
                .description(savedDepartment.getDescription())
                .build();
    }
}
