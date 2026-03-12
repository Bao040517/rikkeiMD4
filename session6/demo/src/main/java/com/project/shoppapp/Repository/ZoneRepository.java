package com.project.shoppapp.Repository;

import com.project.shoppapp.DTOs.Response.ZoneStatisticsResponse;
import com.project.shoppapp.Entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Boolean existsByName(String name);
    Boolean stillHaveOccupiedSpots(Long id);

}
