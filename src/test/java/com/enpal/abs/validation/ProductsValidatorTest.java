package com.enpal.abs.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductsValidatorTest {

    private ProductsValidator productValidator;

    @BeforeEach
    public void setUp() {
        productValidator = new ProductsValidator();
    }

    @Test
    public void testValidProducts_SolarPanels() {
        assertTrue(productValidator.isValid(List.of("SolarPanels"), null));
    }

    @Test
    public void testValidProducts_Heatpumps() {
        assertTrue(productValidator.isValid(List.of("Heatpumps"), null));
    }

    @Test
    public void testValidProducts_AllValid() {
        assertTrue(productValidator.isValid(List.of("SolarPanels", "Heatpumps"), null));
    }

    @Test
    public void testInvalidProducts_AllInvalid() {
        assertFalse(productValidator.isValid(List.of("WindTurbines", "WindMill"), null));
    }

    @Test
    public void testInvalidProducts_OneInvalid() {
        assertFalse(productValidator.isValid(List.of("SolarPanels", "Heatpumps", "WindMill"), null));
    }

    @Test
    public void testNullProduct() {
        assertFalse(productValidator.isValid(null, null));
    }

    @Test
    public void testEmptyProduct() {
        assertFalse(productValidator.isValid(List.of(""), null));
    }

    @Test
    public void testInvalidProducts_TwoValidOneEmpty() {
        assertFalse(productValidator.isValid(List.of("SolarPanels", "Heatpumps", ""), null));
    }

    @Test
    public void testInvalidCharactersInProducts() {
        assertFalse(productValidator.isValid(List.of("Solar!Panels"), null));
    }

    @Test
    public void testLowerCaseProducts() {
        assertFalse(productValidator.isValid(List.of("solarpanels"), null));
    }

    @Test
    public void testUpperCaseProducts() {
        assertFalse(productValidator.isValid(List.of("SOLARPANELS"), null));
    }

    @Test
    public void testMixedCaseProduct() {
        assertFalse(productValidator.isValid(List.of("SoLaRpAnElS"), null));
    }

    @Test
    public void testWhitespaceInProduct() {
        assertFalse(productValidator.isValid(List.of(" SolarPanels "), null));
    }

    @Test
    public void testProductWithNumbers() {
        assertFalse(productValidator.isValid(List.of("SolarPanels123"), null));
    }
}