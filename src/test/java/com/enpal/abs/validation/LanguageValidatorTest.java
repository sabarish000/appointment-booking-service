package com.enpal.abs.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanguageValidatorTest {

    private LanguageValidator languageValidator;

    @BeforeEach
    public void setUp() {
        languageValidator = new LanguageValidator();
    }

    @Test
    public void testValidLanguage_German() {
        assertTrue(languageValidator.isValid("German", null));
    }

    @Test
    public void testValidLanguage_English() {
        assertTrue(languageValidator.isValid("English", null));
    }

    @Test
    public void testInvalidLanguage() {
        assertFalse(languageValidator.isValid("Spanish", null));
    }

    @Test
    public void testNullLanguage() {
        assertFalse(languageValidator.isValid(null, null));
    }

    @Test
    public void testEmptyLanguage() {
        assertFalse(languageValidator.isValid("", null));
    }

    @Test
    public void testInvalidCharactersInLanguage() {
        assertFalse(languageValidator.isValid("Engl!sh", null));
    }

    @Test
    public void testLowerCaseLanguage() {
        assertFalse(languageValidator.isValid("german", null));
    }

    @Test
    public void testUpperCaseLanguage() {
        assertFalse(languageValidator.isValid("GERMAN", null));
    }

    @Test
    public void testMixedCaseLanguage() {
        assertFalse(languageValidator.isValid("GeRmAn", null));
    }

    @Test
    public void testWhitespaceInLanguage() {
        assertFalse(languageValidator.isValid(" German ", null));
    }

    @Test
    public void testLanguageWithNumbers() {
        assertFalse(languageValidator.isValid("German123", null));
    }
}