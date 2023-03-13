package com.validation.configuration.customValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeValidator implements ConstraintValidator<DateTimeConstraint, String> {
    @Override
    public void initialize(DateTimeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
