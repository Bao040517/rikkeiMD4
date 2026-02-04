package com.project.demo.models;

import com.project.demo.models.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "course")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_instructor"))
    private Instructor instructor;

    @OneToMany(mappedBy = "course")
    private List<StudentEnrollment> studentEnrollments;
}
