package com.project.demo.mapper;

import com.project.demo.dtos.InstructorCreateRequestDTO;
import com.project.demo.models.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {

    public Instructor toInStructorEntity(InstructorCreateRequestDTO dto) {
        Instructor instructor = new Instructor();
        instructor.setName(dto.getName());
        instructor.setEmail(dto.getEmail());
        return instructor;
    }
}
