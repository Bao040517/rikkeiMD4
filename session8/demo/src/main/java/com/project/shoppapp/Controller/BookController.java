package com.project.shoppapp.Controller;

import com.project.shoppapp.DTOs.Response.ApiResponse;
import com.project.shoppapp.DTOs.Request.BookCreateDTO;
import com.project.shoppapp.DTOs.Request.BookUpdateStockDTO;
import com.project.shoppapp.Entity.Book;
import com.project.shoppapp.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@ModelAttribute BookCreateDTO bookCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Book>builder()
                        .code("201")
                        .message("Book created successfully")
                        .data(bookService.createBook(bookCreateDTO))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Long id ,@Valid @RequestBody BookUpdateStockDTO bookUpdateStockDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Book>builder()
                        .message("Book updated successfully")
                        .data(bookService.updateBook(id,bookUpdateStockDTO))
                        .build()
        );
    }

}
