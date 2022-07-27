package br.com.alura.comex.comercial.infra.categoria;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.comex.comercial.aplicacao.categoria.CategoriaBuilder;
import br.com.alura.comex.comercial.dominio.categoria.Categoria;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoriaRepositoryComJPATest {

    @Autowired
    CategoriaRepositoryComJPA categoriaRepository;

    @BeforeEach
    public void setup() {
        Optional<Categoria> category = categoriaRepository.encontrarCategoriaPeloNome("teste");
        if (!category.isPresent()) {
            Categoria categoryOnDb = new CategoriaBuilder().comNome("teste").build();
            categoriaRepository.save(categoryOnDb);
        }

    }

    @Test
    void shouldReturnCategoriaByName() {
        String categoryName = "teste";
        Optional<Categoria> category = categoriaRepository.encontrarCategoriaPeloNome(categoryName);

        Assertions.assertNotNull(category);
        Assertions.assertEquals(category.get().getNome(), categoryName);
    }

}
