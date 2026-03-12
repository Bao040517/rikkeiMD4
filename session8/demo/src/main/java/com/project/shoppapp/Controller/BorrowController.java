package com.project.shoppapp.Controller;

import com.project.shoppapp.DTOs.Request.BorrowCreateDTO;
import com.project.shoppapp.DTOs.Response.ApiResponse;
import com.project.shoppapp.Entity.Borrow;
import com.project.shoppapp.Entity.BorrowTicket;
import com.project.shoppapp.Repository.BorrowRepository;
import com.project.shoppapp.Service.BorrowTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private BorrowTicketService borrowTicketService;

    private final BorrowRepository borrowRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<Borrow>> createBorrow(
            @Valid @RequestBody BorrowCreateDTO dto) {

        Borrow borrow = Borrow.builder()
                .username(dto.getUsername())
                .bookId(dto.getBookId())
                .build();

        Borrow savedBorrow = borrowRepository.save(borrow);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Borrow>builder()
                        .success(true)
                        .data(savedBorrow)
                        .build());
    }
    @PatchMapping("/{id}/return")
    public ResponseEntity<ApiResponse<BorrowTicket>> returnBook(@PathVariable Long id) {

        BorrowTicket ticket = borrowTicketService.returnBook(id);

        ApiResponse<BorrowTicket> response = ApiResponse.<BorrowTicket>builder()
                .success(true)
                .data(ticket)
                .build();

        return ResponseEntity.ok(response);
    }
}