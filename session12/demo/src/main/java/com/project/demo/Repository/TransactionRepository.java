package com.project.demo.Repository;

import com.project.demo.Entity.Transaction;
import com.project.demo.Entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t.supply.id, t.supply.name, SUM(t.amount) " +
            "FROM Transaction t " +
            "WHERE t.type = :type " +
            "AND t.createdAt >= :startOfDay " +
            "AND t.createdAt < :endOfDay " +
            "GROUP BY t.supply.id, t.supply.name")
    List<Object[]> findDailyExportStatistics(
            @Param("type") TransactionType type,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT t.supply.id, t.supply.name, SUM(t.amount) as totalAmount " +
            "FROM Transaction t " +
            "WHERE t.type = :type " +
            "GROUP BY t.supply.id, t.supply.name " +
            "ORDER BY totalAmount DESC")
    List<Object[]> findTopExportSupplies(@Param("type") TransactionType type);

    boolean existsByType(TransactionType type);
}
