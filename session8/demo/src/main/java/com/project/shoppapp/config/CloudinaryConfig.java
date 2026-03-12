package com.project.shoppapp.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "Root",
                "api_key", "873787783283998",
                "api_secret", "nj1T2P3kpEUSuhCsmNiFw9klSqc"
        ));
    }
}
