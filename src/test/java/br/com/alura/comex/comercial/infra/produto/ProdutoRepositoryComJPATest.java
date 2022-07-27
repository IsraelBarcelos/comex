package br.com.alura.comex.comercial.infra.produto;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.comex.comercial.aplicacao.produto.ProdutoBuilder;
import br.com.alura.comex.comercial.dominio.produto.Produto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProdutoRepositoryComJPATest {

    @Autowired
    ProdutoRepositoryComJPA produtoRepository;

    @BeforeEach
    public void setup() {
        Optional<Produto> category = produtoRepository.encontrarPeloNome("teste");
        if (!category.isPresent()) {
            Produto categoryOnDb = new ProdutoBuilder().comNome("teste").build();
            produtoRepository.save(categoryOnDb);
        }

    }

    @Test
    public void shouldReturnProdutoByName() {
        String categoryName = "teste";
        Optional<Produto> category = produtoRepository.encontrarPeloNome(categoryName);

        Assertions.assertNotNull(category);
        Assertions.assertEquals(category.get().getNome(), categoryName);
    }
}
