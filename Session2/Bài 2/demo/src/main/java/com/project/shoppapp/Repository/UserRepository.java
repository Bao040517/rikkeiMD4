package com.project.shoppapp.Repository;

import com.project.shoppapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findUsersByNameContainingIgnoreCase(String name);

    List<User> findUsersById(Long id);
}
