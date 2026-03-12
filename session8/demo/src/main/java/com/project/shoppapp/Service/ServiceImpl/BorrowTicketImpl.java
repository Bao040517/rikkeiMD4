package com.project.shoppapp.Service.ServiceImpl;

import com.project.shoppapp.Entity.Book;
import com.project.shoppapp.Entity.BorrowStatus;
import com.project.shoppapp.Entity.BorrowTicket;
import com.project.shoppapp.Exception.AppException;
import com.project.shoppapp.Exception.ErrorCode;
import com.project.shoppapp.Repository.BookRepository;
import com.project.shoppapp.Repository.BorrowTicketRepository;
import com.project.shoppapp.Service.BorrowTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BorrowTicketImpl implements BorrowTicketService {

    @Autowired
    private BorrowTicketRepository borrowTicketRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BorrowTicket returnBook(Long id) {

        BorrowTicket borrowTicket = borrowTicketRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        if (borrowTicket.getBorrowStatus() == BorrowStatus.RETURNED) {
            throw new AppException(ErrorCode.BOOK_ALREADY_RETURNED);
        }

        Book book = bookRepository.findById(borrowTicket.getBookId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        borrowTicket.setBorrowStatus(BorrowStatus.RETURNED);
        borrowTicket.setReturnDate(LocalDateTime.now());

        book.setStock(book.getStock() + 1);

        bookRepository.save(book);

        return borrowTicketRepository.save(borrowTicket);
    }
}