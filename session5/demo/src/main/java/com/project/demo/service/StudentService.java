package com.project.demo.service;

import com.project.demo.models.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> findAll();

    Student findById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteById(Long id);
}
