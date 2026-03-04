package com.project.demo.service.Imple;

import com.project.demo.exceptions.NotFoundException;
import com.project.demo.models.Student;
import com.project.demo.repository.StudentRepository;
import com.project.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Student not found with id: " + id)
                );
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = findById(id);

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteById(Long id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }
}
