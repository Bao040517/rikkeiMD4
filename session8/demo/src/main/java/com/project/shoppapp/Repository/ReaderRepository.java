package com.project.shoppapp.Repository;

import com.project.shoppapp.Entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Optional<Reader> findByEmail(String email);
}
