package br.com.alura.comex.repository;

import br.com.alura.comex.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  Categoria findByNome(Categoria nomeCategoria);
}
