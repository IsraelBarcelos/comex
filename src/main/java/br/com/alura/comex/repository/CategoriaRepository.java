package br.com.alura.comex.repository;

import br.com.alura.comex.models.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
  Categoria findByNome(String nomeCategoria);
}
