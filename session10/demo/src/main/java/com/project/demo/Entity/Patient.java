package com.project.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên bệnh nhân không được để trống")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Họ bệnh nhân không được để trống")
    @Column(nullable = false)
    private String lastName;

    @Min(value = 0, message = "Tuổi phải lớn hơn hoặc bằng 0")
    @Max(value = 150, message = "Tuổi không hợp lệ")
    private int age;

    @Email(message = "Email không hợp lệ")
    private String email;

    private String phone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("patient-prescriptions")
    @Builder.Default
    private List<Prescription> prescriptions = new ArrayList<>();
}
