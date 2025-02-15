package com.enpal.abs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LanguageValidator implements ConstraintValidator<ValidLanguage, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Null values are invalid
        }
        return ValidValues.ALLOWED_LANGUAGES.contains(value);
    }
}