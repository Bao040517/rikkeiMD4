package com.project.shoppapp.Controller;

import com.project.shoppapp.DTOs.Request.ReaderCreateDTO;
import com.project.shoppapp.Entity.Reader;
import com.project.shoppapp.Service.ReaderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reader createReader(
            @ModelAttribute @Valid ReaderCreateDTO dto
    ) {
        return readerService.createReader(dto);
    }
}