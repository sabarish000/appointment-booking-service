package com.enpal.abs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    // This method checks if the provided date string is valid according to the specified format
    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
