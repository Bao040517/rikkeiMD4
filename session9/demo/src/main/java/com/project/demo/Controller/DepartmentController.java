package com.project.demo.Controller;

import com.project.demo.DTO.Request.DepartmentDTO;
import com.project.demo.DTO.Response.ApiResponse;
import com.project.demo.DTO.Response.DepartmentResponse;
import com.project.demo.Entity.Department;
import com.project.demo.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> addDepartment(
            @Valid @RequestBody DepartmentDTO departmentDTO) {

        DepartmentResponse response = departmentService.createDepartment(departmentDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }
}
