package com.project.shoppapp.Repository;

import com.project.shoppapp.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
