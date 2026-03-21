package com.project.demo.Controller;

import com.project.demo.Entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Nguyễn Văn A", 15000000.0),
                new Employee(2L, "Trần Thị B", 18000000.0),
                new Employee(3L, "Lê Văn C", 20000000.0)
        );
        return ResponseEntity.ok(employees);
    }
}
