package br.com.alura.comex.comercial.dominio.cliente;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.comex.comercial.dominio.categoria.ValidaIdCategoria;
import br.com.alura.comex.comercial.infra.categoria.CategoriaRepositoryComJPA;

public class IdCategoriaValidation implements ConstraintValidator<ValidaIdCategoria, Long> {

    @Autowired
    private CategoriaRepositoryComJPA categoriaRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return categoriaRepository.findById(value).isPresent();
    }

}
