package br.com.alura.comex.controllers.form.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.alura.comex.dominio.cliente.IdClienteValidation;

@Target({ java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdClienteValidation.class)
public @interface ValidaIdCliente {
    String message() default "Id do cliente é inválido.";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
