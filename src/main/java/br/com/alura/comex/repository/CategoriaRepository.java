package br.com.alura.comex.repository;

import br.com.alura.comex.models.Categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  Optional<Categoria> findByNome(String nomeCategoria);
}
