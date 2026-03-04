package com.project.shoppapp.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "zone")
public class Zone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_zone")
    private String name;
    @Column(name = "capacity")
    private Long capacity;
    @Column(name = "occupied_spots")
    private Long occupiedSpots;

    @OneToMany(mappedBy = "zone")
    List<ParkingTicket> parkingTickets;
}
