package br.com.alura.comex.comercial.dominio.produto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class IdProdutoValidation implements ConstraintValidator<ValidaIdProduto, Long> {

    @Autowired
    private RepositorioDeProduto produtoRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return produtoRepository.encontrarProdutoPeloId(value).isPresent();
    }
}
