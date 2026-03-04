package com.project.shoppapp.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "license_plate")
    String licensePlate;

    @Column(name = "color")
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VehicleType type;

    @OneToMany(mappedBy = "vehicle")
    List<ParkingTicket> parkingTickets;


}
