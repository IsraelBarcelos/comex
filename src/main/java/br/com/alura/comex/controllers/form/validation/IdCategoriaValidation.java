package br.com.alura.comex.controllers.form.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.comex.repository.CategoriaRepository;

public class IdCategoriaValidation implements ConstraintValidator<ValidaIdCategoria, Long> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return categoriaRepository.findById(value).isPresent();
    }

}
