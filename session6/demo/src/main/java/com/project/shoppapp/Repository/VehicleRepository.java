package com.project.shoppapp.Repository;

import com.project.shoppapp.Entity.Vehicle;
import com.project.shoppapp.DTOs.Response.VehicleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    @Query("""
        SELECT new com.project.shoppapp.DTOs.Response.VehicleResponse (
            v.id,
            v.licensePlate,
            v.color,
            v.type
        )
        FROM Vehicle v
        WHERE (:keyword IS NULL 
               OR LOWER(v.licensePlate) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(v.color) LIKE LOWER(CONCAT('%', :keyword, '%')))
    """)
    Page<VehicleResponse> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
