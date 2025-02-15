package com.enpal.abs.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;


public class ProductsValidator implements ConstraintValidator<ValidProducts, List<String>> {
    @Override
    public boolean isValid(List<String> values, ConstraintValidatorContext context) {
        if (values == null || values.isEmpty()) {
            return false; // Null or empty lists are invalid
        }
        return ValidValues.ALLOWED_PRODUCTS.containsAll(values);
    }
}
