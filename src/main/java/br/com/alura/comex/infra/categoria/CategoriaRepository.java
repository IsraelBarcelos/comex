package br.com.alura.comex.infra.categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.dominio.categoria.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  Optional<Categoria> findByNome(String nomeCategoria);
}
