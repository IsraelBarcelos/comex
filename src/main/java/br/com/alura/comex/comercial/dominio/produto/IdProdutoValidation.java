package br.com.alura.comex.comercial.dominio.produto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.comex.comercial.infra.produto.ProdutoRepositoryComJPA;

public class IdProdutoValidation implements ConstraintValidator<ValidaIdProduto, Long> {

    @Autowired
    private ProdutoRepositoryComJPA produtoRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return produtoRepository.encontrarProdutoPeloId(value).isPresent();
    }
}
