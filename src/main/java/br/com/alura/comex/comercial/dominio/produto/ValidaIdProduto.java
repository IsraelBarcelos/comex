package br.com.alura.comex.comercial.dominio.produto;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdProdutoValidation.class)
public @interface ValidaIdProduto {
    String message() default "Id do produto é inválido";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
