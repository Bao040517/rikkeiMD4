package com.project.demo.Repository;

import com.project.demo.Entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {

    List<Supply> findAllByIsDeletedFalse();

    List<Supply> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name);
}
