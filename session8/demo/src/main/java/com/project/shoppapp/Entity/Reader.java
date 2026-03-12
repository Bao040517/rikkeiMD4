package com.project.shoppapp.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reader")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String avatar;

}
