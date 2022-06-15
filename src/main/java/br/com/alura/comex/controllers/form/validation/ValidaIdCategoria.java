package br.com.alura.comex.controllers.form.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCategoriaValidation.class)
public @interface ValidaIdCategoria {
    String message() default "Id da categoria deve existir no projeto";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
