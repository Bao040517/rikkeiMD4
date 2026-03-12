package com.project.shoppapp.Service.ServiceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project.shoppapp.DTOs.Request.ReaderCreateDTO;
import com.project.shoppapp.Entity.Reader;
import com.project.shoppapp.Repository.ReaderRepository;
import com.project.shoppapp.Service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Reader createReader(ReaderCreateDTO dto) {

        if (readerRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        String imageUrl = null;

        try {
            MultipartFile file = dto.getAvatarFile();

            if (file != null && !file.isEmpty()) {

                String fileName = file.getOriginalFilename();

                if (!(fileName.endsWith(".png")
                        || fileName.endsWith(".jpg")
                        || fileName.endsWith(".jpeg"))) {
                    throw new RuntimeException("Invalid image format");
                }

                Map uploadResult = cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.emptyMap()
                );

                imageUrl = uploadResult.get("url").toString();
            }

        } catch (Exception e) {
            throw new RuntimeException("Upload image failed");
        }

        Reader reader = new Reader();
        reader.setEmail(dto.getEmail());
        reader.setFullName(dto.getFullName());
        reader.setPhoneNumber(dto.getPhoneNumber());
        reader.setAddress(dto.getAddress());
        reader.setAvatar(imageUrl);

        return readerRepository.save(reader);
    }
}