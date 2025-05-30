package com.estudo.api_biblioteca.dto.request.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DataDevolucaoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataDevolucaoValida {

    String message() default "A data de devolução prevista deve ser posterior à data de empréstimo.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
