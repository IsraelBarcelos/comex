package br.com.alura.comex.comercial.infra.produto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.comercial.dominio.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByNome(String string);
}
