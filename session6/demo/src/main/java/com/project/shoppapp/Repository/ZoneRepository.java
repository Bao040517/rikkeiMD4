package com.project.shoppapp.Repository;

import com.project.shoppapp.Entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Boolean existsByName(String name);
    Boolean stillHaveOccupiedSpots(Long id);
}
