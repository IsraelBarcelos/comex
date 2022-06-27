package br.com.alura.comex.repository;

import br.com.alura.comex.models.Categoria;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaRepositoryTest {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Test
    public void shouldReturnCategoriaByName() {
        String categoryName = "teste";
        Optional<Categoria> category = categoriaRepository.findByNome(categoryName);

        Assertions.assertNotNull(category);
        Assertions.assertEquals(category.get().getNome(), categoryName);
    }

}
