package com.project.shoppapp.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${app.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) {
        try {

            if (file.isEmpty() || file == null) {
                throw new RuntimeException("File is Empty");
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(uploadDir + fileName);
            file.transferTo(dest);

            return fileName;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
