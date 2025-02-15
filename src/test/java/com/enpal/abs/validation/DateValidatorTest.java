package com.enpal.abs.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateValidatorTest {

    private DateValidator dateValidator;

    @BeforeEach
    public void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testValidDate() {
        assertTrue(dateValidator.isValid("2024-02-28", null));
    }

    @Test
    public void testInvalidDate() {
        assertFalse(dateValidator.isValid("2024-02-30", null));
    }

    @Test
    public void testNullDate() {
        assertFalse(dateValidator.isValid(null, null));
    }

    @Test
    public void testEmptyDate() {
        assertFalse(dateValidator.isValid("", null));
    }

    @Test
    public void testInvalidFormatDate() {
        assertFalse(dateValidator.isValid("28-02-2024", null));
    }

    @Test
    public void testInvalidCharactersInDate() {
        assertFalse(dateValidator.isValid("2024-02-aa", null));
    }

    @Test
    public void testFutureDate() {
        assertTrue(dateValidator.isValid("2124-02-28", null));
    }

    @Test
    public void testLeapYearDate() {
        assertTrue(dateValidator.isValid("2024-02-29", null));
    }

    @Test
    public void testNonLeapYearDate() {
        assertFalse(dateValidator.isValid("2023-02-29", null));
    }

    @Test
    public void testMonthOutOfRange() {
        assertFalse(dateValidator.isValid("2024-13-01", null));
    }

    @Test
    public void testDayOutOfRange() {
        assertFalse(dateValidator.isValid("2024-01-32", null));
    }
}