package com.project.shoppapp.Service.ServiceImpl;

import com.project.shoppapp.DTOs.Request.BookCreateDTO;
import com.project.shoppapp.DTOs.Request.BookUpdateStockDTO;
import com.project.shoppapp.Entity.Book;
import com.project.shoppapp.Exception.AppException;
import com.project.shoppapp.Exception.ErrorCode;
import com.project.shoppapp.Repository.BookRepository;
import com.project.shoppapp.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FileService fileService;

    @Override
    public Book createBook(BookCreateDTO bookCreateDTO) {
        Book book = new Book();
        book.setTitle(bookCreateDTO.getTitle());
        book.setAuthor(bookCreateDTO.getAuthor());
        book.setStock(bookCreateDTO.getStock());
        book.setCoverUrl(fileService.uploadFile(bookCreateDTO.getCoverImage()));
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, BookUpdateStockDTO bookUpdateStockDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));
        book.setStock(bookUpdateStockDTO.getStock());
        return bookRepository.save(book);
    }
}
