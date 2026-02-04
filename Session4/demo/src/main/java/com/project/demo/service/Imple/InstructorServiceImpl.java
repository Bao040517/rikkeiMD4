package com.project.demo.service.Imple;

import com.project.demo.dtos.InstructorCreateRequestDTO;
import com.project.demo.exceptions.NotFoundException;
import com.project.demo.mapper.InstructorMapper;
import com.project.demo.models.Instructor;
import com.project.demo.repository.InstructorRepository;
import com.project.demo.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    @Override
    public Instructor createInstructor(InstructorCreateRequestDTO instructorCreateRequestDTO) {
        return instructorRepository.save(instructorMapper.toInStructorEntity(instructorCreateRequestDTO)
        );
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found with id: " + id));
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor instructor) {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found with id: " + id)
                );

        existingInstructor.setName(instructor.getName());
        existingInstructor.setEmail(instructor.getEmail());

        return instructorRepository.save(existingInstructor);
    }

    @Override
    public void deleteById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found with id: " + id)
                );

        instructorRepository.delete(instructor);
    }
}
