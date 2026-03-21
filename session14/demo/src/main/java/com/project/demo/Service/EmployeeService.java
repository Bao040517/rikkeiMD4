package com.project.demo.Service;

import com.project.demo.DTO.request.EmployeeCreateDTO;
import com.project.demo.DTO.request.EmployeeUpdateDTO;
import com.project.demo.Entity.Employee;
import com.project.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Employee createEmployee(EmployeeCreateDTO dto) throws IOException {
        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());

        if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {
            String url = cloudinaryService.uploadImage(dto.getAvatarFile());
            employee.setAvatarUrl(url);
        }

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeUpdateDTO dto) throws IOException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Không tìm thấy nhân viên");
        }

        Employee employee = optionalEmployee.get();
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());

        if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {
            String url = cloudinaryService.uploadImage(dto.getAvatarFile());
            employee.setAvatarUrl(url);
        }

        return employeeRepository.save(employee);
    }
}
