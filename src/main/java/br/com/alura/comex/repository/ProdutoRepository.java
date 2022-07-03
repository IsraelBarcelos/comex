package br.com.alura.comex.repository;

import br.com.alura.comex.models.Produto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByNome(String string);
}
