package com.project.demo.Service.Impl;

import com.project.demo.DTO.Request.EmployeeCreateDTO;
import com.project.demo.Entity.Employee;
import com.project.demo.Exception.AppException;
import com.project.demo.Exception.ErrorCode;
import com.project.demo.Mapper.EmployeeMapper;
import com.project.demo.Repository.DepartmentRepository;
import com.project.demo.Repository.EmployeeRepository;
import com.project.demo.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024;

    @Override
    public Employee createEmployee(EmployeeCreateDTO dto) {

        if (!departmentRepository.existsById(dto.getDepartment_id())) {
            throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
        }

        Employee employee = employeeMapper.toEntity(dto);

        return employeeRepository.save(employee);
    }

    @Override
    public String uploadAvatar(Long id, MultipartFile file) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        validateFile(file);

        String newFileName = generateFileName(file.getOriginalFilename());

        Path filePath = Paths.get(uploadDir, newFileName);

        try {

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        String avatarUrl = "/uploads/" + newFileName;

        employee.setAvatarUrl(avatarUrl);

        employeeRepository.save(employee);

        return avatarUrl;
    }

    private void validateFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new AppException(ErrorCode.VALIDATION_ERROR);
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new AppException(ErrorCode.VALIDATION_ERROR);
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null ||
                !(fileName.endsWith(".jpg")
                        || fileName.endsWith(".jpeg")
                        || fileName.endsWith(".png"))) {

            throw new AppException(ErrorCode.VALIDATION_ERROR);
        }
    }

    private String generateFileName(String originalFileName) {

        return UUID.randomUUID() + "_" + originalFileName;

    }
}