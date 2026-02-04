package com.project.demo.service;

import com.project.demo.dtos.InstructorCreateRequestDTO;
import com.project.demo.models.Instructor;

import java.util.List;

public interface InstructorService {

    Instructor createInstructor(InstructorCreateRequestDTO instructorCreateRequestDTO);

    List<Instructor> findAll();

    Instructor findById(Long id);

    Instructor updateInstructor(Long id, Instructor instructor);

    void deleteById(Long id);
}
