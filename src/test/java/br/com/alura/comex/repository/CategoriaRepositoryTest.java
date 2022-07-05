package br.com.alura.comex.repository;

import br.com.alura.comex.builders.CategoriaBuilder;
import br.com.alura.comex.models.Categoria;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoriaRepositoryTest {

    @Autowired
    CategoriaRepository categoriaRepository;

    @BeforeEach
    public void setup() {
        Optional<Categoria> category = categoriaRepository.findByNome("teste");
        if (!category.isPresent()) {
            Categoria categoryOnDb = new CategoriaBuilder().comNome("teste").build();
            categoriaRepository.save(categoryOnDb);
        }

    }

    @Test
    public void shouldReturnCategoriaByName() {
        String categoryName = "teste";
        Optional<Categoria> category = categoriaRepository.findByNome(categoryName);

        Assertions.assertNotNull(category);
        Assertions.assertEquals(category.get().getNome(), categoryName);
    }

}
