package br.com.alura.comex.comercial.infra.produto;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.comex.comercial.dominio.produto.Produto;
import br.com.alura.comex.comercial.infra.categoria.CategoriaRepositoryComJPA;
import br.com.alura.comex.utils.CreateCategoriaUtil;
import br.com.alura.comex.utils.CreateProdutoUtil;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProdutoRepositoryComJPATest {

    @Autowired
    ProdutoRepositoryComJPA produtoRepository;

    @Autowired
    CategoriaRepositoryComJPA categoriaRepository;

    @BeforeEach
    public void setup() throws Exception {
        CreateProdutoUtil.createProduto(produtoRepository, categoriaRepository);
    }

    @Test
    public void shouldReturnProdutoByName() {
        String productName = CreateCategoriaUtil.nome;
        Optional<Produto> produto = produtoRepository.encontrarPeloNome(productName);

        if (!produto.isPresent()) {
            Assertions.fail("Produto não encontrado");
        }

        Assertions.assertNotNull(produto.get());
        Assertions.assertEquals(productName, produto.get().getNome());
    }
}
