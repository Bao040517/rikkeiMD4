package com.project.shoppapp.Repository;

import com.project.shoppapp.DTOs.Response.TicketSummaryResponse;
import com.project.shoppapp.Entity.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    Optional<ParkingTicket>
    findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);
    @Query("SELECT new com.project.shoppapp.DTOs.Response.TicketSummaryResponse(t.id,\n" +
            "            v.licensePlate,\n" +
            "            z.name,\n" +
            "            t.checkInTime,\n" +
            "            t.checkOutTime) FROM ParkingTicket t\n" +
            "        JOIN t.vehicle v\n" +
            "        JOIN t.zone z WHERE FUNCTION('DATE', t.checkInTime) = CURRENT_DATE")
    List<TicketSummaryResponse> findTodayTickets(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
    @Query("""
    SELECT pt
    FROM ParkingTicket pt
    JOIN pt.vehicle v
    WHERE v.licensePlate = :licensePlate
      AND pt.checkInTime BETWEEN :fromDate AND :toDate
""")
    List<ParkingTicket> findByLicensePlateAndDateRange(
            @Param("licensePlate") String licensePlate,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate
    );
}
