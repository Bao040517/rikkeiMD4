package com.project.shoppapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "enrollment")
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "course_id", nullable = false)
    private Long courseId;
}
