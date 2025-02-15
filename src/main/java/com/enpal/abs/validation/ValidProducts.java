package com.enpal.abs.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductsValidator.class)
public @interface ValidProducts {
    String message() default "Invalid product(s). Allowed values are: SolarPanels, Heatpumps";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}