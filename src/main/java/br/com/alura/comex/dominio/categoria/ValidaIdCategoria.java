package br.com.alura.comex.dominio.categoria;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.alura.comex.dominio.cliente.IdCategoriaValidation;

@Target({ java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCategoriaValidation.class)
public @interface ValidaIdCategoria {
    String message() default "Id da categoria é inválido";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
