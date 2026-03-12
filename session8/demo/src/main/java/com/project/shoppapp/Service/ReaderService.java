package com.project.shoppapp.Service;

import com.project.shoppapp.DTOs.Request.ReaderCreateDTO;
import com.project.shoppapp.Entity.Reader;

public interface ReaderService {
    Reader createReader(ReaderCreateDTO dto);
}
