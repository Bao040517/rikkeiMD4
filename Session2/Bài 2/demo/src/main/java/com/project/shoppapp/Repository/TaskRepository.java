package com.project.shoppapp.Repository;

import com.project.shoppapp.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByTitleContainingIgnoreCase(String title);
}
