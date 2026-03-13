package com.project.demo.Mapper;

import com.project.demo.DTO.Request.EmployeeCreateDTO;
import com.project.demo.Entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeCreateDTO employeeCreateDTO);
    EmployeeCreateDTO toDTO(Employee employee);
}
