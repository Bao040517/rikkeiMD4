package com.project.shoppapp.Service;

import com.project.shoppapp.DTOs.Request.BookCreateDTO;
import com.project.shoppapp.DTOs.Request.BookUpdateStockDTO;
import com.project.shoppapp.Entity.Book;

public interface BookService {
    Book createBook(BookCreateDTO bookCreateDTO);
    Book updateBook(Long id, BookUpdateStockDTO bookUpdateStockDTO);
}
