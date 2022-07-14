package br.com.alura.comex.infra.produto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.comex.dominio.produto.ValidaIdProduto;

public class IdProdutoValidation implements ConstraintValidator<ValidaIdProduto, Long> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return produtoRepository.findById(value).isPresent();
    }
}
