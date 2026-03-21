package com.project.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Họ không được để trống")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Tên không được để trống")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Chuyên khoa không được để trống")
    @Column(nullable = false)
    private String specializationName;

    @Email(message = "Email không hợp lệ")
    private String email;

    private String phone;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("doctor-appointments")
    @Builder.Default
    private List<Appointment> appointments = new ArrayList<>();
}
