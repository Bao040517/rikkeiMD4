package com.project.shoppapp.service;

import com.project.shoppapp.DTOs.InstructorDetail;
import com.project.shoppapp.models.Course;
import com.project.shoppapp.models.Instructor;

import java.util.List;


public interface InstructorService {
    Instructor createInstructor(Instructor instructor);
    List<Instructor> findAll();
    Instructor findById(Long id);
    void updateInstructor(Instructor instructor);
    void deleteById(Long id);
    List<InstructorDetail> findAllInstructors();

}
