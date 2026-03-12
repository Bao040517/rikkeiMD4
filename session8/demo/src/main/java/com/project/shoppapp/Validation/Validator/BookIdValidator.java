package com.project.shoppapp.Validation.Validator;

import com.project.shoppapp.Repository.BookRepository;
import com.project.shoppapp.Validation.ExistingBookId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BookIdValidator implements ConstraintValidator<ExistingBookId, Long> {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public boolean isValid(Long bookId, ConstraintValidatorContext constraintValidatorContext) {
        if (bookId == null) {
            return false;
        }

        return bookRepository.existsById(bookId);
    }
}
