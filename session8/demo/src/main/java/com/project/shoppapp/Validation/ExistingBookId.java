package com.project.shoppapp.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)

public @interface ExistingBookId {
    String message() default "Sách không tồn tại trong hệ thống";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
