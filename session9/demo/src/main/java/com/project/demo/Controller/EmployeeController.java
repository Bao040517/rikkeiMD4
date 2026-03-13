package com.project.demo.Controller;

import com.project.demo.DTO.Request.EmployeeCreateDTO;
import com.project.demo.DTO.Response.ApiResponse;
import com.project.demo.Entity.Employee;
import com.project.demo.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(" /api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@Valid @RequestBody EmployeeCreateDTO employeeCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(employeeService.createEmployee(employeeCreateDTO)));
    }
    @PutMapping("/{id}/avatar")
    public ResponseEntity<ApiResponse<?>> uploadAvatar(@PathVariable Long id,@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(
                ApiResponse.success(employeeService.uploadAvatar(id, file))
        );
    }}
