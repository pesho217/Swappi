package com.example.swappi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidGenderConstraint implements ConstraintValidator<ValidGender,String> {
    @Override
    public boolean isValid(String egnValue, ConstraintValidatorContext context) {
        if (egnValue == null || egnValue.length() != 10) {
            return false;
        }
        return true;
    }
}
