package com.enpal.abs.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RatingValidatorTest {

    private RatingValidator ratingValidator;

    @BeforeEach
    public void setUp() {
        ratingValidator = new RatingValidator();
    }

    @Test
    public void testValidRating_Gold() {
        assertTrue(ratingValidator.isValid("Gold", null));
    }

    @Test
    public void testValidRating_Silver() {
        assertTrue(ratingValidator.isValid("Silver", null));
    }

    @Test
    public void testValidRating_Bronze() {
        assertTrue(ratingValidator.isValid("Bronze", null));
    }

    @Test
    public void testInvalidRating() {
        assertFalse(ratingValidator.isValid("Platinum", null));
    }

    @Test
    public void testNullRating() {
        assertFalse(ratingValidator.isValid(null, null));
    }

    @Test
    public void testEmptyRating() {
        assertFalse(ratingValidator.isValid("", null));
    }

    @Test
    public void testInvalidCharactersInRating() {
        assertFalse(ratingValidator.isValid("Gold!", null));
    }

    @Test
    public void testLowerCaseRating() {
        assertFalse(ratingValidator.isValid("gold", null));
    }

    @Test
    public void testUpperCaseRating() {
        assertFalse(ratingValidator.isValid("GOLD", null));
    }

    @Test
    public void testMixedCaseRating() {
        assertFalse(ratingValidator.isValid("GoLd", null));
    }

    @Test
    public void testWhitespaceInRating() {
        assertFalse(ratingValidator.isValid(" Gold ", null));
    }

    @Test
    public void testRatingWithNumbers() {
        assertFalse(ratingValidator.isValid("Gold123", null));
    }
}